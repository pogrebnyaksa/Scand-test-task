package by.scand.exchangerates.job;

import by.scand.exchangerates.entity.ExchangeRate;
import by.scand.exchangerates.payload.RatesCubeElement;
import by.scand.exchangerates.payload.RatesRootElement;
import by.scand.exchangerates.service.ExchangeRateService;
import by.scand.exchangerates.util.ExchangeRatesLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class ExchangeRateJob {

    private final ExchangeRateService exchangeRateService;
    private  final ExchangeRatesLoader exchangeRatesLoader;

    @Autowired
    public ExchangeRateJob(ExchangeRateService exchangeRateService,
                           ExchangeRatesLoader exchangeRatesLoader) {
        this.exchangeRateService = exchangeRateService;
        this.exchangeRatesLoader = exchangeRatesLoader;
    }

    @Scheduled(cron = "${exchange_rates.cron}")
    public void getRates() {
        Date currentDate = new Date();
        if (exchangeRateService.isIncompleteDailyRates(currentDate)) {
            exchangeRateService.deleteByDate(currentDate);

            RatesRootElement dailyRates = exchangeRatesLoader.getDailyRates();

            RatesCubeElement ratesCubeElement = dailyRates.getRatesCubeElement().getRatesCubeElements().get(0);
            ratesCubeElement.getRatesCubeElements().forEach(rate -> {
                ExchangeRate exchangeRate = new ExchangeRate();
                exchangeRate.setOperationDate(currentDate);
                exchangeRate.setCurrency(rate.getCurrency());
                exchangeRate.setRate(rate.getRate());

                exchangeRateService.save(exchangeRate);
            });
        }
    }
}
