package com.example.dailyreport.application.form_validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueCourseValidator.class)
public @interface UniqueCourse {

	String message() default "この講座名は既に登録されています";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
