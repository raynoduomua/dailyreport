package com.example.dailyreport.application.form_validation;

import java.time.LocalDate;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class TeacherMiniExamForm {

	private Integer id;

	@NotNull(groups = ValidGroup1.class, message = "受講生を選択してください")
	private Integer userId;

	@NotNull(groups = ValidGroup1.class, message = "単元テスト名を選択してください")
	private Integer testId;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(groups = ValidGroup1.class, message = "単元テスト実施日付を選択してください")
	private LocalDate testDate;

	@NotNull(groups = ValidGroup1.class, message = "受講生単元テスト点数を入力してください")
	@Max(value = 100, groups = ValidGroup2.class, message = "点数は100点以下を入力してください")
	private Integer score;

	@NotNull(groups = ValidGroup1.class, message = "受講生単元テスト平均点数を入力してください")
	@Max(value = 100, groups = ValidGroup2.class, message = "平均点数は100点以下を入力してください")
	private Integer scoreAverage;

}
