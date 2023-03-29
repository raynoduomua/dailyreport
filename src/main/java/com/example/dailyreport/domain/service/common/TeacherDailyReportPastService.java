package com.example.dailyreport.domain.service.common;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.dailyreport.application.common.security.LoginUser;
import com.example.dailyreport.application.form_validation.PastTeacherDailyReportForm;
import com.example.dailyreport.infrastructure.dto.AccountAndTeachersDailyReports;
import com.example.dailyreport.infrastructure.mapper.AccountAndTeachersDailyReportsMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TeacherDailyReportPastService {

	private final AccountAndTeachersDailyReportsMapper acouAccountAndTeachersDailyReportsMapper;

	/**
	 * 講師日報一覧
	 * @param loginUser                  ログイン中のユーザ情報
	 * @param pastTeacherDailyReportForm Formクラス
	 * @return                           講師日報一覧
	 */
	public List<AccountAndTeachersDailyReports> pastTeacherDailyReport(LoginUser loginUser,
			PastTeacherDailyReportForm pastTeacherDailyReportForm) {

		AccountAndTeachersDailyReports reports = new AccountAndTeachersDailyReports();

		if (pastTeacherDailyReportForm.getCourseNameId() != null) {
			reports.setCourseNameId(pastTeacherDailyReportForm.getCourseNameId());
		}
		if (pastTeacherDailyReportForm.getFromDate() != null) {
			reports.setFromDate(pastTeacherDailyReportForm.getFromDate());
		}
		if (pastTeacherDailyReportForm.getToDate() != null) {
			reports.setToDate(pastTeacherDailyReportForm.getToDate());
		}

		switch (loginUser.getUser().getRole()) {
		case 2:
		case 3:
			reports.setCourseNameId(loginUser.getUser().getCourseNameId());
			break;
		}

		return this.acouAccountAndTeachersDailyReportsMapper.searchTeachersDailyReports(reports);
	}

}
