package com.aractron.spring.training.labs.transactions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.aractron.spring.training.dao.FacultyDao;
import com.aractron.spring.training.dao.StudentDao;
import com.aractron.spring.training.domain.Faculty;
import com.aractron.spring.training.domain.Student;
import com.aractron.spring.training.service.impl.UserInfoTxTestImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class AnnotationTransactionalTest {
	private static Log log = LogFactory
			.getLog(AnnotationTransactionalTest.class);
	@Autowired
	private FacultyDao facultyDao;
	@Autowired
	private StudentDao studentDao;
	@Autowired
	private UserInfoTxTestImpl service;

	@Test
	public void testInsertFaculty() {
		Faculty faculty = new Faculty();
		faculty.setUserName("tx_faculty");
		faculty.setPassword("pass");
		faculty.setFirstName("Tx");
		Exception expected = null;
		try {
			service.insertFaculty(faculty);
		} catch (RuntimeException ex) {
			expected = ex;
			log.info(ex);
		}
		assertNotNull(expected);
		Faculty _faculty = facultyDao.findByUsernameAndPassword(
				faculty.getUserName(), faculty.getPassword());
		assertNull(_faculty);
	}

	@Test
	public void testUpdateFaculty() {
		Faculty faculty = facultyDao.findByUsernameAndPassword("teach1",
				"pass1");
		assertNotNull(faculty);
		String originalFirstName = faculty.getFirstName();
		faculty.setFirstName("transaction");
		Exception expected = null;
		try {
			service.updateFaculty(faculty);
		} catch (RuntimeException ex) {
			expected = ex;
			log.info(ex);
		}
		assertNotNull(expected);
		Faculty _faculty = facultyDao.findByUsernameAndPassword("teach1",
				"pass1");
		assertEquals(originalFirstName, _faculty.getFirstName());
	}

	@Test
	public void testInsertStudent() {
		Student student = new Student();
		student.setUserName("tx_faculty");
		student.setPassword("pass");
		student.setFirstName("Tx");
		Exception expected = null;
		try {
			service.insertStudent(student);
		} catch (RuntimeException ex) {
			expected = ex;
		}
		assertNotNull(expected);
		Student _student = studentDao.findByUsernameAndPassword(
				student.getUserName(), student.getPassword());
		assertNull(_student);
	}

	@Test
	public void testUpdateStudent() {
		Student student = studentDao.findByUsernameAndPassword("test", "test");
		String originalFirstName = student.getFirstName();
		student.setFirstName("transaction");
		Exception expected = null;
		try {
			service.updateStudent(student);
		} catch (RuntimeException ex) {
			expected = ex;
			log.info(ex);
		}
		assertNotNull(expected);
		Student _student = studentDao.findByUsernameAndPassword("test", "test");

		assertEquals(originalFirstName, _student.getFirstName());
	}
}
