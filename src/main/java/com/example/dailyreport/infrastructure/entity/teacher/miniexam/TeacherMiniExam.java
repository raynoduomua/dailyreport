package com.example.dailyreport.infrastructure.entity.teacher.miniexam;

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
@Table(name = "unittests")
public class TeacherMiniExam {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;

	@Column(name = "USER_ID")
	private Integer userId;

	@Column(name = "TEST_ID")
	private Integer testId;

	@Column(name = "SCORE")
	private Integer score;

	@Column(name = "SCORE_AVERAGE")
	private Integer scoreAverage;

	@Column(name = "TEST_DATE")
	private LocalDate testDate;

	@Column(name = "CREATED_AT")
	private LocalDate createdAt;

	@Column(name = "UPDATED_AT")
	private LocalDate updatedAt;

}
