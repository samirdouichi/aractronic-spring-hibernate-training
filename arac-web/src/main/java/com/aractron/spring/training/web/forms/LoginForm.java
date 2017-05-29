package com.aractron.spring.training.web.forms;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * Form for log-in credentials. Uses JSR-303 validation.
 */
public class LoginForm implements Serializable {
	private static final long serialVersionUID = 430640256654323784L;

	@NotEmpty
	private String username;
	@NotEmpty
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String userName) {
		this.username = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
