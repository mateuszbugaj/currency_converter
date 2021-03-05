package currency_converter.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {NegativeValueException.class})
    @ResponseBody
    ResponseEntity<ExceptionResponse> handleUserAlreadyExistsException(HttpServletRequest request, NegativeValueException ex){
        ExceptionResponse response = new ExceptionResponse(ex.getClass().getName(), ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = {CurrencyNotFoundException.class})
    @ResponseBody
    ResponseEntity<ExceptionResponse> handleUserAlreadyExistsException(HttpServletRequest request, CurrencyNotFoundException ex){
        ExceptionResponse response = new ExceptionResponse(ex.getClass().getName(), ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {FetchingExchangeRatesException.class})
    @ResponseBody
    ResponseEntity<ExceptionResponse> handleUserAlreadyExistsException(HttpServletRequest request, FetchingExchangeRatesException ex){
        ExceptionResponse response = new ExceptionResponse(ex.getClass().getName(), ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.FAILED_DEPENDENCY);
    }

}
