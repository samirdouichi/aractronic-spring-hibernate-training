package com.aractron.spring.training.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Validate that two fields in an object have the same value.
 */
@SuppressWarnings("unused")
public class SameValidator implements ConstraintValidator<Same, Object> {
	private final Log logger = LogFactory.getLog(getClass());

	@Override
	public void initialize(Same constraintAnnotation) {
		// CS8 - Lab 4 - Student to perform validator initialization
	}

	@Override
	public boolean isValid(final Object targetValue,
			ConstraintValidatorContext context) {
		boolean same = false;
		// CS8 - Lab 4 - Student to perform validator implementation
		return same;
	}
}
