package com.scaler.capstone.jobportal.exception;

/**
 * Custom exception class for the Job Portal application.
 * Used to indicate application-specific errors.
 */
public class InvalidApplicationStatusException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new InvalidApplicationStatusException with the specified detail message.
     *
     * @param message the detail message
     */
    public InvalidApplicationStatusException(String message) {
        super(message);
    }

}
