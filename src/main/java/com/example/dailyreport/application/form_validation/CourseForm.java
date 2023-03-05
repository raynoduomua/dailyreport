package com.example.dailyreport.application.form_validation;

import javax.validation.constraints.NotBlank;

import com.example.dailyreport.application.form_validation.unique.UniqueCourse;

import lombok.Data;

@Data
public class CourseForm {

	private Integer id;

	@NotBlank(groups = ValidGroup1.class)
	@UniqueCourse(groups = ValidGroup2.class)
	private String coursename;

}
