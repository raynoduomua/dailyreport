package com.example.dailyreport.infrastructure.entity.admin;

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
@Table(name = "coursesname")
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;

	@Column(name = "COURSE_NAME")
	private String courseName;

	@Column(name = "CREATED_AT")
	private LocalDate createdAt;

	@Column(name = "UPDATED_AT")
	private LocalDate updatedAt;

}
