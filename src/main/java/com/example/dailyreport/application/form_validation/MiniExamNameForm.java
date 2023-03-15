package com.example.dailyreport.application.form_validation;

import javax.validation.constraints.NotBlank;

import com.example.dailyreport.application.form_validation.unique.UniqueMiniExamName;

import lombok.Data;

@Data
public class MiniExamNameForm {

	private Integer id;

	@NotBlank(groups = ValidGroup1.class)
	@UniqueMiniExamName(groups = ValidGroup2.class)
	private String testName;

}
