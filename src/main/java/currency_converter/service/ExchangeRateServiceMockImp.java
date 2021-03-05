package currency_converter.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import currency_converter.utils.Currency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Profile({"test"})
public class ExchangeRateServiceMockImp implements ExchangeRateService{

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public double getExchangeRate(Currency currency) throws IOException {
        if(currency == Currency.EUR){
            return 2;
        }

        if(currency == Currency.GDP){
            return 3;
        }

        if(currency == Currency.USD){
            return 4;
        }

        return -1;
    }
}
