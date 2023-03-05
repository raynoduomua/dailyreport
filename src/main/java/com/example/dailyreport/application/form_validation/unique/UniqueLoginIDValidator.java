package com.example.dailyreport.application.form_validation.unique;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.dailyreport.infrastructure.repository.admin.AdminAccountRepository;

public class UniqueLoginIDValidator implements ConstraintValidator<UniqueLoginID, String> {

	public final AdminAccountRepository adminAccountRepository;

	public UniqueLoginIDValidator() {
		this.adminAccountRepository = null;
	}

	@Autowired
	public UniqueLoginIDValidator(AdminAccountRepository adminAccountRepository) {
		this.adminAccountRepository = adminAccountRepository;
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return this.adminAccountRepository == null || this.adminAccountRepository.findByLoginId(value).isEmpty();
	}

}
