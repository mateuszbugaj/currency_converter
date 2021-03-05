package currency_converter.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import currency_converter.utils.Currency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
@Profile({"prod"})
public class ExchangeRateServiceImp implements ExchangeRateService{

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public double getExchangeRate(Currency currency) throws IOException {
        URL url = new URL(String.format("http://api.nbp.pl/api/exchangerates/rates/a/%s/?format=json", currency.getCode()));
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        connection.disconnect();

        ObjectNode node = objectMapper.readValue(content.toString(), ObjectNode.class);

        return node.findValue("mid").asDouble();
    }
}
