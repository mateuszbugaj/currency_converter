package currency_converter.exception;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExceptionResponse {

    @JsonProperty("exception")
    private String exception;

    @JsonProperty("message")
    private String exceptionMessage;

    public ExceptionResponse() {

    }

    public ExceptionResponse(String exception, String exceptionMessage) {
        super();
        this.exception = exception;
        this.exceptionMessage = exceptionMessage;
    }

    public String getException() {
        return exception;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }
}
