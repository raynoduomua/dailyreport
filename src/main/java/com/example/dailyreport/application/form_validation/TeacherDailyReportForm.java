package com.example.dailyreport.application.form_validation;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class TeacherDailyReportForm {

	private Integer id;

	@NotBlank(groups = ValidGroup1.class)
	private String dailyReports;

}
