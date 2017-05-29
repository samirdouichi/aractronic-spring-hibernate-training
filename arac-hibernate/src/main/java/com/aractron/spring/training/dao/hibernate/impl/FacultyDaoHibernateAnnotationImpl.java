package com.aractron.spring.training.dao.hibernate.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.aractron.spring.training.dao.FacultyDao;
import com.aractron.spring.training.domain.Faculty;

/**
 * This class is a Hibernate implementation of the FacultyDao interface that is 
 * configured using annotations.
 * 
 * @author vanessa.ortiz
 */
@Repository
@Transactional
public class FacultyDaoHibernateAnnotationImpl implements FacultyDao{
	
	/**
	 * Class instance of the Logger
	 */
	private static Log log = LogFactory.getLog((FacultyDaoHibernateAnnotationImpl.class));
	
	/**
	 * Class instance of Hibernate's SessionFactory
	 */
	@Autowired
	private SessionFactory sessionFactory;

	
	/**
	 * {@inheritDoc}
	 */
	public List<Faculty> findAllFacultyByDepartment(String departmentName) {

		Query q = sessionFactory.getCurrentSession().createQuery("from HibernateAnnotationFaculty f where f.department=:department");
		q.setString("department", departmentName);
		
		@SuppressWarnings("unchecked")
		List<Faculty> facultyList = q.list();
	
		return facultyList;
	}

	/**
	 * {@inheritDoc}
	 */
	public Faculty findById(Long id) {
		
		Query q = sessionFactory.getCurrentSession().createQuery("from HibernateAnnotationFaculty f where f.id=:id");
		q.setLong("id", id);

		Faculty faculty = (Faculty) q.uniqueResult();

		return faculty; 
	}

	/**
	 * {@inheritDoc}
	 */
	public List<Faculty> findAll() {

		Query q = sessionFactory.getCurrentSession().createQuery("from HibernateAnnotationFaculty");
		@SuppressWarnings("unchecked")
		List<Faculty> facultyList = q.list();
	
		return facultyList;
	}

	/**
	 * {@inheritDoc}
	 */
	public Faculty findByUsernameAndPassword(String username, String password) {
		
		Query q = sessionFactory.getCurrentSession().createQuery("from HibernateAnnotationFaculty f where f.userName=:username and f.password=:password");
		q.setString("username", username);
		q.setString("password", password);

		Faculty faculty = (Faculty) q.uniqueResult();
		
		return faculty;
	}

	/**
	 * {@inheritDoc}
	 */
	public void insert(Faculty faculty) {
		
		sessionFactory.getCurrentSession().saveOrUpdate(faculty);

		log.info("Inserted " + faculty.getUserName() + " into the Faculty table");
	}

	/**
	 * {@inheritDoc}
	 */
	public void update(Faculty faculty) {
		
		sessionFactory.getCurrentSession().saveOrUpdate(faculty);

		log.info("Updated " + faculty.getUserName() + " in the Faculty table");
	}

	/**
	 * {@inheritDoc}
	 */
	public void delete(Faculty faculty) {
		
		sessionFactory.getCurrentSession().delete(faculty);

		log.info("Deleted " + faculty.getUserName() + " from the Faculty table");
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
