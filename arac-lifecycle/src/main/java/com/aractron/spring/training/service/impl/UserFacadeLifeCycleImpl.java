package com.aractron.spring.training.service.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.aractron.spring.training.domain.User;
import com.aractron.spring.training.service.impl.UserFacadeDaoImpl;

public class UserFacadeLifeCycleImpl extends UserFacadeDaoImpl implements
		InitializingBean, DisposableBean, ApplicationContextAware {
	private ApplicationContext context;
	private String logFileName = "UserFacade";
	private String logFilePath = "/";
	private BufferedWriter writer;

	@Override
	public void afterPropertiesSet() throws Exception {
		File file = new File(logFilePath, logFileName + ".log");
		writer = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(file, true)));
		writer.write("UserFacade bean operating with the following bean definitions: "
				+ StringUtils.join(context.getBeanDefinitionNames()));
		writer.flush();
		System.out.println("UserFacade log created at "
				+ file.getAbsolutePath());
	}

	@Override
	public void destroy() throws Exception {
		writer.close();
	}

	@Override
	public User findUser(String userName, String password) {
		User user = super.findUser(userName, password);
		// Student to log this event
		return user;
	}

	/**
	 * Return the suffix for the log file name. The log file will always have
	 * the suffix of '.log'.
	 * 
	 * @return 'UserFacade' by default
	 */
	public String getLogFileName() {
		return logFileName;
	}

	/**
	 * Return the filepath for the log file.
	 * 
	 * @return '/' by default
	 */
	public String getLogFilePath() {
		return logFilePath;
	}

	/**
	 * Write the log message to the file.
	 * 
	 * @param string
	 */
	@SuppressWarnings("unused")
	private void log(String string) {
		try {
			System.out.println(string);
			writer.write(new Date() + " " + string);
			writer.flush();
		} catch (Exception e) {
			System.err.println("Unable to write the following log message: "
					+ string);
			e.printStackTrace();
		}
	}

	@Override
	public User saveUser(User user) {
		User tmpUser = super.saveUser(user);
		// Student to log this event
		return tmpUser;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.context = applicationContext;
	}

	public void setLogFileName(String logFileName) {
		this.logFileName = logFileName;
	}

	public void setLogFilePath(String logFilePath) {
		this.logFilePath = logFilePath;
	}

}
