package com.aractron.spring.training.domain.hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.aractron.spring.training.domain.Faculty;

/**
 * Hibernate version of the Faculty domain object.
 * 
 * @author vanessa.ortiz
 */
@Entity
@Table(name = "FACULTY")
public class HibernateAnnotationFaculty extends Faculty {
	
	/**
	 * Serial version
	 */
	private static final long serialVersionUID = -7516809362956311444L;

	/**
	 * Faculty Id, (PK).
	 * 
	 */
	@Id @GeneratedValue
	@Column(name = "FACULTY_ID" )
	private Long id;
	
	/**
	 * Faculty's username, unique in the system
	 */
	@Column(name = "USERNAME")
	private String userName;
	
	/**
	 * Faculty's password 
	 */
	@Column(name = "PASSWORD")
	private String password;
	
	/**
	 * Faculty's first name
	 */
	@Column(name = "FIRSTNAME")
	private String firstName;
	
	/**
	 * Faculty's last name
	 */
	@Column(name = "LASTNAME")
	private String lastName;
	
	/**
	 * Faculty's email address
	 */
	@Column(name = "EMAIL")
	private String email;
	
	/**
	 * Faculty's phone number
	 */
	@Column(name = "PHONENUMBER")
	private String phoneNumber;
	
	/**
	 * The department name that the Faculty belongs to.
	 */
	@Column(name = "DEPARTMENT")
	private String department;

	/**
	 * Empty Constructor
	 */
	public HibernateAnnotationFaculty(){
		// Empty constructor required by Hibernate
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.SHORT_PREFIX_STYLE);
	}

	/**
	 * {@inheritDoc}
	 */
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
	
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the department
	 */
	public String getDepartment() {
		return department;
	}

	/**
	 * @param department the department to set
	 */
	public void setDepartment(String department) {
		this.department = department;
	}
}
