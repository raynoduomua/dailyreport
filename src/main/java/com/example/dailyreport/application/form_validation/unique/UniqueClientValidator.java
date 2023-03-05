package com.example.dailyreport.application.form_validation.unique;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.dailyreport.infrastructure.repository.admin.AdminClientRepository;

public class UniqueClientValidator implements ConstraintValidator<UniqueClient, String> {

	public final AdminClientRepository adminClientRepository;

	public UniqueClientValidator() {
		this.adminClientRepository = null;
	}

	@Autowired
	public UniqueClientValidator(AdminClientRepository adminClientRepository) {
		this.adminClientRepository = adminClientRepository;
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return this.adminClientRepository == null || this.adminClientRepository.findByClientName(value).isEmpty();
	}

}
