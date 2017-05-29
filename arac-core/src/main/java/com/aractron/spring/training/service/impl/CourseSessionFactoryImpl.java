package com.aractron.spring.training.service.impl;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.aractron.spring.training.domain.Course;
import com.aractron.spring.training.domain.CourseSession;
import com.aractron.spring.training.service.CourseSessionFactory;

/**
 * Implementation of CourseSessionFactory that leverages the Spring application
 * context.
 * 
 * @author aaron.levensailor
 */
public class CourseSessionFactoryImpl implements CourseSessionFactory,
		ApplicationContextAware {
	
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
