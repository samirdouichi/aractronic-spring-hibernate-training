package com.aractron.spring.training.domain.hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.aractron.spring.training.domain.Course;
import com.aractron.spring.training.domain.StudentClassification;

/**
 * Hibernate version of the Course domain object.
 * 
 * @author vanessa.ortiz
 */
@Entity
@Table(name = "COURSE")
public class HibernateAnnotationCourse extends Course{

	/**
	 * Serial version
	 */
	private static final long serialVersionUID = 4230192504153926206L;

	/**
	 * Course Id, (PK).
	 * 
	 */
	@Id @GeneratedValue
	@Column(name = "COURSE_ID" )
	private Long id;
	
	/**
	 * The Course's name
	 */
	@Column(name = "NAME")
	private String name;
	
	/**
	 * The Course's description
	 */
	@Column(name = "DESCRIPTION")
	private String description;
	
	/**
	 * The Course's department name
	 */
	@Column(name = "DEPARTMENT")
	private String department;
	
	/**
	 * The Course's number of credit hours
	 */
	@Column(name = "CREDITHOURS")
	private double creditHours;
	
	/**
	 * The Course's Student classification level. Students that have the same
	 * level can register for this course.
	 */
	@Enumerated(value=EnumType.STRING) 
	@Column(name = "LEVEL")
	private StudentClassification level;
	
	
	/**
	 * Empty Constructor
	 */
	public HibernateAnnotationCourse(){
		// Empty constructor required by Hibernate
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
	 * Retrieve the short name of this Course.
	 * 
	 * @return null by default
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Retrieve the description of this Course.
	 * 
	 * @return null by default
	 */
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Retrieve the name of the department primarily 
	 * responsible for this Course.
	 * 
	 * @return null by default
	 */
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	/**
	 * Retrieve the number of credit hours for this Course.
	 * 
	 * @return 0 by default
	 */
	public double getCreditHours() {
		return creditHours;
	}

	public void setCreditHours(double creditHours) {
		this.creditHours = creditHours;
	}


	/**
	 * @return the level
	 */
	public StudentClassification getLevel() {
		return level;
	}


	/**
	 * @param level the level to set
	 */
	public void setLevel(StudentClassification level) {
		this.level = level;
	}
}
