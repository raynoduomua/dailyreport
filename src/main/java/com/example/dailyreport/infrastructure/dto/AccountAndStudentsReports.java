package com.example.dailyreport.infrastructure.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class AccountAndStudentsReports {

	private Integer id;

	private LocalDate studentsDate;

	private String name;

	private String learningContents;

	private Integer understanding;

	private String understandingDetail;

	private Integer teacherSupport;

	private String question;

	private Integer clientNameId;

	private Integer courseNameId;

	private Integer role;

	private LocalDate fromDate;

	private LocalDate toDate;

}
