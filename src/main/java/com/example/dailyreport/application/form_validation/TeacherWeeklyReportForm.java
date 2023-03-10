package com.example.dailyreport.application.form_validation;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class TeacherWeeklyReportForm {

	private Integer id;

	@NotBlank(groups = ValidGroup1.class)
	private String weeklyReports;

	@NotNull(groups = ValidGroup1.class, message = "受講生を選択してください")
	private Integer userId;

}
