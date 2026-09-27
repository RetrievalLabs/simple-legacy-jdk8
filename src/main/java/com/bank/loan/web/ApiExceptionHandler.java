package com.bank.loan.web;

import com.bank.loan.service.LoanIneligibleException;
import com.bank.loan.service.LoanNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.Collections;
import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(LoanNotFoundException.class)
    public ResponseEntity<Map<String, String>> notFound(LoanNotFoundException error) { return response(HttpStatus.NOT_FOUND, error.getMessage()); }
    @ExceptionHandler({LoanIneligibleException.class, IllegalStateException.class, IllegalArgumentException.class})
    public ResponseEntity<Map<String, String>> badRequest(RuntimeException error) { return response(HttpStatus.BAD_REQUEST, error.getMessage()); }
    private ResponseEntity<Map<String, String>> response(HttpStatus status, String message) {
        return new ResponseEntity<Map<String, String>>(Collections.singletonMap("error", message), status);
    }
}
