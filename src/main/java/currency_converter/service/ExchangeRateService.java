package currency_converter.service;

import currency_converter.utils.Currency;

import java.io.IOException;

public interface ExchangeRateService {
    double getExchangeRate(Currency currency) throws IOException;
}
