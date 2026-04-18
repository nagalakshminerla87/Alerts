package com.lloyd.alerts.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidation(MethodArgumentNotValidException ex) {

        List<Map<String, String>> violations = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(e -> Map.of("field", e.getField(), "message", e.getDefaultMessage()))
                .toList();

        return ResponseEntity.badRequest().body(Map.of(
                "error", "Validation failed",
                "violations", violations
        ));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleBusiness(RuntimeException ex) {
        return ResponseEntity.unprocessableEntity().body(Map.of(
                "error", ex.getMessage()
        ));
    }
}
