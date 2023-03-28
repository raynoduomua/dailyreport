package com.example.dailyreport.infrastructure.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class AccountAndTeachersDailyReports {

	private Integer id;

	private LocalDate classDate;

	private String courseName;

	private String dailyReports;

	private Integer courseNameId;

	private LocalDate fromDate;

	private LocalDate toDate;

}
