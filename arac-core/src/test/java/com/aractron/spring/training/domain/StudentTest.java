package com.aractron.spring.training.domain;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.aractron.spring.training.domain.Course;
import com.aractron.spring.training.domain.CourseSession;
import com.aractron.spring.training.domain.Student;

/**
 * Test class to verify the Student registration functionality.
 *  
 * @author aaron.levensailor
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SuppressWarnings("unused")
@ContextConfiguration
public class StudentTest {
	
	@Autowired
	private Student student;
	@Autowired
	private Course course;
	@Autowired
	private CourseSession session;

	/**
	 * Method that tests whether or not a student is correctly registered and if 
	 * changes are made to the registered courses, that they be made correctly.
	 */
	@Test
	public void testRegistrationChanges() {
		assertNotNull(student);
		assertNotNull(session);
		assertEquals(0, student.getRegisteredCourses().size());
		student.getRegisteredCourses().add(session);
		session.getStudents().add(student);
		assertEquals(1, student.getRegisteredCourses().size());
		for (CourseSession cs : student.getRegisteredCourses()) {
			assertEquals(session, cs);
		}
		assertTrue(student.getRegisteredCourses().contains(session));
		assertTrue(session.getStudents().contains(student));
		student.getRegisteredCourses().remove(session);
		session.getStudents().remove(student);
		assertEquals(0, student.getRegisteredCourses().size());
		assertFalse(student.getRegisteredCourses().contains(session));
		assertFalse(session.getStudents().contains(student));
	}
}
