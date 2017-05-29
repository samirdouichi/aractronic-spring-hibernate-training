package com.aractron.spring.training.domain.hibernate;

import com.aractron.spring.training.domain.StudentClassification;

/**
 * Child class of the EnumUserType used to work around the problem
 * that Hibernate has when mapping Enum java types.
 * 
 * @author vanessa.ortiz
 */
public class StudentClassificationUserType extends EnumUserType<StudentClassification>{

	/**
	 * Class constructor.
	 */
	public StudentClassificationUserType() {
		super(StudentClassification.class);
	}

}
