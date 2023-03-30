package com.example.dailyreport.application.form_validation;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class PastTeacherWeeklyReportForm {

	private Integer courseNameId;

	private Integer clientNameId;

	private Integer id;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fromDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate toDate;

}
