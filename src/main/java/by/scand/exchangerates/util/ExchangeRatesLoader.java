package by.scand.exchangerates.util;

import by.scand.exchangerates.exception.GetRatesException;
import by.scand.exchangerates.payload.RatesRootElement;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.net.MalformedURLException;
import java.net.URL;

@Component
public class ExchangeRatesLoader {

    private static final String GET_RATES_ERROR_MESSAGE = "A problem occurred during getting exchange rates";

    @Value("${exchange_rates.url}")
    private String exchangeRatesUrl;

    public RatesRootElement getDailyRates() {
        RatesRootElement rates = null;

        try {
            URL url = new URL(exchangeRatesUrl);

            JAXBContext context = JAXBContext.newInstance(RatesRootElement.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            rates = (RatesRootElement) unmarshaller.unmarshal(url);

            return rates;
        } catch (JAXBException | MalformedURLException e) {
            throw new GetRatesException(GET_RATES_ERROR_MESSAGE);
        }
    }
}
