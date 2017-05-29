package com.aractron.spring.training.domain;

/**
 * Entity representing a particular Faculty for a {@link Course}.
 */
public class Faculty extends User {
	
	/**
	 * Serial version
	 */
	private static final long serialVersionUID = -7458884322092102229L;
	
	/**
	 * The department name that the Faculty belongs to.
	 */
	private String department;

	/**
	 * Getter for the Faculty's department.
	 * 
	 * @return Department name.
	 */
	public String getDepartment() {
		return department;
	}

	/**
	 * Setter for the Faculty's department.
	 * 
	 * @param department The name to set.
	 */
	public void setDepartment(String department) {
		this.department = department;
	}
}
