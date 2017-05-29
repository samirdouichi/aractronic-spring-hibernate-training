package com.aractron.spring.training.service.impl;

import com.aractron.spring.training.domain.CourseSession;
import com.aractron.spring.training.domain.Student;
import com.aractron.spring.training.exception.CourseRegistrationException;
import com.aractron.spring.training.service.CourseRegistrationFacade;

/**
 * Basic implementation of {@link CourseRegistrationFacade}.
 * 
 */
public class CourseRegistrationFacadeBasicImpl implements
		CourseRegistrationFacade {

	@Override
	public CourseSession registerStudent(Student student, CourseSession session)
			throws CourseRegistrationException {
		student.getRegisteredCourses().add(session);
		session.getStudents().add(student);
		System.out.println("Registered " + student.getUserName() + " for "
				+ session.getCourse().getName() + " " + session.getTerm()
				+ " term");
		return session;
	}

	@Override
	public CourseSession unregisterStudent(Student student,
			CourseSession session) throws CourseRegistrationException {
		student.getRegisteredCourses().remove(session);
		session.getStudents().remove(student);
		System.out.println("Unregistered " + student.getUserName() + " for "
				+ session.getCourse().getName());
		return null;
	}

	@Override
	public CourseSession registerStudent(Student student, Long courseSessionId)
			throws CourseRegistrationException {
		return null;
	}

	@Override
	public CourseSession unregisterStudent(Student student, Long courseSessionId)
			throws CourseRegistrationException {
		return null;
	}

}
