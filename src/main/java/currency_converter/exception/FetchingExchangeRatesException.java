package currency_converter.exception;

public class FetchingExchangeRatesException extends Exception {
    public FetchingExchangeRatesException() {
        super("Exception occurred while fetching exchange rates from API");
    }
}
