package com.aractron.spring.training.jdbc.junit.dao;

import java.util.List;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.aractron.spring.training.dao.FacultyDao;
import com.aractron.spring.training.domain.Faculty;

/**
 * Test class that verifies the functionality of the FacultyDao interface being implemented
 * by the Hibernate versions of the DaoImplementations. 
 * 
 * @author vanessa.ortiz
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(locations="classpath:/com/aractron/spring/training/labs/jdbc/jdbc-beans-config.xml")
public class FacultyDaoJdbcExampleTest {
	
	@Autowired
	private FacultyDao facultyDao;
	
	private Faculty faculty;
	private List<Faculty> facultyList;
	
	@Test
	public void testFindById(){

		faculty = facultyDao.findById(1l);
		Assert.assertNotNull(faculty);
		Assert.assertEquals(1l,faculty.getId().longValue());
		Assert.assertEquals("teach1", faculty.getUserName());
	}
	
	@Test
	public void testFindByUsernameAndPassword(){
		
		faculty = facultyDao.findByUsernameAndPassword("teach1","pass1");
		Assert.assertNotNull(faculty);
		Assert.assertEquals("teach1", faculty.getUserName());
	}
	
	@Test
	public void testFindAllFacultyByDepartment(){
		
		facultyList = facultyDao.findAllFacultyByDepartment("English");
		Assert.assertNotNull(facultyList);
		Assert.assertFalse(facultyList.isEmpty());
		Assert.assertEquals("English", facultyList.get(0).getDepartment());		
	}
	
	@Test
	public void testFindAll(){
		
		facultyList = facultyDao.findAll();
		Assert.assertNotNull(facultyList);
		Assert.assertFalse(facultyList.isEmpty());		
	}
	
	@Test
	public void testInsert(){

		faculty = new Faculty();
		faculty.setUserName("kingOfPop");
		faculty.setPassword("shamone");
		faculty.setFirstName("Micheal");
		faculty.setLastName("Jackson");
		faculty.setEmail("billyJ@xhy.com");
		faculty.setPhoneNumber("1234567");
		faculty.setDepartment("Arts");
		
		facultyDao.insert(faculty);
		Faculty localFaculty = facultyDao.findByUsernameAndPassword("kingOfPop", "shamone");
		
		Assert.assertNotNull(localFaculty);
		Assert.assertEquals(faculty.getUserName(), localFaculty.getUserName());
	}

}
