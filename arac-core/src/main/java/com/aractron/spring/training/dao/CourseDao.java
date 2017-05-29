package com.aractron.spring.training.dao;

import java.util.List;

import com.aractron.spring.training.domain.Course;

/**
 * Data Access interface for managing Course domain objects. 
 * 
 * @author vanessa.ortiz
 */
public interface CourseDao {
	
	/**
	 * Method that retrieves a Course object by using it's Id (PK).
	 *  
	 * @param id A Course's unique identifier (PK).
	 * @return The Course object being sought.
	 */
	public Course findCourseById(Long id);
	
	/**
	 * Method that retrieves a Course object by using it's name.
	 * 
	 * @param courseName The name of the Course, case sensitive.
	 * @return the Course object being sought.
	 */
	public Course findCourseByName(String courseName);
	
	/**
	 * Method that retrieves all of the Course object in the Course table.
	 * 
	 * @return An ArrayList of Course objects.
	 */
	public List<Course> findAllCourses();
	
	/**
	 * Method that retrieves any Course that belong to a specific department.
	 * 
	 * @param departmentName Name of the course department, case sensitive.
	 * @return An ArratList of Course objects.
	 */
	public List<Course> findCoursesByDepartment(String departmentName);
	
	/**
	 * Method that saves a Course object to the Course table.
	 * 
	 * @param course The Course object to be saved.
	 */
	public void insert(Course course);

	/**
	 * Method that updates a Course object in the Course table.
	 * 
	 * @param course The Course object to be updated.
	 */
	public void update(Course course);
	
	/**
	 * Method that removes a Course object from the Course table.
	 * 
	 * @param course The Course object to be removed.
	 */
	public void delete(Course course);
}
