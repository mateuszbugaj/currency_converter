package currency_converter.utils;

public enum Currency {
    USD ("USD"),
    GDP ("GBP"),
    EUR ("EUR");

    private final String code;

    Currency(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
