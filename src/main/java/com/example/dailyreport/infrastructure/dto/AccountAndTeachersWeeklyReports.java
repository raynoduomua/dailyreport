package com.example.dailyreport.infrastructure.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class AccountAndTeachersWeeklyReports {

	private Integer id;

	private String name;

	private String weeklyReports;

	private LocalDate classWeek;

	private LocalDate createdAt;

	private LocalDate updatedAt;

	private Integer userId;

	private Integer courseId;

	private LocalDate Monday;

	private LocalDate Friday;

}
