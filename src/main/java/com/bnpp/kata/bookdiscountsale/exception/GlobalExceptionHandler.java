// src/main/java/com/bnpp/kata/bookdiscountsale/exception/GlobalExceptionHandler.java
package com.bnpp.kata.bookdiscountsale.exception;

import com.bnpp.kata.bookdiscountsale.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidBookException.class)
    public ResponseEntity<ErrorResponse> handleInvalidBasket(InvalidBookException ex) {
        ErrorResponse error = new ErrorResponse("INVALID_BASKET", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleJsonParse(HttpMessageNotReadableException ex) {
        ErrorResponse error = new ErrorResponse("JSON_PARSE_ERROR", "Invalid JSON format: ");
        return ResponseEntity.badRequest().body(error);  // 400!
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntime(RuntimeException ex) {
        ErrorResponse error = new ErrorResponse("INTERNAL_ERROR", "Pricing calculation failed");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

}
