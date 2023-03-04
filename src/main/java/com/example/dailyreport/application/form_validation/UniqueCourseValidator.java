package com.example.dailyreport.application.form_validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.dailyreport.infrastructure.repository.admin.AdminCourseRepository;

public class UniqueCourseValidator implements ConstraintValidator<UniqueCourse, String> {

	private final AdminCourseRepository adminCourseRepository;

	public UniqueCourseValidator() {
		this.adminCourseRepository = null;
	}

	@Autowired
	public UniqueCourseValidator(AdminCourseRepository adminCourseRepository) {
		this.adminCourseRepository = adminCourseRepository;
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return this.adminCourseRepository == null || this.adminCourseRepository.findByCourseName(value).isEmpty();
	}

}
