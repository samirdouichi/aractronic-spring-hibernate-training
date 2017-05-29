package com.aractron.spring.training.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.aractron.spring.training.dao.FacultyDao;
import com.aractron.spring.training.dao.StudentDao;
import com.aractron.spring.training.domain.Faculty;
import com.aractron.spring.training.domain.Student;
import com.aractron.spring.training.domain.User;
import com.aractron.spring.training.service.UserFacade;

/**
 * User management object that leverages the DAO layer.
 * 
 * @author aaron.levensailor
 */
public class UserFacadeDaoImpl implements UserFacade {
	
	/**
	 * Class instance of the StudentDao interface
	 */
	@Autowired
	private StudentDao studentDao;
	
	/**
	 * Class instance of the FacultyDao interface
	 */
	@Autowired
	private FacultyDao facultyDao;

	/**
	 * {@inheritDoc}
	 */
	public User findUser(String userName, String password) {
		User user = null;
		Student students = studentDao.findByUsernameAndPassword(
				userName, password);
		if (students == null ) {
			user = facultyDao.findByUsernameAndPassword(userName, password);
		} else {
			user = students;
		}
		return user;
	}
	
	/**
	 * {@inheritDoc}
	 */
	public User saveUser(User user) {
		User newUser = null;
		if (user.getId() == 0) {
			if (user instanceof Student) {
				studentDao.insert((Student) user);
			} else {
				facultyDao.insert((Faculty) user);
			}
			newUser = findUser(user.getUserName(), user.getPassword());
		} else {
			if (user instanceof Student) {
				studentDao.update((Student) user);
			} else {
				facultyDao.update((Faculty) user);
			}
			newUser = user;
		}
		return newUser;
	}

	/**
	 * Getter for facultyDao.
	 * 
	 * @return the facultyDao
	 */
	public FacultyDao getFacultyDao() {
		return facultyDao;
	}

	/**
	 * Getter for studentDao.
	 * 
	 * @return the studentDao.
	 */
	public StudentDao getStudentDao() {
		return studentDao;
	}

	/**
	 * Setter for facultyDao.
	 * 
	 * @param facultyDao The facultyDao to set.
	 */
	public void setFacultyDao(FacultyDao facultyDao) {
		this.facultyDao = facultyDao;
	}

	/**
	 * Setter for studentDao.
	 * 
	 * @param studentDao The studentDao to set.
	 */
	public void setStudentDao(StudentDao studentDao) {
		this.studentDao = studentDao;
	}

}
