package com.lloyd.casemanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidation(MethodArgumentNotValidException ex) {

        List<Map<String, String>> violations = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(e -> Map.of(
                        "field", e.getField(),
                        "message", e.getDefaultMessage()))
                .toList();

        return ResponseEntity.badRequest().body(Map.of(
                "error", "Validation failed",
                "violations", violations
        ));
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<?> handleBusiness(IllegalStateException ex) {
        return ResponseEntity.status(422).body(Map.of(
                "error", ex.getMessage()
        ));
    }
}