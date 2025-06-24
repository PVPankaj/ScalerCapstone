package com.scaler.capstone.jobportal.exception;

/**
 * Custom AlreadyAppliedForJobException class for the Job Portal application.
 * Used to indicate application-specific errors.
 */
public class AlreadyAppliedForJobException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new AlreadyAppliedForJobException with the specified detail message.
     *
     * @param message the detail message
     */
    public AlreadyAppliedForJobException(String message) {
        super(message);
    }

}
