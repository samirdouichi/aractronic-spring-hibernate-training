package com.aractron.spring.training.dao;

import java.util.List;

import com.aractron.spring.training.domain.Course;
import com.aractron.spring.training.domain.CourseSession;

/**
 * Data Access interface for managing CourseSession objects.
 * 
 * @author vanessa.ortiz
 */
public interface CourseSessionDao {
	
	/**
	 * Method that retrieves a CourseSession object by using it's Id (PK).
	 *  
	 * @param id A CourseSession's unique identifier (PK).
	 * @return The CourseSession object being sought.
	 */
	public CourseSession findCourseSessionById(Long id);
	
	/**
	 * Method that retrieves a CourseSession object by using the 
	 * term and a Course's name. The combination of the term and 
	 * Course name makes the CourseSession object unique.
	 *  
	 * @param courseName The name of the Course associated with the CourseSession.
	 * @param term The period when the CourseSessoin is held: SPRING or FALL
	 * @return The CourseSession object being sought.
	 */
	public CourseSession findCourseSessionBySession(String courseName, String term);
	
	/**
	 * Method that retrieves all of the CourseSession in the CourseSession table.
	 * 
	 * @return An ArrayList of CourseSession objects.
	 */
	public List<CourseSession> findAllCourseSessions();
	
	/**
	 * Method that retrieves all of the CourseSession objects using the Course
	 * object that is related to it.
	 * 
	 * @param course The Course object that is related to the CourseSession.
	 * @return The CourseSession object being sought.
	 */
	public List<CourseSession> findAllCourseSessions(Course course);

	/**
	 * Method that saves a CourseSession object to the CourseSession table.
	 * @param course The CourseSession object to be saved.
	 */
	public void insert(CourseSession courseSession);
	
	/**
	 * Method that updates a CourseSession object in the CourseSession table.
	 * @param course The CourseSession object to be updated.
	 */
	public void update(CourseSession courseSession);
	
	/**
	 * Method that removes a CourseSession object from the CourseSession table.
	 * @param course The CourseSession object to be removed.
	 */
	public void delete(CourseSession courseSession);

}
