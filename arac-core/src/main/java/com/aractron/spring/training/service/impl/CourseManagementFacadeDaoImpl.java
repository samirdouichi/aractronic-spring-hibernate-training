package com.aractron.spring.training.service.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.aractron.spring.training.dao.CourseDao;
import com.aractron.spring.training.dao.CourseSessionDao;
import com.aractron.spring.training.domain.Course;
import com.aractron.spring.training.domain.CourseSession;
import com.aractron.spring.training.domain.Faculty;
import com.aractron.spring.training.exception.CourseManagementException;
import com.aractron.spring.training.service.CourseManagementFacade;
import com.aractron.spring.training.service.CourseSessionFactory;

/**
 * Implementation of CourseManagementFacade that leverages DAO 
 * objects to persist the changes.
 * 
 * @author aaron.levensailor
 */
public class CourseManagementFacadeDaoImpl implements CourseManagementFacade {
	
	/**
	 * Class instance of the CourseDao interface.
	 */
	@Autowired
	private CourseDao courseDao;
	
	/**
	 * Class instance of the CourseSessiondao interface.
	 */
	@Autowired
	private CourseSessionDao courseSessionDao;
	
	/**
	 * Class instance of the CourseSessionFactory interface.
	 */
	@Autowired
	private CourseSessionFactory courseSessionFactory;

	/**
	 * Saves a new Course to the course catalog (course table).
	 * 
	 * @param course
	 */
	public void addNewCourseIntoCatalog(Course course) {
		courseDao.insert(course);
	}

	/**
	 * {@inheritDoc}
	 */
	public CourseSession addSession(Course course, String term)
			throws CourseManagementException {
		// Simplified example of a business validation rule.
		List<String> validTerms = Arrays.asList(new String[] { "spring",
				"summer", "fall", "winter" });
		if (term == null || !validTerms.contains(term.toLowerCase())) {
			throw new CourseManagementException(
					"CourseSessions must contain one of the following terms: "
							+ validTerms);
		}
		CourseSession tmp = courseSessionFactory.createSession(course);
		tmp.setTerm(term);
		// perform the insert
		courseSessionDao.insert(tmp);
		// and then retrieve to obtain a CourseSession with an ID
		CourseSession session = courseSessionDao.findCourseSessionBySession(tmp
				.getCourse().getName(), tmp.getTerm());
		return session;
	}

	/**
	 * {@inheritDoc}
	 */
	public Collection<Course> findAllCourses() {
		return courseDao.findAllCourses();
	}

	/**
	 * {@inheritDoc}
	 */
	public Course findCourseByName(String courseName) {
		return courseDao.findCourseByName(courseName);
	}

	/**
	 * {@inheritDoc}
	 */
	public CourseSession registerFaculty(Faculty faculty, CourseSession session)
			throws CourseManagementException {
		session.setFaculty(faculty);
		courseSessionDao.update(session);
		return session;
	}

	/**
	 * Removes an existing Course from the course catalog (course table).
	 * 
	 * @param course
	 */
	public void removeCourseFromCatalog(String courseName) {
		Course course = new Course();
		course.setName(courseName);
		courseDao.delete(course);
	}

	/**
	 * {@inheritDoc}
	 */
	public void removeSession(CourseSession session)
			throws CourseManagementException {
		courseSessionDao.delete(session);
	}

	/**
	 * {@inheritDoc}
	 */
	public CourseSession unregisterFaculty(Faculty faculty,
			CourseSession session) throws CourseManagementException {
		session.setFaculty(null);
		courseSessionDao.update(session);
		return session;
	}

	/**
	 * {@inheritDoc}
	 */
	public void updateCourseInfo(Course course) {
		courseDao.update(course);
	}	

	/**
	 * {@inheritDoc}
	 */
	public List<CourseSession> getSessions(Course course)
			throws CourseManagementException {
		return courseSessionDao.findAllCourseSessions(course);
	}
	
	/**
	 * Setter for courseDao.
	 * 
	 * @param courseDao
	 *            the courseDao to set
	 */
	public void setCourseDao(CourseDao courseDao) {
		this.courseDao = courseDao;
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
	 * Setter for cocourseSessionFactory.
	 * 
	 * @param courseSessionFactory The courseSessionFactory to set.
	 */
	public void setCourseSessionFactory(
			CourseSessionFactory courseSessionFactory) {
		this.courseSessionFactory = courseSessionFactory;
	}


	/**
	 * Getter for courseDao.
	 * 
	 * @return the courseDao
	 */
	public CourseDao getCourseDao() {
		return courseDao;
	}

	/**
	 * Getter for courseSessionDao.
	 * 
	 * @return the courseSessionDao
	 */
	public CourseSessionDao getCourseSessionDao() {
		return courseSessionDao;
	}

	/**
	 * Getter for courseSessionFactory.
	 * 
	 * @return courseSessionFactory
	 */
	public CourseSessionFactory getCourseSessionFactory() {
		return courseSessionFactory;
	}

}
