package currency_converter.dto;

public class ExchangeOutput {
    private double value;
    private double exchangeRate;

    public ExchangeOutput() {
    }

    public ExchangeOutput(double value, double exchangeRate) {
        this.value = value;
        this.exchangeRate = exchangeRate;
    }

    public double getValue() {
        return value;
    }

    public double getExchangeRate() {
        return exchangeRate;
    }
}
