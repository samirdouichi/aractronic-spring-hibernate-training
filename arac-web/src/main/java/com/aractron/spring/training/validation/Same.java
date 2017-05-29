package com.aractron.spring.training.validation;

import static java.lang.annotation.ElementType.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Validation annotation to validate that 2 fields have the same value. An array
 * of fields and their matching confirmation fields can be supplied.
 * <div>
 * Example, compare 1 pair of fields:
 * <pre>@Same(left = "password", right = "passwordConfirmation", message =
 *                   "Password does not match")
 * </pre></div>
 * <div>
 * Example, compare more than 1 pair of fields:
 * <pre>@Same({
 * 	@Same(left = "password", right = "passwordConfirmation"),
 * 	@Same(left = "phone", right = "phoneConfirmation", message =
 *                   "Phone numbers do not match")})
 * </pre></div>
 */
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SameValidator.class)
@Documented
@Target({ TYPE, ANNOTATION_TYPE })
public @interface Same {
	String message() default "{com.aractron.spring.training.validation.Same.message}";

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
	 * Defines several <code>@Same</code> annotations on the same element
	 * 
	 * @see Same
	 */
	@Target({ TYPE, ANNOTATION_TYPE })
	@Retention(RetentionPolicy.RUNTIME)
	@Documented
	@interface List {
		Same[] value();
	}

}
