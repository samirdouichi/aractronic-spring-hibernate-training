package com.aractron.spring.training.junit.dao;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.aractron.spring.training.dao.CourseDao;
import com.aractron.spring.training.domain.Course;
import com.aractron.spring.training.domain.StudentClassification;

/**
 * Test class for the CourseDao interface. The implementation being tested is the one found in the 
 * Core project: CourseDaoJDBCImpl
 * 
 * @author vanessa.ortiz
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:com/aractron/spring/training/dao/impl/dao-bean-config.xml")
public class CourseDaoTest {

	@Autowired
	private CourseDao courseDao;

	@Test
	public void testInsert(){
		Course course = new Course();
		
		course.setCreditHours(3.0d);
		course.setDepartment("Humanities");
		course.setDescription("Figure out what goes on in people's heads");
		course.setName("Psychology 201");
		course.setLevel(StudentClassification.GRADUATE);

		courseDao.insert(course);
		
		Course newCourse = courseDao.findCourseByName("Psychology 201");
		Assert.assertSame(course.getName(), newCourse.getName());
	}
	
}
