package currency_converter.exception;

public class NegativeValueException extends Exception {

    public NegativeValueException() {
        super("Exchange value cannot be negative.");
    }
}
