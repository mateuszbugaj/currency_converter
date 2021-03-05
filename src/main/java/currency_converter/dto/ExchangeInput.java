package currency_converter.dto;

public class ExchangeInput {
    private final double value;
    private final String currency;

    public ExchangeInput(double value, String currency) {
        this.value = value;
        this.currency = currency;
    }

    public double getValue() {
        return value;
    }

    public String getCurrency() {
        return currency;
    }
}
