package com.aractron.spring.training.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.aractron.spring.training.dao.FacultyDao;
import com.aractron.spring.training.dao.StudentDao;
import com.aractron.spring.training.domain.Faculty;
import com.aractron.spring.training.domain.Student;

/**
 * Implementation of User information management methods to demonstrate the value of transactional
 * demarcation of methods i.e. at method level.
 */
public class UserInfoTxTestImpl {
	private static Log log = LogFactory.getLog(UserInfoTxTestImpl.class);
	private StudentDao studentDao;
	private FacultyDao facultyDao;

	public UserInfoTxTestImpl() {
	}

	@Transactional(isolation = Isolation.SERIALIZABLE)
	public void insertFaculty(Faculty faculty) {
		facultyDao.insert(faculty);
		log.info("Insert SQL will fail");
		throw new RuntimeException("forced");
	}
	
	//Add the transactional annotation for lab3
	public void insertStudent(Student user) {
		studentDao.insert(user);
		log.info("Insert SQL will fail");
		throw new RuntimeException("forced");
	}

	/**
	 * Custom implementation of FacultyDao.update() to demonstrate transactions.
	 * 
	 * @param user
	 *            faculty record to update
	 */
	@Transactional(isolation = Isolation.SERIALIZABLE)
	public void updateFaculty(Faculty faculty) {
		facultyDao.update(faculty);
		log.info("Update SQL will fail");
		throw new RuntimeException("forced");
	}

	/**
	 * Custom implementation of StudentDao.update() to demonstrate transactions.
	 * 
	 * @param user
	 *            student record to update
	 */

	//Add the transactional annotation for lab3
	public void updateStudent(Student student) {
		studentDao.update(student);
		log.info("Update SQL will fail");
		throw new RuntimeException("forced");
	}

	public StudentDao getStudentDao() {
		return studentDao;
	}

	public void setStudentDao(StudentDao studentDao) {
		this.studentDao = studentDao;
	}

	public FacultyDao getFacultyDao() {
		return facultyDao;
	}

	public void setFacultyDao(FacultyDao facultyDao) {
		this.facultyDao = facultyDao;
	}

}
