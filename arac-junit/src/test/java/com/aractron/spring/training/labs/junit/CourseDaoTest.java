package com.aractron.spring.training.labs.junit;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.aractron.spring.training.dao.CourseDao;
import com.aractron.spring.training.domain.Course;
import com.aractron.spring.training.domain.Student;

// Students to create a test class and configuration file to test the CourseDaoJDBCImpl class.
// Step 1 - mark this as a Spring JUnit4 test
// Step 2 - mark this as a 'context configuration' class
@SuppressWarnings("unused")
public class CourseDaoTest {
	@Autowired
	private CourseDao courseDao;

	// step 3 - write a test method for one 'finder' and one 'insert' method
	// participants to write test to find course by id '1'
	public void testFindCourseById() {
		Course course = null;
		assertNotNull(course);
	}

	@SuppressWarnings({ "deprecation", "null" })
	@Transactional
	// Participant to write the test for CourseDao.updateCourse()
	public void testUpdateCourse() {
		Course course = null;
		Course anotherCourse = courseDao.findCourseByName(course.getName());
	}

}
