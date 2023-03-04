package com.example.dailyreport.application.form_validation;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class ClientForm {

	private Integer id;

	@NotBlank(groups = ValidGroup1.class)
	@UniqueClient(groups = ValidGroup2.class)
	private String clientname;

}
