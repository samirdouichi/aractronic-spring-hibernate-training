package com.aractron.spring.training.exception;

/**
 * Exception class that is thrown when an error occurs during course management.
 * 
 * @author aaron.levensailor
 */
public class CourseManagementException extends Exception {
	
	/**
	 * Serial version 
	 */
	private static final long serialVersionUID = -1265121178902282528L;

	/**
	 * Constructor that takes in a message.
	 * 
	 * @param message The message to display.
	 */
	public CourseManagementException(String message) {
		super(message);
	}

	/**
	 * Constructor that takes in throwable.
	 * 
	 * @param throwable The exception to throw.
	 */
	public CourseManagementException(Throwable throwable) {
		super(throwable);
	}

	/**
	 * Constructor that takes in a message and a throwable.
	 * 
	 * @param message The message to display.
	 * @param throwable The exception to throw.
	 */
	public CourseManagementException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
