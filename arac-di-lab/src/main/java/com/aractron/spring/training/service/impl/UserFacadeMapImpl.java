package com.aractron.spring.training.service.impl;

import java.util.HashMap;
import java.util.Map;

import com.aractron.spring.training.domain.Faculty;
import com.aractron.spring.training.domain.Student;
import com.aractron.spring.training.domain.User;
import com.aractron.spring.training.exception.UserManagementException;
import com.aractron.spring.training.service.UserFacade;

/**
 * Rudimentary example of a UserFacade where the instance uses a Map to define
 * the known Users of the system.
 */
public class UserFacadeMapImpl implements UserFacade {
	private Map<String, Class<User>> userMap = new HashMap<String, Class<User>>();

	public UserFacadeMapImpl(Map<String, Class<User>> userMap) {
		this.userMap = userMap;
	}

	@Override
	public User findUser(String userName, String password) {
		User user = null;
		String credentials = userName + ":" + password;
		if (userMap.containsKey(credentials)) {
			Class<User> userClass = userMap.get(credentials);
			if (Student.class.equals(userClass)) {
				user = new Student();
			} else if (Faculty.class.equals(userClass)) {
				user = new Faculty();
			}
			user.setUserName(userName);
			user.setPassword(password);
		}
		return user;
	}

	@SuppressWarnings("unchecked")
	@Override
	public User saveUser(User user) throws UserManagementException {
		userMap.put(user.getUserName() + ":" + user.getPassword(),
				(Class<User>) user.getClass());
		return user;
	}
}
