package com.cocus.bahamasapi.handler;

import com.cocus.bahamasapi.handler.exceptions.BahamasGovIntegrationException;
import com.cocus.bahamasapi.handler.exceptions.RecordConflictingException;
import com.cocus.bahamasapi.handler.exceptions.RecordNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        details.add("Server Time: " + LocalTime.now().toString());
        ErrorResponse error = new ErrorResponse("Server Error", details);
        return new ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(RecordNotFoundException.class)
    public final ResponseEntity<Object> handleRecordNotFoundException(RecordNotFoundException ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        details.add("Server Time: " + LocalTime.now().toString());
        ErrorResponse error = new ErrorResponse("Record Not Found", details);
        return new ResponseEntity(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RecordConflictingException.class)
    public final ResponseEntity<Object> handleRecordConflictingException(RecordConflictingException ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        details.add("Server Time: " + LocalTime.now().toString());
        ErrorResponse error = new ErrorResponse("Record Conflicting", details);
        return new ResponseEntity(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public final ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        details.add(ex.getCause().getCause().toString());
        details.add("Server Time: " + LocalTime.now().toString());
        ErrorResponse error = new ErrorResponse("Data Integrity Violation", details);
        return new ResponseEntity(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BahamasGovIntegrationException.class)
    public final ResponseEntity<Object> handleBahamasGovIntegrationException(BahamasGovIntegrationException ex, WebRequest request) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());
        details.add("Server Time: " + LocalTime.now().toString());
        ErrorResponse error = new ErrorResponse("Bahamas Gov Integration Error", details);
        return new ResponseEntity(error, HttpStatus.SERVICE_UNAVAILABLE);
    }
}