package com.aractron.spring.training.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Validate that two fields in an object have different values.
 * 
 * @author aaron.levensailor
 */
public class DifferentValidator implements
		ConstraintValidator<Different, Object> {
	private final Log logger = LogFactory.getLog(getClass());
	private String leftFieldName;
	private String rightFieldName;

	@Override
	public void initialize(Different constraintAnnotation) {
		leftFieldName = constraintAnnotation.left();
		rightFieldName = constraintAnnotation.right();
	}

	@Override
	public boolean isValid(final Object targetValue,
			ConstraintValidatorContext context) {
		boolean different = false;
		try {
			Object left = BeanUtils.getProperty(targetValue, leftFieldName);
			Object right = BeanUtils.getProperty(targetValue, rightFieldName);

			different = (left == null && right != null)
					 || (left != null && right == null) 
					 || !left.equals(right);
		} catch (Exception e) {
			logger.error("Unable to validate difference of " + leftFieldName
					+ ", " + rightFieldName + ": " + e, e);
		}

		return different;
	}
}
