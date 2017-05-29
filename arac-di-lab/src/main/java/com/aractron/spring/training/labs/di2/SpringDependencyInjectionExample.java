package com.aractron.spring.training.labs.di2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.aractron.spring.training.domain.CourseSession;
import com.aractron.spring.training.domain.Student;
import com.aractron.spring.training.exception.CourseRegistrationException;
import com.aractron.spring.training.service.CourseRegistrationFacade;
import com.aractron.spring.training.service.UserFacade;

// This example demonstrates Spring DI
public class SpringDependencyInjectionExample {
	public static void main(String[] args) throws CourseRegistrationException {
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				new String[] { "/com/aractron/spring/training/labs/di2/beans-config.xml" });

		@SuppressWarnings("unused")
		UserFacade userFacade = ac.getBean("userFacade", UserFacade.class);
		CourseRegistrationFacade registrar = ac.getBean("registrar",
				CourseRegistrationFacade.class);
		
		// Student: find user 'jyotsna' (password: 'password') using userFacade.findUser()
		Student student = null;
		// Student: obtain the 'innerBeans/session1' CourseSession from the Application Context
		CourseSession session = null;
		// register 'jyotsna' user for this course
		registrar.registerStudent(student, session);
	}
}
