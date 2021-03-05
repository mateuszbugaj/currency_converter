package currency_converter.controller;

import currency_converter.dto.ExchangeInput;
import currency_converter.dto.ExchangeOutput;
import currency_converter.exception.CurrencyNotFoundException;
import currency_converter.exception.FetchingExchangeRatesException;
import currency_converter.exception.NegativeValueException;
import currency_converter.service.ExchangeRateService;
import currency_converter.utils.Currency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
@RequestMapping("/api/v1/")
public class ExchangeController {

    @Autowired
    private ExchangeRateService exchangeRateService;

    @PostMapping("exchange")
    public ExchangeOutput exchange(@RequestBody ExchangeInput exchangeInput) throws Exception {

        if(exchangeInput.getValue() < 0){
            throw new NegativeValueException();
        }

        for(Currency currency:Currency.values()){
            if (currency.getCode().equals(exchangeInput.getCurrency())){
                try{
                    double exchangeRate = exchangeRateService.getExchangeRate(currency);
                    double exchangeOutputValue = exchangeRate * exchangeInput.getValue();
                    return new ExchangeOutput(exchangeOutputValue, exchangeRate);
                } catch (IOException e){
                    throw new FetchingExchangeRatesException();
                }
            }
        }

        throw new CurrencyNotFoundException(exchangeInput.getCurrency());
    }
}
