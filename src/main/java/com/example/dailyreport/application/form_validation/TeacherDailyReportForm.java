package com.example.dailyreport.application.form_validation;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class TeacherDailyReportForm {

	private Integer id;

	@NotBlank(groups = ValidGroup1.class)
	private String dailyReports;

	@NotNull(groups = ValidGroup1.class, message = "日付を選択し、検索してから登録してください")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate studentsDate;

}
