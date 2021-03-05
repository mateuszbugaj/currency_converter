package currency_converter.exception;

public class CurrencyNotFoundException extends Exception {
    public CurrencyNotFoundException(String code) {
        super(String.format("Currency with code %s not found", code));
    }
}
