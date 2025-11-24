package org.demo.restpattern.api.exception;

import lombok.extern.slf4j.Slf4j;
import org.demo.restpattern.core.exception.InvalidSuperheroDataException;
import org.demo.restpattern.core.exception.SuperheroNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(SuperheroNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(SuperheroNotFoundException ex) {
        return build(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(InvalidSuperheroDataException.class)
    public ResponseEntity<Map<String, Object>> handleInvalid(InvalidSuperheroDataException ex) {
        return build(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneric(Exception ex) {
        log.error("Unhandled exception", ex);
        return build(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred");
    }

    private ResponseEntity<Map<String, Object>> build(HttpStatus status, String message) {
        return ResponseEntity.status(status).body(Map.of(
                "status", status.value(),
                "error", status.getReasonPhrase(),
                "message", message,
                "timestamp", LocalDateTime.now().toString()
        ));
    }
}

