package com.aractron.spring.training.dao;

import java.util.List;

import com.aractron.spring.training.domain.CourseSession;
import com.aractron.spring.training.domain.Student;

/**
 * Data Access interface for managing Student objects.
 *  
 * @author vanessa.ortiz
 *
 */
public interface StudentDao extends UserDao<Student>{

	/**
	 * Method that retrieves all of the Students that are registered in 
	 * a CourseSession according to the Course's name.
	 * 
	 * @param courseName The name of the Course used to find the Students registered to it.
	 * @return An ArrayList of Student objects.
	 */
	public List<Student> findRegisteredStudentsByCourseName(String courseName);
	
	/**
	 * Method that saves the Student/CourseSession relationship in the DB.
	 * 
	 * @param student The Student to register to a CourseSession.
	 * @param courseSession The CourseSession that the Student is registering for.
	 */
	public void registerStudent(Student student, CourseSession courseSession);
	
	/**
	 * Method that removes the Student/CourseSessoin relationship in the DB.
	 * 
	 * @param student The Student to be unregistered from the CourseSession.
	 * @param courseSession The CourseSession that the Student is being removed from.
	 */
	public void unregisterStudent(Student student, CourseSession courseSession);
}
