package by.scand.exchangerates.controller;

import by.scand.exchangerates.entity.GeneralRestResponse;
import by.scand.exchangerates.service.ExchangeRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;

@RestController
public class CurrencyController {

    private final ExchangeRateService exchangeRateService;

    @Autowired
    public CurrencyController(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }

    @GetMapping(path = "/api/currency/convert")
    public ResponseEntity convert(@RequestParam BigDecimal amount,
                                  @RequestParam String currencyFrom,
                                  @RequestParam String currencyTo) {
        GeneralRestResponse<BigDecimal> response = new GeneralRestResponse<>();

        try {
            BigDecimal convertedAmount = exchangeRateService.convert(amount, currencyFrom, currencyTo, new Date());

            response.setStatus("OK");
            response.setResult(convertedAmount);
        } catch (Exception ex) {
            response.setStatus("ERROR");
            response.setError(ex.getMessage());

            return new ResponseEntity(response, HttpStatus.OK);
        }

        return new ResponseEntity(response, HttpStatus.OK);
    }
}
