package com.scaler.capstone.jobportal.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

/**
 * Centralised exception → HTTP-response mapping.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleNotFound(ResourceNotFoundException ex) {
        return build(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(InvalidApplicationStatusException.class)
    public ResponseEntity<ApiError> handleBusiness(InvalidApplicationStatusException ex) {
        return build(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(AlreadyAppliedForJobException.class)
    public ResponseEntity<ApiError> handleBusiness(AlreadyAppliedForJobException ex) {
        return build(HttpStatus.CONFLICT, ex.getMessage());
    }


    @ExceptionHandler(JobPortalException.class)
    public ResponseEntity<ApiError> handleBusiness(JobPortalException ex) {
        return build(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    /* ---------- helper ---------- */
    private ResponseEntity<ApiError> build(HttpStatus status, String msg) {
        return ResponseEntity.status(status)
                .body(new ApiError(status.value(), msg, LocalDateTime.now()));
    }

    /**
     * Simple error payload.
     */
    public record ApiError(int status, String message, LocalDateTime timestamp) {
    }
}