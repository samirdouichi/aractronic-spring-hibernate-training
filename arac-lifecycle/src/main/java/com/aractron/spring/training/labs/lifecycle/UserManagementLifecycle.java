package com.aractron.spring.training.labs.lifecycle;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.aractron.spring.training.domain.Student;
import com.aractron.spring.training.domain.User;
import com.aractron.spring.training.exception.UserManagementException;
import com.aractron.spring.training.service.UserFacade;

public class UserManagementLifecycle {
	@SuppressWarnings("null")
	public static void main(String[] args) throws UserManagementException {
		ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext(
				new String[] { "/com/aractron/spring/training/labs/lifecycle/beans-config.xml" });
		UserFacade uf = (UserFacade) ac.getBean(UserFacade.class);
		Student student = null;
		uf.saveUser(student);
		
		User user = uf.findUser(student.getUserName(), student.getPassword());
		System.out.println(user);
		ac.close();
	}
}
