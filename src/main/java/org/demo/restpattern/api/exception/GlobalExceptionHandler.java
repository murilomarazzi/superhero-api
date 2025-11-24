package org.demo.restpattern.api.exception;

import lombok.extern.slf4j.Slf4j;
import org.demo.restpattern.api.dto.ErrorResponse;
import org.demo.restpattern.core.exception.InvalidSuperheroDataException;
import org.demo.restpattern.core.exception.SuperheroNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(SuperheroNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleSuperheroNotFoundException(SuperheroNotFoundException ex) {
        var error = new ErrorResponse(
                ex.getMessage(),
                "Not Found",
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
    
    @ExceptionHandler(InvalidSuperheroDataException.class)
    public ResponseEntity<ErrorResponse> handleInvalidSuperheroDataException(InvalidSuperheroDataException ex) {
        var error = new ErrorResponse(
                ex.getMessage(),
                "Bad Request",
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {
        var errors = ex.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(", "));
        
        var error = new ErrorResponse(
                errors,
                "Validation Error",
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        log.error("Unexpected error occurred", ex);
        var error = new ErrorResponse(
                "An unexpected error occurred",
                "Internal Server Error",
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
