package com.aractron.spring.training.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.aractron.spring.training.domain.Course;
import com.aractron.spring.training.domain.CourseSession;

/**
 * Utility class to generate new CourseSession objects. Requires a Spring
 * configuration where there is a prototype scoped bean definition of the
 * CourseSession class.
 * 
 * @author aaron.levensailor
 */
public class CourseSessionFactory implements ApplicationContextAware {
	
	/**
	 * Class instance of Spring's ApplicationContext interface
	 */
	private ApplicationContext context;

	/**
	 * Method that creates a CourseSession bean.
	 * 
	 * @param course The course to set within the CourseSession.
	 * @return A CourseSession object.
	 */
	public CourseSession createSession(Course course) {
		CourseSession session = context.getBean(CourseSession.class);
		session.setCourse(course);
		return session;
	}

	/**
	 * Setter for context.
	 * 
	 * @param applicationContext The context to set.
	 */
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.context = applicationContext;
	}
}
