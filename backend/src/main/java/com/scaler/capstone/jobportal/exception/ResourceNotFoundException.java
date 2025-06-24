package com.scaler.capstone.jobportal.exception;

/**
 * Custom exception class for the missing resources(eg: missing job application, missing user etc.).
 * Used to indicate application-specific errors.
 */
public class ResourceNotFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new ResourceNotFoundException with the specified detail message.
     *
     * @param message the detail message
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }

}
