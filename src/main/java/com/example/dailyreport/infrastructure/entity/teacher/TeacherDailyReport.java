package com.example.dailyreport.infrastructure.entity.teacher;

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
@Table(name = "teacheresdailyreports")
public class TeacherDailyReport {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;

	@Column(name = "DAILY_REPORTS")
	private String dailyReports;

	@Column(name = "COURSE_ID")
	private Integer courseId;

	@Column(name = "CLASS_DATE")
	private LocalDate classDate;

	@Column(name = "CREATED_AT")
	private LocalDate createdAt;

	@Column(name = "UPDATED_AT")
	private LocalDate updatedAt;

}
