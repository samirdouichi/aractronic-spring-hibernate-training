package com.aractron.spring.training.validation;

import static java.lang.annotation.ElementType.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Validation annotation to validate that 2 fields do not have the same value. An array
 * of fields and their matching confirmation fields can be supplied.
 * <div>
 * Example, compare 1 pair of fields:
 * <pre>@Different(left = "username", right = "password", message =
 *                   "User Name and password may not match")
 * </pre></div>
 * <div>
 * Example, compare more than 1 pair of fields:
 * <pre>@Different({
 * 	@Different(left = "username", right = "password"),
 * 	@Different(left = "homePhone", right = "businessPhone", message =
 *                   "Please provide different phone numbers for home and business")})
 * </pre></div>
 */
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DifferentValidator.class)
@Documented
@Target({ TYPE, ANNOTATION_TYPE })
public @interface Different {
	String message() default "{com.aractron.spring.training.validation.Different.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	/**
	 * @return the left side of the comparison
	 */
	String left();

	/**
	 * @return the right side of the comparison
	 */
	String right();

	/**
	 * Defines several <code>@Different</code> annotations on the same element
	 * 
	 * @see Different
	 */
	@Target({ TYPE, ANNOTATION_TYPE })
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	@interface List {
		Different[] value();
	}

}
