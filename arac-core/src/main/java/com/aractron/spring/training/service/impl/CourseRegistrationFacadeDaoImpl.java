package com.aractron.spring.training.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.aractron.spring.training.dao.CourseSessionDao;
import com.aractron.spring.training.dao.StudentDao;
import com.aractron.spring.training.domain.Course;
import com.aractron.spring.training.domain.CourseSession;
import com.aractron.spring.training.domain.Student;
import com.aractron.spring.training.exception.CourseRegistrationException;
import com.aractron.spring.training.service.CourseRegistrationFacade;

/**
 * Service layer interface for {@link Course} registration activities that
 * leverages the DAO layer in its implementation.
 * 
 * @author aaron.levensailor
 */
public class CourseRegistrationFacadeDaoImpl implements
		CourseRegistrationFacade {
	
	/**
	 * Class instance of the CourseSessionDao interface.
	 */
	@Autowired
	private CourseSessionDao courseSessionDao;
	
	/**
	 * Class instance of the StudentDao interface.
	 */
	@Autowired
	private StudentDao studentDao;

	/**
	 * {@inheritDoc}
	 */
	public CourseSession unregisterStudent(Student student,
			CourseSession session) throws CourseRegistrationException {
		try {
			student.getRegisteredCourses().remove(session);
			session.getStudents().remove(student);
			studentDao.unregisterStudent(student, session);
		} catch (Exception ex) {
			throw new CourseRegistrationException("Unable to register {"
					+ student + ", " + session + "}", ex);
		}
		return session;
	}

	/**
	 * {@inheritDoc}
	 */
	public CourseSession unregisterStudent(Student student, Long courseSessionId)
			throws CourseRegistrationException {
		CourseSession session = courseSessionDao
				.findCourseSessionById(courseSessionId);
		return unregisterStudent(student, session);
	}
	
	/**
	 * {@inheritDoc}
	 */
	public CourseSession registerStudent(Student student, CourseSession session)
			throws CourseRegistrationException {
		try {
			student.getRegisteredCourses().add(session);
			session.getStudents().add(student);
			studentDao.registerStudent(student, session);
		} catch (Exception ex) {
			throw new CourseRegistrationException("Unable to register {"
					+ student + ", " + session + "}", ex);
		}
		return session;
	}

	/**
	 * {@inheritDoc}
	 */
	public CourseSession registerStudent(Student student, Long courseSessionId)
			throws CourseRegistrationException {
		CourseSession session = courseSessionDao
				.findCourseSessionById(courseSessionId);
		return registerStudent(student, session);
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
	 * Setter for studentDao.
	 * 
	 * @param studentDao The studentDao to set.
	 */
	public void setStudentDao(StudentDao studentDao) {
		this.studentDao = studentDao;
	}
	
	/**
	 * Setter for courseSessionDao.
	 * 
	 * @param courseSessionDao The courseSessionDao to set.
	 */
	public void setCourseSessionDao(CourseSessionDao courseSessionDao) {
		this.courseSessionDao = courseSessionDao;
	}
	
	/**
	 * Getter for courseSessionDao.
	 * 
	 * @return the courseSessionDao.
	 */
	public CourseSessionDao getCourseSessionDao() {
		return courseSessionDao;
	}
}
