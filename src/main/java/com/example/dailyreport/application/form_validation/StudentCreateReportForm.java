package com.example.dailyreport.application.form_validation;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class StudentCreateReportForm {

	private Integer id;

	private Integer userId;

	@NotBlank(groups = ValidGroup1.class)
	private String learningContents;

	@NotNull(groups = ValidGroup1.class, message = "本日の理解度を選択してください")
	private Integer understanding;

	@NotBlank(groups = ValidGroup1.class)
	private String understandingDetail;

	@NotNull(groups = ValidGroup1.class, message = "本日の講師対応を選択してください")
	private Integer teacherSupport;

	private String question;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate studentsDate;

}
