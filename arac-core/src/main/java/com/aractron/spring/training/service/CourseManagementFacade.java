package com.aractron.spring.training.service;

import java.util.Collection;
import java.util.List;

import com.aractron.spring.training.domain.Course;
import com.aractron.spring.training.domain.CourseSession;
import com.aractron.spring.training.domain.Faculty;
import com.aractron.spring.training.exception.CourseManagementException;

/**
 * Service layer interface for {@link Course} management activities.
 * 
 * @author aaron.levensailor
 */
public interface CourseManagementFacade {

	/**
	 * Saves a new Course to the course catalog (course table).
	 * 
	 * @param course
	 */
	public void addNewCourseIntoCatalog(Course course);

	/**
	 * Adds a session of the course to the roster.
	 * 
	 * @param course
	 *            Course instance
	 * @param term
	 *            term for the session
	 * @return new CourseSession entity
	 */
	public CourseSession addSession(Course course, String term)
			throws CourseManagementException;

	/**
	 * Return all of the Course objects in the system.
	 * 
	 * @return empty collection by default
	 */
	public Collection<Course> findAllCourses();

	/**
	 * Retrieves Course object from the catalog (course table) by looking it up
	 * using the course name.
	 * 
	 * @param courseName
	 * @return
	 */
	public Course findCourseByName(String courseName);

	/**
	 * Return the sessions currently created for this Course.
	 * 
	 * @param course
	 * @return empty list by default
	 */
	public List<CourseSession> getSessions(Course course)
			throws CourseManagementException;

	/**
	 * Register a faculty member to deliver a particular session of a
	 * {@link Course}.
	 * 
	 * @param faculty
	 *            a faculty instance who will be instructing the course
	 * @param session
	 *            a session of a Course
	 * @return updated {@link CourseSession} instance
	 * @throws CourseManagementException
	 */
	public CourseSession registerFaculty(Faculty faculty, CourseSession session)
			throws CourseManagementException;

	/**
	 * Removes an existing Course from the course catalog (course table).
	 * 
	 * @param course
	 */
	public void removeCourseFromCatalog(String courseName);

	/**
	 * Remove the CourseSession and unregister all students in the process.
	 * 
	 * @param session
	 * @throws CourseManagementException
	 */
	public void removeSession(CourseSession session)
			throws CourseManagementException;

	/**
	 * Unregister a faculty from a session of the course.
	 * 
	 * @param faculty
	 * @param session
	 * @return updated CourseSession
	 * @throws CourseManagementException
	 */
	public CourseSession unregisterFaculty(Faculty faculty,
			CourseSession session) throws CourseManagementException;

	/**
	 * Updates a Course's information in the course catalog.
	 * 
	 * @param course
	 */
	public void updateCourseInfo(Course course);
}
