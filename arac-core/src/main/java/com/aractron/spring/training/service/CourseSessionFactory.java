package com.aractron.spring.training.service;

import com.aractron.spring.training.domain.Course;
import com.aractron.spring.training.domain.CourseSession;

/**
 * Utility class to generate new CourseSession objects. Requires a Spring
 * configuration where there is a prototype scoped bean definition of the
 * CourseSession class.
 * 
 * @author aaron.levensailor
 */
public interface CourseSessionFactory {
	/**
	 * Return a new instance of a CourseSession
	 * 
	 * @param course
	 *            the Course to create a new session for
	 * @return null by default
	 */
	public CourseSession createSession(Course course);
}
