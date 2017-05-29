package com.aractron.spring.training.aspect;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

/**
 * Aspect class that will output a message to the console upon entry and exist 
 * of any method as defined by the pointcut expressions. 
 * 
 * @author vanessa.ortiz
 */
@Aspect
public class MethodLoggerAspect {
	
	/**
	 * Class instance of the Logger
	 */
	private static Log log =  LogFactory.getLog((MethodLoggerAspect.class));

	/**
	 * Log method entry.
	 * 
	 * @param joinPoint
	 *            the join point
	 */
	@Before( "execution(* com.aractron.spring.training.dao.*FacultyDao*+.*(..))" )
	public void logMethodEntry(JoinPoint joinPoint) 
	{
		 StringBuffer logMessageStringBuffer = new StringBuffer();
		 logMessageStringBuffer.append("<<< Entering Method : ");
		 logMessageStringBuffer.append(joinPoint.getTarget().getClass().getName());
		 logMessageStringBuffer.append(".");
		 logMessageStringBuffer.append(joinPoint.getSignature().getName() + " >>>");
		 		 
		 log.info(logMessageStringBuffer.toString());
	}

	/**
	 * Log normal method exit.
	 * 
	 * @param joinPoint
	 *            the join point
	 */
	@AfterReturning( "execution(* com.aractron.spring.training.dao.*FacultyDao+.*(..))" )
	public void logNormalMethodExit(JoinPoint joinPoint) 
	{
		 StringBuffer logMessageStringBuffer = new StringBuffer();
		 logMessageStringBuffer.append("<<< Exiting Method : ");
		 logMessageStringBuffer.append(joinPoint.getTarget().getClass().getName());
		 logMessageStringBuffer.append(".");
		 logMessageStringBuffer.append(joinPoint.getSignature().getName() + " >>>");
		 
		 log.info(logMessageStringBuffer.toString());
	}
}
