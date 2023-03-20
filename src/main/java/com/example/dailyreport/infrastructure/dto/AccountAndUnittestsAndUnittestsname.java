package com.example.dailyreport.infrastructure.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class AccountAndUnittestsAndUnittestsname {

	private Integer id;

	private String name;

	private Integer userId;

	private String testName;

	private Integer score;

	private Integer scoreAverage;

	private LocalDate testDate;

	private LocalDate createdAt;

	private LocalDate updatedAt;

}
