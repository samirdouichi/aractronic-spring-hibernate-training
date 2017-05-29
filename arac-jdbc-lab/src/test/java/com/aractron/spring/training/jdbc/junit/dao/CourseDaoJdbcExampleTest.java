package com.aractron.spring.training.jdbc.junit.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.aractron.spring.training.dao.CourseDao;
import com.aractron.spring.training.domain.Course;
import com.aractron.spring.training.domain.StudentClassification;

/**
 * Test class that verifies the functionality of the CourseDao interface being implemented
 * by the Hibernate versions of the DaoImplementations. This is the test class that 
 * students will modify by creating a couple of test classes for the DaoImpl methods
 * that they wrote.
 * 
 * @author vanessa.ortiz
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(locations="classpath:/com/aractron/spring/training/labs/jdbc/jdbc-beans-config.xml")
public class CourseDaoJdbcExampleTest {

	@Autowired
	private CourseDao courseDao;
	
	private Course course;
	private List<Course> courseList;
	
	@Test
	public void testFindAllCourses() {

		courseList = courseDao.findAllCourses();
		Assert.assertNotNull(courseList);
		Assert.assertFalse(courseList.isEmpty());
	}
	
	@Test
	public void testFindCourseByName(){
		
		course = courseDao.findCourseByName("Spring 101");
		Assert.assertNotNull(course);
		Assert.assertTrue("Spring 101".equals(course.getName()));
	}
	
	@Test
	public void testFindCoursesByDepartment(){
		
		courseList = courseDao.findCoursesByDepartment("English");
		Assert.assertNotNull(courseList);
		Assert.assertFalse(courseList.isEmpty());
		Assert.assertEquals("English", courseList.get(0).getDepartment());
	}
	
	@Test
	public void testFindCourseById(){
		
		course = courseDao.findCourseById(3L);
		Assert.assertNotNull(course);
		Assert.assertEquals(3L, course.getId().longValue());
	}
	
	@Test
	public void testInsert(){
		
		course = new Course();
		course.setCreditHours(2.5);
		course.setDepartment("Humanities");
		course.setDescription("Learn to philosofize");
		course.setLevel(StudentClassification.SOPHOMORE);
		course.setName("Philosophy 222");
		courseDao.insert(course);
		
		Course localCourse = courseDao.findCourseByName(course.getName());
		Assert.assertNotNull(localCourse);
		Assert.assertEquals(course.getName(), localCourse.getName());
	}
	
	@Test
	public void testUpdate(){
		
		course = courseDao.findCourseById(3L);
		Assert.assertNotNull(course);
		
		course.setDescription("Another descrption");
		courseDao.update(course);
		
		Course localCourse = courseDao.findCourseById(3L);
		Assert.assertNotNull(localCourse);
		Assert.assertEquals(3L, localCourse.getId().longValue());
		Assert.assertEquals("Another descrption", localCourse.getDescription());
	}
	
}
