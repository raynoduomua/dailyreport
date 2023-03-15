package com.example.dailyreport.application.form_validation.unique;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.dailyreport.infrastructure.repository.admin.AdminMiniExamRepository;

public class UniqueMiniExamNameValidator implements ConstraintValidator<UniqueMiniExamName, String> {

	public final AdminMiniExamRepository adminMiniExamRepository;

	public UniqueMiniExamNameValidator() {
		this.adminMiniExamRepository = null;
	}

	@Autowired
	public UniqueMiniExamNameValidator(AdminMiniExamRepository adminMiniExamRepository) {
		this.adminMiniExamRepository = adminMiniExamRepository;
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return this.adminMiniExamRepository == null || this.adminMiniExamRepository.findByTestName(value).isEmpty();
	}

}
