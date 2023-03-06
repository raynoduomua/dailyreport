package com.example.dailyreport.application.form_validation;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class StudentCreateReportForm {

	private Integer id;

	private Integer userId;

	@NotBlank(groups = ValidGroup1.class, message = "本日の研修内容を入力してください")
	private String learningContents;

	@NotNull(groups = ValidGroup1.class, message = "本日の理解度を選択してください")
	private Integer understanding;

	@NotBlank(groups = ValidGroup1.class, message = "本日の理解度詳細を入力してください")
	private String understandingDetail;

	@NotNull(groups = ValidGroup1.class, message = "本日の講師対応を選択してください")
	private Integer teacherSupport;

	private String question;

}
