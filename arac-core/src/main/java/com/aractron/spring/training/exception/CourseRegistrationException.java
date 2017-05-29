package com.aractron.spring.training.exception;

/**
 * Exception class that is thrown when an error occurs during course registration.
 * 
 * @author aaron.levensailor
 */
public class CourseRegistrationException extends Exception {
	
	/**
	 * Serial version
	 */
	private static final long serialVersionUID = 9045592219602792047L;

	/**
	 * Constructor that takes in a message.
	 * 
	 * @param message The message to display.
	 */
	public CourseRegistrationException(String message) {
		super(message);
	}

	/**
	 * Constructor that takes in throwable.
	 * 
	 * @param throwable The exception to throw.
	 */
	public CourseRegistrationException(Throwable throwable) {
		super(throwable);
	}

	/**
	 * Constructor that takes in a message and a throwable.
	 * 
	 * @param message The message to display.
	 * @param throwable The exception to throw.
	 */
	public CourseRegistrationException(String message, Throwable throwable) {
		super(message, throwable);
	}

}
