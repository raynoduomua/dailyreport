package com.example.dailyreport.infrastructure.entity.teacher.report;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "teachersweeklyreports")
public class TeacherWeeklyReport {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;

	@Column(name = "WEEKLY_REPORTS")
	private String weeklyReports;

	@Column(name = "COURSE_ID")
	private Integer courseId;

	@Column(name = "USER_ID")
	private Integer userId;

	@Column(name = "CLASS_WEEK")
	private LocalDate classWeek;

	@Column(name = "CREATED_AT")
	private LocalDate createdAt;

	@Column(name = "UPDATED_AT")
	private LocalDate updatedAt;

}
