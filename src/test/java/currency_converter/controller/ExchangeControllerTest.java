package currency_converter.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import currency_converter.dto.ExchangeOutput;
import currency_converter.exception.CurrencyNotFoundException;
import currency_converter.exception.NegativeValueException;
import currency_converter.utils.Currency;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles(profiles = "test")
@AutoConfigureMockMvc
public class ExchangeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void exchangeWithCorrectValues() throws Exception {
        // Given
        double value = 10.0;
        Currency currency = Currency.EUR;

        // When
        String request = String.format("{\"value\": %f, \"currency\": \"%s\"}", value, currency.getCode());
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1/exchange")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();


        // Then
        ExchangeOutput exchangeOutput = new ObjectMapper()
                .readValue(result.getResponse().getContentAsString(), ExchangeOutput.class);

        Assertions.assertEquals(value * 2, exchangeOutput.getValue());
        Assertions.assertEquals(2, exchangeOutput.getExchangeRate());
    }

    @Test
    void exchangeWithNegativeValueAndReturnConflict() throws Exception {
        // Given
        double value = -10.0;
        Currency currency = Currency.EUR;

        // When
        String request = String.format("{\"value\": %f, \"currency\": \"%s\"}", value, currency.getCode());
        String errorMessage = mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1/exchange")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict())
                .andReturn().getResolvedException().getMessage();


        Assertions.assertEquals(new NegativeValueException().getMessage(), errorMessage);
    }

    @Test
    void exchangeWithWrongCurrencyCodeAndReturnNotFound() throws Exception {
        // Given
        double value = 10.0;
        String notExistingCurrencyCode = "NotExisting";

        // When
        String request = String.format("{\"value\": %f, \"currency\": \"%s\"}", value, notExistingCurrencyCode);
        String errorMessage = mockMvc.perform(MockMvcRequestBuilders
                .post("/api/v1/exchange")
                .content(request)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andReturn().getResolvedException().getMessage();


        Assertions.assertEquals(new CurrencyNotFoundException(notExistingCurrencyCode).getMessage(), errorMessage);
    }
}