package com.aractron.spring.training.labs.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.aractron.spring.training.dao.FacultyDao;
import com.aractron.spring.training.dao.StudentDao;
import com.aractron.spring.training.domain.Faculty;
import com.aractron.spring.training.domain.Student;
import com.aractron.spring.training.domain.StudentClassification;
import com.aractron.spring.training.exception.CourseRegistrationException;

/**
 * Main application for the AOP lab. Attempts to create a new Faculty and Student
 * and save them in the DB while changing their usernames and outputting log 
 * statements to the console on the insert() method calls.
 */
public class SpringAopLabMain {
	public static void main(String[] args) throws CourseRegistrationException {

		ApplicationContext ac = new ClassPathXmlApplicationContext(
				new String[] { "/com/aractron/spring/training/labs/aop/aop-beans-config.xml" });
		
		FacultyDao fDao = ac.getBean("facultyDao", FacultyDao.class);
		StudentDao sDao = ac.getBean("studentDao", StudentDao.class);

		// Create a new faculty member and save them in the DB.
		Faculty f = new Faculty();
		f.setUserName("knowItAll");
		f.setPassword("allKnow");
		f.setFirstName("Smarty");
		f.setLastName("Pants");
		f.setEmail("sPants@testchool.com");
		f.setPhoneNumber("1234567890");
		f.setDepartment("Humanities");

		fDao.insert(f);

		Faculty anotherFaculty = fDao.findByUsernameAndPassword(f.getUserName(),f.getPassword());
		
		if (f.getFirstName().equals(anotherFaculty.getFirstName())) {
			System.out.println("The faculty's first name matches was what inserted!!!");
			System.out.println("Username: " + anotherFaculty.getUserName());
		}
				

		// Create a new student and save them in the DB
		Student s = new Student();
		s.setUserName("padawan");
		s.setPassword("young");
		s.setFirstName("Luke");
		s.setLastName("skywalker");
		s.setEmail("lSkywalk@testchool.com");
		s.setPhoneNumber("1234567890");
		s.setClassification(StudentClassification.SOPHOMORE);
		s.setRegisteredCourses(null);

		sDao.insert(s);

		Student anotherStudent = sDao.findByUsernameAndPassword(s.getUserName(),s.getPassword());

		if (s.getFirstName().equals(anotherStudent.getFirstName())) {
			System.out.println("The student's first name matches was what inserted!!!");
			System.out.println("Username: " + anotherStudent.getUserName());
		}
	}
}
