package com.aractron.spring.training.web.forms;

import java.io.Serializable;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.aractron.spring.training.domain.Student;
import com.aractron.spring.training.domain.StudentClassification;
import com.aractron.spring.training.validation.Different;

/**
 * Simple form for new user registration that leverages JSR-303 validation.
 * <p>
 * A common alternate approach is to use domain objects as model bindings.
 */
@Different(left = "username", right = "password", message = "User name and password may not match")
// CS8 - lab 4 - Students to add Same annotation validation
public class RegisterForm implements Serializable {
	private static final long serialVersionUID = -692783094319843205L;
	@NotEmpty
	private String firstname;
	private String lastname;
	@NotEmpty
	private String username;
	@NotEmpty
	private String password;
	private String passwordConfirmation;
	@Email
	private String email;
	@Pattern(regexp = "\\d{3}-\\d{3}-\\d{4}")
	private String phoneNo;
	private StudentClassification classification;

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public StudentClassification getClassification() {
		return classification;
	}

	public void setClassification(StudentClassification classification) {
		this.classification = classification;
	}

	/**
	 * Create a new Student object from this form.
	 */
	public Student transform() {
		Student student = new Student();
		updateEntity(student);
		return student;
	}

	/**
	 * Update a Student object with the fields from this form.
	 */
	public void updateEntity(Student student) {
		student.setEmail(getEmail());
		student.setFirstName(getFirstname());
		student.setLastName(getLastname());
		student.setPassword(getPassword());
		student.setUserName(getUsername());
		student.setPhoneNumber(getPhoneNo());
		student.setClassification(getClassification());
	}

	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}

	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
	}
}
