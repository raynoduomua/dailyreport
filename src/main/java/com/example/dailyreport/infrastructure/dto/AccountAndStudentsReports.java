package com.example.dailyreport.infrastructure.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class AccountAndStudentsReports {

	private Integer id;

	private String name;

	private Integer courseNameId;

	private String learningContents;

	private Integer understanding;

	private String understandingDetail;

	private Integer teacherSupport;

	private String question;

	private LocalDate studentsDate;

}
