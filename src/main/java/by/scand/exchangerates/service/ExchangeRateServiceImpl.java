package by.scand.exchangerates.service;

import by.scand.exchangerates.entity.ExchangeRate;
import by.scand.exchangerates.exception.ConvertRatesException;
import by.scand.exchangerates.repository.ExchangeRateRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

@Slf4j
@Service
@Transactional
public class ExchangeRateServiceImpl implements ExchangeRateService {

    private static final String BASIC_CURRENCY = "EUR";
    private static final int DEFAULT_SCALE = 2;

    private final ExchangeRateRepository exchangeRateRepository;

    @Autowired
    public ExchangeRateServiceImpl(ExchangeRateRepository exchangeRateRepository) {
        this.exchangeRateRepository = exchangeRateRepository;
    }

    @Override
    public void save(ExchangeRate exchangeRate) {
        exchangeRateRepository.save(exchangeRate);
    }

    @Override
    public void deleteByDate(Date date) {
        exchangeRateRepository.deleteAllByOperationDate(date);
    }

    @Override
    public Boolean isIncompleteDailyRates(Date date) {
        return exchangeRateRepository.countAllByOperationDate(date) == 0 ||
                exchangeRateRepository.countAllByOperationDateAndRateIsNull(date) > 0;
    }

    @Override
    public BigDecimal convert(BigDecimal amount, String currencyFrom, String currencyTo, Date date) {
        return amount.multiply(getConvertMultiplier(currencyFrom, currencyTo, date));
    }

    @Override
    public BigDecimal getConvertMultiplier(String currencyFrom, String currencyTo, Date date) {
        if (currencyTo.equalsIgnoreCase(currencyFrom)) {
            return BigDecimal.ONE;
        } else  if (currencyFrom.equalsIgnoreCase(BASIC_CURRENCY)) {
            BigDecimal rateTo = exchangeRateRepository.findFirstByOperationDateAndCurrency(date, currencyTo).getRate();

            if (rateTo == null) {
                throw new ConvertRatesException("Exchange rate don't defined");
            }

            return rateTo;
        } else {
            BigDecimal rateFrom = exchangeRateRepository.findFirstByOperationDateAndCurrency(date, currencyFrom).getRate();
            BigDecimal rateTo = currencyTo.equalsIgnoreCase(BASIC_CURRENCY) ? BigDecimal.valueOf(1) :
                    exchangeRateRepository.findFirstByOperationDateAndCurrency(date, currencyTo).getRate();

            if (rateFrom == null || rateTo == null) {
                throw new ConvertRatesException("Exchange rates don't defined");
            }

            return BigDecimal.valueOf(1)
                    .divide(rateFrom, DEFAULT_SCALE, RoundingMode.CEILING)
                    .multiply(rateTo);
        }
    }
}
