package com.example.dailyreport.application.form_validation;

import java.time.LocalDate;

import lombok.Data;

@Data
public class PastTeacherDailyReportForm {

	private Integer id;

	private Integer courseNameId;

	private LocalDate fromDate;

	private LocalDate toDate;

}
