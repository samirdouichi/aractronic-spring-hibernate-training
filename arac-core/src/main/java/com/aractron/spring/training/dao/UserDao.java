package com.aractron.spring.training.dao;

import java.util.List;

/**
 * Generic Data Access interface for managing User domain objects. This interface serves
 * as the parent interface for the Faculty and Student Daos. 
 * 
 * @author vanessa.ortiz
 */
public interface UserDao<T> {

	/**
	 * Method that retrieves an instance of the User object by it's Id (PK).
	 * 
	 * @param id The object's unique identifier (PK);
	 * @return The object being sought.
	 */
	public T findById(Long id);
	
	/**
	 * Method that retrieves an instance of the User object based on it's 
	 * username and password. 
	 *  
	 * @param username The user's username, case sensitive.
	 * @param password The user's password, case sensitive.
	 * @return An instance of the User object, either a Faculty or Student object.
	 */
	public T findByUsernameAndPassword(String username, String password);
	
	/**
	 * Method that retrieves all of the domain entries in the corresponding typed table.
	 * 
	 * @return An ArrayList of User instance objects.
	 */
	public List<T> findAll();
	
	/**
	 * Method that saves an instance of the User object to the corresponding typed table.
	 * 
	 * @param user Either a Faculty or Student object to be saved.
	 */
	public void insert(T user);
	
	/**
	 * Method that updates an instance of the User object in the corresponding typed table.
	 * 
	 * @param user Either a Faculty or Student object to be updated.
	 */
	public void update(T user);
	
	/**
	 * Method that removes an instance User object to the corresponding typed table.
	 * 
	 * @param user Either a Faculty or Student object to be removed.
	 */
	public void delete(T user);
}
