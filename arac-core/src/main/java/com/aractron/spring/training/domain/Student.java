package com.aractron.spring.training.domain;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * Entity representing a Student at the University.
 */
public class Student extends User {
	
	/**
	 * Serial version
	 */
	private static final long serialVersionUID = -7724521139811867881L;
	
	/**
	 * Student's level 
	 */
	private StudentClassification classification = StudentClassification.FRESHMAN;
	
	/**
	 * Set of CourseSessions that the student is registered to.
	 */
	private Set<CourseSession> registeredCourses = new HashSet<CourseSession>();

	/**
	 * {@inheritDoc}
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder("Student[");
		sb.append("id=").append(getId());
		sb.append(", userName=").append(getUserName());
		sb.append(", firstName=").append(getFirstName());
		sb.append(", lastName=").append(getLastName());
		sb.append(", class=").append(getClassification());
		sb.append(", courseLoad=").append(getRegisteredCourses().size());
		return sb.append("]").toString();
	}

	/**
	 * {@inheritDoc}
	 */
	public int hashCode() {
		return new HashCodeBuilder().append(getId()).append(getUserName())
				.append(getFirstName()).append(getLastName())
				.append(getClassification()).toHashCode();
	}
	
	/**
	 * Getter for classification.
	 * 
	 * @return Student's classification.
	 */
	public StudentClassification getClassification() {
		return classification;
	}

	/**
	 * Setter for classification.
	 * @param classification The classification to set.
	 */
	public void setClassification(StudentClassification classification) {
		this.classification = classification;
	}

	/**
	 * Getter for registeredCourses.
	 * @return A set of CourseSession objects that the Student is registered to.
	 */
	public Set<CourseSession> getRegisteredCourses() {
		return registeredCourses;
	}

	/**
	 * Setter for registeredCourses
	 * @param registeredCourses The registeredCourses to set.
	 */
	public void setRegisteredCourses(Set<CourseSession> registeredCourses) {
		this.registeredCourses = registeredCourses;
	}
}
