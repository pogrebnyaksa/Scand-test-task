package by.scand.exchangerates.repository;

import by.scand.exchangerates.entity.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {

    ExchangeRate findFirstByOperationDateAndCurrency(Date date, String currency);

    Long countAllByOperationDateAndRateIsNull(Date date);

    Long countAllByOperationDate(Date date);

    void deleteAllByOperationDate(Date date);
}
