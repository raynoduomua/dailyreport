package com.example.dailyreport.infrastructure.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class AccountAndCourseAndClient {

	private Integer id;

	private String name;

	private String nameKana;

	private Integer clientNameId;

	private Integer courseNameId;

	private Integer role;

	private LocalDate createdAt;

	private LocalDate updatedAt;

	private String clientName;

	private String courseName;

}
