package by.scand.exchangerates.service;

import by.scand.exchangerates.entity.ExchangeRate;

import java.math.BigDecimal;
import java.util.Date;

public interface ExchangeRateService {

    void save(ExchangeRate exchangeRate);

    void deleteByDate(Date date);

    Boolean isIncompleteDailyRates(Date date);

    BigDecimal convert(BigDecimal amount, String currencyFrom, String currencyTo, Date date);

    BigDecimal getConvertMultiplier(String currencyFrom, String currencyTo, Date date);
}
