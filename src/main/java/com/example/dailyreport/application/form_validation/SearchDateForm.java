package com.example.dailyreport.application.form_validation;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class SearchDateForm {

	@NotNull(groups = ValidGroup1.class, message = "検索日を選択してください")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate studentsDate;

}
