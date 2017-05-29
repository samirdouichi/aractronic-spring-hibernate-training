package com.aractron.spring.training.labs.di1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.aractron.spring.training.domain.CourseSession;
import com.aractron.spring.training.domain.Student;
import com.aractron.spring.training.exception.CourseRegistrationException;
import com.aractron.spring.training.service.CourseRegistrationFacade;

// This example introduces Spring DI and bean scope
public class SpringDependencyExample {
	public static void main(String[] args) throws CourseRegistrationException {
		ApplicationContext ac = new ClassPathXmlApplicationContext(
				new String[] { "/com/aractron/spring/training/labs/di1/beans-config.xml" });

		CourseRegistrationFacade registrar = ac.getBean("registrar",
				CourseRegistrationFacade.class);
		Student student1 = (Student) ac.getBean("student");
		Student student2 = ac.getBean(Student.class);
		// TODO: give student1 and student2 distinct names
		
		CourseSession session = (CourseSession) ac.getBean("session");
		// call registrar.registerStudent and register student 1 into the above course session.
		System.out.println(session);
		// call registrar.registerStudent and register student 2 into the above course session.
		System.out.println(session);
	}
}
