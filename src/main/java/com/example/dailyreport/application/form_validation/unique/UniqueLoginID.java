package com.example.dailyreport.application.form_validation.unique;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueLoginIDValidator.class)
public @interface UniqueLoginID {

	String message() default "このログインIDは既に登録されています";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
