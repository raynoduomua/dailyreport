package com.example.dailyreport.infrastructure.entity.student;

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
@Table(name = "studentsreports")
public class StudentReport {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;

	@Column(name = "USER_ID")
	private Integer userId;

	@Column(name = "LEARNING_CONTENTS")
	private String learningContents;

	@Column(name = "UNDERSTANDING")
	private Integer understanding;

	@Column(name = "UNDERSTANDING_DETAIL")
	private String understandingDetail;

	@Column(name = "TEACHER_SUPPORT")
	private Integer teacherSupport;

	@Column(name = "QUESTION")
	private String question;

	@Column(name = "STUDENTS_DATE")
	private LocalDate studentsDate;

	@Column(name = "CREATED_AT")
	private LocalDate createdAt;

	@Column(name = "UPDATED_AT")
	private LocalDate updatedAt;

}
