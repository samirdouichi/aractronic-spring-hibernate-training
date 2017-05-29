package com.aractron.spring.training.hibernate.junit.dao;

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
import com.aractron.spring.training.domain.hibernate.HibernateAnnotationCourse;
import com.aractron.spring.training.domain.hibernate.StudentClassificationUserType;

/**
 * Test class that verifies the functionality of the CourseDao interface being 
 * implemented by the Hibernate versions of the DaoImplementations.
 * 
 * @author vanessa.ortiz
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
// TODO: CS7_LAB4_1.2.1 - Change the context config location to use the annotation based configuration file.
@ContextConfiguration(locations="classpath:/com/aractron/spring/training/labs/hibernate/hibernateLab-bean-xml-config.xml")
public class CourseDaoHibernateTest {

	@Autowired
	private CourseDao courseDao;
	
	private Course course;
	private List<Course> courseList;
	
	// TODO: CS7_LAB2_1.1.1.1 - Create a new test method called testFindAllCourses() that tests the CourseDao's
	// findAllCourses() method.
	
	// TODO: CS7_LAB2_1.1.1.2 - Create a new test method called testFindCourseByName() that tests the CourseDao's
	// findCourseByName() method.

	
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
		
		// TODO: CS7_LAB4_1.2.3 - Create a new Hibernate version of the Course domain object and assign it to the course variable instead of
		// using the regular Course domain object.
		
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
