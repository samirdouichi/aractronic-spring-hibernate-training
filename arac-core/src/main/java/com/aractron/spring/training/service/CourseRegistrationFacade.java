package com.aractron.spring.training.service;

import com.aractron.spring.training.domain.CourseSession;
import com.aractron.spring.training.domain.Student;
import com.aractron.spring.training.exception.CourseRegistrationException;

/**
 * Service layer interface for {@link Course} registration activities.
 * 
 * @author aaron.levensailor
 */
public interface CourseRegistrationFacade {
	
	/**
	 * Register student into course.
	 * 
	 * @param student
	 *            the student enrolling in the course session
	 * @param session
	 *            the course session
	 * @return the updated {@link CourseSession} instance
	 */
	public CourseSession registerStudent(Student student, CourseSession session)
			throws CourseRegistrationException;

	/**
	 * Register student into course.
	 * 
	 * @param student
	 *            the student enrolling in the course session
	 * @param courseSessionId
	 *            the course session identifier
	 * @return the updated {@link CourseSession} instance
	 */
	public CourseSession registerStudent(Student student, Long courseSessionId)
			throws CourseRegistrationException;

	/**
	 * Unregister student from course.
	 * 
	 * @param student
	 *            the student un-enrolling from the course session
	 * @param session
	 *            the course session
	 * @return the updated {@link CourseSession} instance
	 */
	public CourseSession unregisterStudent(Student student,
			CourseSession session) throws CourseRegistrationException;

	/**
	 * Unregister student from course.
	 * 
	 * @param student
	 *            the student un-enrolling from the course session
	 * @param courseSessionId
	 *            the course session identifier
	 * @return the updated {@link CourseSession} instance
	 */
	public CourseSession unregisterStudent(Student student, Long courseSessionId)
			throws CourseRegistrationException;

}
