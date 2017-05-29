package com.aractron.spring.training.service;

import com.aractron.spring.training.domain.User;
import com.aractron.spring.training.exception.UserManagementException;

/**
 * Service layer interface for {@link User} related operations.
 * 
 * @author aaron.levensailor
 */
public interface UserFacade {
	
	/**
	 * Find user by their login credentials.
	 * 
	 * @param userName
	 *            the user name
	 * @param password
	 *            the password
	 * @return the user
	 */
	public User findUser(String userName, String password);

	/**
	 * Insert/Update a User object.
	 * 
	 * @param user
	 *            User instance to insert or update
	 * @return updated User object
	 */
	public User saveUser(User user) throws UserManagementException;
}
