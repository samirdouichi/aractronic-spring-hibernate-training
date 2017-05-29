package com.aractron.spring.training.exception;

/**
 * Exception class that is thrown when an error occurs during user management.
 * 
 * @author aaron.levensailor
 */
public class UserManagementException extends Exception {
	
	/**
	 * Serial version
	 */
	private static final long serialVersionUID = -401561337506372247L;

	/**
	 * Constructor that takes in a message.
	 * 
	 * @param message The message to display.
	 */
	public UserManagementException(String message) {
		super(message);
	}

	/**
	 * Constructor that takes in throwable.
	 * 
	 * @param throwable The exception to throw.
	 */
	public UserManagementException(Throwable throwable) {
		super(throwable);
	}

	/**
	 * Constructor that takes in a message and a throwable.
	 * 
	 * @param message The message to display.
	 * @param throwable The exception to throw.
	 */
	public UserManagementException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
