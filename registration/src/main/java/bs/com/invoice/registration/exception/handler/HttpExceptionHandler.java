package bs.com.invoice.registration.exception.handler;

import bs.com.invoice.registration.exception.InvoiceAlreadyPresentException;
import bs.com.invoice.registration.exception.NotFoundException;
import bs.com.invoice.registration.exception.handler.details.ErrorDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@Slf4j
@RestControllerAdvice
public class HttpExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorDetails> handlerNotFoundException(final NotFoundException ex) {
        return new ResponseEntity<>(ErrorDetails.builder()
                .error("NotFoundException")
                .timestamp(new Date())
                .message(ex.getMessage())
                .status(HttpStatus.NOT_FOUND.value())
                .build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvoiceAlreadyPresentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorDetails> handlerInvoiceAlreadyPresentException(final InvoiceAlreadyPresentException ex) {
        return new ResponseEntity<>(ErrorDetails.builder()
                .error("InvoiceAlreadyPresentException")
                .timestamp(new Date())
                .message(ex.getMessage())
                .status(HttpStatus.BAD_REQUEST.value())
                .build(), HttpStatus.BAD_REQUEST);
    }

}
