package com.aractron.spring.training.labs.jdbc;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.aractron.spring.training.dao.CourseDao;
import com.aractron.spring.training.dao.CourseSessionDao;
import com.aractron.spring.training.dao.FacultyDao;
import com.aractron.spring.training.dao.StudentDao;
import com.aractron.spring.training.domain.Course;
import com.aractron.spring.training.domain.CourseSession;
import com.aractron.spring.training.domain.Faculty;
import com.aractron.spring.training.domain.Student;
import com.aractron.spring.training.domain.StudentClassification;
import com.aractron.spring.training.exception.CourseRegistrationException;

/**
 * Main class that performs various CRUD functionality on the Student, Faculty, Course 
 * and CourseSession objects to verify functionality.
 *
 * @author vanessa.ortiz
 */
public class SpringJDBCLabMain {
	@SuppressWarnings("unused")
	public static void main(String[] args) throws CourseRegistrationException {

		ApplicationContext ac = new ClassPathXmlApplicationContext(
				new String[] { "/com/aractron/spring/training/labs/jdbc/jdbc-beans-config.xml" });
		
		FacultyDao facultyDao = ac.getBean("facultyDao", FacultyDao.class);
		CourseDao courseDao = ac.getBean("courseDao", CourseDao.class);
		
		
		// Create a new faculty member and save them in the DB.
		Faculty f = new Faculty();
		f.setUserName("knowItAll");
		f.setPassword("allKnow");
		f.setFirstName("Smarty");
		f.setLastName("Pants");
		f.setEmail("sPants@testchool.com");
		f.setPhoneNumber("1234567890");
		f.setDepartment("Humanities");

		facultyDao.insert(f);

		Faculty anotherFaculty = facultyDao.findByUsernameAndPassword("knowItAll","allKnow");
		List<Faculty> facultyList = facultyDao.findAll();
		
		if(anotherFaculty != null){
			if (f.getFirstName().equals(anotherFaculty.getFirstName())) {
				System.out.println("The faculty's first name matches what was inserted!!!");
				System.out.println("Username: " + anotherFaculty.getUserName());
			}
		}
		else{
			System.out.println("ERROR - Retrieve of recently inserted faculty failed!");
		}
				
		// Create a new course and save it in the DB
		Course course = new Course();
		course.setCreditHours(new Double(3.66));
		course.setDepartment("Arts");
		course.setDescription("Learn how to take great pictures.");
		course.setName("Photography 201");
		course.setLevel(StudentClassification.JUNIOR);
		
		courseDao.insert(course);
		
		Course anotherCourse = courseDao.findCourseByName("Photography 201");
		
		if(anotherCourse != null){
			if(course.getDepartment().equals(anotherCourse.getDepartment())){
				System.out.println("The course's department name matches what was inserted!!! ");
				System.out.println("Department name: " + anotherCourse.getDepartment());
			}
		}
		else{
			System.out.println("ERROR - Retrieve of recently inserted course failed!");
		}
		
		anotherCourse.setDescription("Anothwer new description");
		courseDao.update(anotherCourse);
		anotherCourse = null;
		anotherCourse = courseDao.findCourseByName("Photography 201");
		
		if(anotherCourse != null){
			if("Anothwer new description".equals(anotherCourse.getDescription())){
				System.out.println("The course's description matches the update!!! ");
				System.out.println("Description name: " + anotherCourse.getDescription());
			}
		}
		else{
			System.out.println("ERROR - Retrieve of recently inserted course failed!");
		}
		
		//Test the delete method of CourseDaoJdbcImplExample.java class
		Course deleteCourse = courseDao.findCourseByName("Photography 201");
		if(deleteCourse != null){
			if(course.getName().equals(deleteCourse.getName())){
				System.out.println("the course names matched");
				courseDao.delete(deleteCourse);
			}
		}else{
			System.out.println("ERROR - Retrieve of recently updated course failed!");
		}
		
		if(courseDao.findCourseByName("Photography 201") != null){
			System.out.println("delete method didn't work");
		}else{
			System.out.println("Hurray! delete method worked");
		}
		
	}
}
