package com.aractron.spring.training.junit.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.aractron.spring.training.dao.FacultyDao;
import com.aractron.spring.training.domain.Faculty;

/**
 * Test class for the FacultyDao interface. The implementation being tested is the one found in the 
 * Core project: FacultyDaoJDBCImpl
 * 
 * @author vanessa.ortiz
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:com/aractron/spring/training/dao/impl/dao-bean-config.xml")
public class FacultyDaoTest {

	@Autowired
	private FacultyDao facultyDao;
	
	private Faculty faculty;
	private List<Faculty> facultyList;
	

	@Test
	public void testFindById(){
		faculty = facultyDao.findById(2l);
		Assert.assertNotNull(faculty);
		Assert.assertSame(2l,faculty.getId());
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
		faculty.setUserName("imBad");
		faculty.setPassword("shamone");
		faculty.setFirstName("Micheal");
		faculty.setLastName("Jackson");
		faculty.setEmail("billyJ@xhy.com");
		faculty.setPhoneNumber("1234567");
		faculty.setDepartment("Arts");
		
		facultyDao.insert(faculty);
		Faculty localFaculty = facultyDao.findByUsernameAndPassword("imBad", "shamone");
		
		Assert.assertNotNull(localFaculty);
		Assert.assertEquals(faculty.getUserName(), localFaculty.getUserName());
	}
}
