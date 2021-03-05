package currency_converter.service;

import currency_converter.utils.Currency;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles
class ExchangeRateServiceImpTest {

    @Autowired
    ExchangeRateServiceImp exchangeRateServiceImp;

    @Test
    void getExchangeRate() throws IOException {
        // Given
        Currency currency = Currency.EUR;

        // When
        double exchangeRate = exchangeRateServiceImp.getExchangeRate(currency);
    }
}