package com.aractron.spring.training.domain;

import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Entity representing a Course in the university's curriculum.
 */
public class Course extends BaseEntity implements Comparable<Course> {

	/**
	 * Serial version
	 */
	private static final long serialVersionUID = -7987659475447046780L;

	/**
	 * The Course's name
	 */
	private String name;

	/**
	 * The Course's name
	 */
	private String description;

	/**
	 * The Course's department name
	 */
	private String department;

	/**
	 * The Course's number of credit hours
	 */
	private double creditHours;

	/**
	 * The Course's Student classification level. Students that have the same
	 * level can register for this course.
	 */
	private StudentClassification level;

	/**
	 * {@inheritDoc}
	 */
	public int compareTo(Course that) {
		int sig = this.getDepartment().compareTo(that.getDepartment());
		if (sig == 0) {
			sig = this.getLevel().compareTo(that.getLevel());
			if (sig == 0) {
				sig = (int) (this.getCreditHours() - that.getCreditHours());
				if (sig == 0) {
					sig = this.getName().compareTo(that.getName());
				}
			}
		}
		return sig;
	}

	/**
	 * {@inheritDoc}
	 */
	public int hashCode() {
		return new HashCodeBuilder().append(getId()).append(getName())
				.append(getDepartment()).toHashCode();
	}

	/**
	 * Retrieve the number of credit hours for this Course.
	 * 
	 * @return 0 by default
	 */
	public double getCreditHours() {
		return creditHours;
	}

	/**
	 * Retrieve the name of the department primarily responsible for this
	 * Course.
	 * 
	 * @return null by default
	 */
	public String getDepartment() {
		return department;
	}

	/**
	 * Retrieve the description of this Course.
	 * 
	 * @return null by default
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Retrieve the level of this Course.
	 * 
	 * @return
	 */
	public StudentClassification getLevel() {
		return level;
	}

	/**
	 * Retrieve the short name of this Course.
	 * 
	 * @return null by default
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter for the number of credit hours.
	 * 
	 * @param creditHours
	 */
	public void setCreditHours(double creditHours) {
		this.creditHours = creditHours;
	}

	/**
	 * Setter for the department name.
	 * 
	 * @param department
	 */
	public void setDepartment(String department) {
		this.department = department;
	}

	/**
	 * Setter for the Course description.
	 * 
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Setter for the Course level.
	 * 
	 * @param level
	 */
	public void setLevel(StudentClassification level) {
		this.level = level;
	}

	/**
	 * Setter for the Course name.
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
}
