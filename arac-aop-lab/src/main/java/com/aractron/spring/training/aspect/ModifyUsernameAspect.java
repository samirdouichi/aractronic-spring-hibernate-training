package com.aractron.spring.training.aspect;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import com.aractron.spring.training.dao.impl.StudentDaoJDBCImpl;
import com.aractron.spring.training.domain.Faculty;
import com.aractron.spring.training.domain.Student;

import org.aspectj.lang.JoinPoint;

/**
 * Aspect class that will add a prefix to a User's username before saving the user in the DB. <br>
 * For Student: stu_ <br>
 * For Faculty: instr_
 * 
 * @author vanessa.ortiz
 */
// TODO: CS4_LAB2_1.1.1 - Add the Aspect annotation
public class ModifyUsernameAspect {
	
	/** 
	 * Class instance of the Logger
	 */
	private static Log log =  LogFactory.getLog((StudentDaoJDBCImpl.class));
	
	/**
	 * Method that will insert the prefix "instr_" into the Faculty's username before
	 * the Faulty is saved to the DB.
	 * 
	 * @param joinPoint the joinpoint
	 */
	// TODO: CS4_LAB2_1.1.2 & 1.1.4 - Create the pointcut expression that will implement the below advice
	// every time the FacultyDao's insert() method is called to execute. Then add the appropriate annotation
	// to have this advice execute before the insert() method is executed.
	public void changeFacultyUsername(JoinPoint joinPoint){
		
		Object[] methodParams = joinPoint.getArgs();
		 
		 if(methodParams[0] instanceof Faculty){
			 log.info("<<< Changing Faculty's current username: " + ((Faculty)methodParams[0]).getUserName() + " >>>");
			 
			 String newUsername = "instr_" +  ((Faculty)methodParams[0]).getUserName();
			 ((Faculty)methodParams[0]).setUserName(newUsername); 
			 
			 log.info("<<< Successfully changed Faculty's username to: " + ((Faculty)methodParams[0]).getUserName() + " >>>");
		 }
	}
	
	/**
	 * Method that will insert the prefix "stu_" into a Student's username before
	 * the Student is saved to the DB.
	 * 
	 * @param joinPoint the joinpoint
	 */
	// TODO: CS4_LAB2_1.1.3 & 1.1.4 - Create the pointcut expression that will implement the below advice
	// every time the StudentDao's insert() method is called to execute. Then add the appropriate annotation
	// to have this advice execute before the insert() method is executed.
	public void changeStudentUsername(JoinPoint joinPoint){

		// TODO: CS4_LAB2_1.1.5 - Complete the implementation of this method. Hint: similar to the one above.
	}
}
