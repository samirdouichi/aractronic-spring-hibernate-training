package com.aractron.spring.training.domain;

/**
 * Entity representing a user of the university portal system.
 */
public abstract class User extends BaseEntity implements Comparable<User> {
	
	/**
	 * Serial version
	 */
	private static final long serialVersionUID = -1424855053811498503L;

	/**
	 * User's username, unique in the system
	 */
	private String userName;

	/**
	 * User's password 
	 */
	private String password;

	/**
	 * User's first name
	 */
	private String firstName;

	/**
	 * User's last name
	 */
	private String lastName;

	/**
	 * User's email address
	 */
	private String email;

	/**
	 * User's phone number
	 */
	private String phoneNumber;

	/**
	 * {@inheritDoc}
	 */
	public int compareTo(User that) {
		int sig = this.getUserName().compareTo(that.getUserName());
		return sig;
	}

	/**
	 * Getter for username.
	 * 
	 * @return The user's username.
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Setter for username.
	 * 
	 * @param userName The username to set.
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * Getter for password.
	 * 
	 * @return The user's password.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Setter for password.
	 * 
	 * @param password The password to set.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Getter for firstName.
	 * 
	 * @return The user's first name.
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Setter for firstName.
	 * 
	 * @param firstName the firstName to set.
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Getter for last name.
	 * 
	 * @return The users last name.
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Setter for lastName.
	 * 
	 * @param lastName The last name to set.
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Getter for email.
	 * 
	 * @return User's email address.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Setter for email.
	 * 
	 * @param email The email to set.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Getter for phoneNumber.
	 * 
	 * @return The user's phone number.
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * Setter for phoneNumber.
	 * 
	 * @param phoneNumber The phoneNumber to set. 
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
