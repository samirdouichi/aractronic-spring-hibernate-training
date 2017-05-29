package com.aractron.spring.training.web.forms;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

/**
 * Integration of Spring Validation framework with JSR-303 validations.
 */
@Component
public class BeanValidator implements org.springframework.validation.Validator {
	public boolean supports(@SuppressWarnings("rawtypes") Class clazz) {
		return true;
	}

	public void validate(Object target, Errors errors) {
		Validator validator = Validation.buildDefaultValidatorFactory()
				.getValidator();
		Set<ConstraintViolation<Object>> constraintViolations = validator
				.validate(target);
		for (ConstraintViolation<Object> constraintViolation : constraintViolations) {
			String propertyPath = constraintViolation.getPropertyPath()
					.toString();
			String message = constraintViolation.getMessage();
			errors.rejectValue(propertyPath, "", message);
		}
	}
}