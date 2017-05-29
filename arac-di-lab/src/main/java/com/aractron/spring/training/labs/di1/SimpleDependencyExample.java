package com.aractron.spring.training.labs.di1;

import com.aractron.spring.training.domain.CourseSession;
import com.aractron.spring.training.domain.Student;
import com.aractron.spring.training.exception.CourseRegistrationException;
import com.aractron.spring.training.service.CourseRegistrationFacade;
import com.aractron.spring.training.service.impl.CourseRegistrationFacadeBasicImpl;

//This example does not use DI
public class SimpleDependencyExample {
	public static void main(String[] args) throws CourseRegistrationException {
		CourseRegistrationFacade registrar = new CourseRegistrationFacadeBasicImpl();
		Student student1 = new Student();
		Student student2 = new Student();

		// TODO: give student1 and student2 distinct names
		CourseSession session = new CourseSession();
		registrar.registerStudent(student1, session);
		
		System.out.println(session);
		registrar.registerStudent(student2, session);
		System.out.println(session);
	}
}
