package com.aractron.spring.training.dao.hibernate.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aractron.spring.training.dao.CourseDao;
import com.aractron.spring.training.domain.Course;

/**
 * This class is a Hibernate implementation of the CourseDao interface that is 
 * configured using annotations.
 * 
 * @author vanessa.ortiz
 */
@Repository
@Transactional
public class CourseDaoHibernateAnnotationImpl implements CourseDao {

	/**
	 * Class instance of the Logger
	 */
	private static Log log = LogFactory.getLog((CourseDaoHibernateAnnotationImpl.class));

	/**
	 * Class instance of Hibernate's SessionFactory
	 */
	@Autowired
	private SessionFactory sessionFactory;


	/**
	 * {@inheritDoc}
	 */
	public List<Course> findAllCourses() {
		
		// TODO: CS7_LAB4_1.0.1.1 - Complete the implementation of this method.
		// Hint - Similar method exists in the FacultyDaoHibernateAnnotationImpl class. 

		return null;
	}

	
	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	public List<Course> findCoursesByDepartment(String departmentName) {
		
		Query q = sessionFactory.getCurrentSession().createQuery("from HibernateAnnotationCourse c where c.department=:department");
		q.setString("department", departmentName);

		List<Course> courseList = q.list();

		return courseList;
	}

	/**
	 * {@inheritDoc}
	 */
	public Course findCourseById(Long id) {
		
		Query q = sessionFactory.getCurrentSession().createQuery("from HibernateAnnotationCourse c where c.id=:id");
		q.setLong("id", id);
		
		Course course = (Course) q.uniqueResult();
		
		return course;
	}

	/**
	 * {@inheritDoc}
	 */
	public Course findCourseByName(String courseName) {
		
		// TODO: CS7_LAB4_1.0.1.2 - Complete the implementation of this method.
		// Hint - Similar method exists in the FacultyDaoHibernateAnnotationImpl class. 
	
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public void insert(Course course) {
		
		sessionFactory.getCurrentSession().saveOrUpdate(course);

		log.info("Inserted " + course.getName() + " into the Course table");
	}

	/**
	 * {@inheritDoc}
	 */
	public void update(Course course) {
		
		sessionFactory.getCurrentSession().saveOrUpdate(course);

		log.info("Updated " + course.getName() + " in the Course table");
	}

	/**
	 * {@inheritDoc}
	 */
	public void delete(Course course) {
		
		sessionFactory.getCurrentSession().delete(course);

		log.info("Deleted " + course.getName() + " from the Course table");
	}
	
	/**
	 * Getter for the sessionFactory.
	 * 
	 * @return sessionFactory The sessionFactory.
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	/**
	 * Setter for the sessionFactory.
	 * 
	 * @param sessionFactory The sessionFactory to set.
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
