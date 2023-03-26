package com.example.dailyreport.domain.service.common;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.dailyreport.application.common.security.LoginUser;
import com.example.dailyreport.application.form_validation.PastStudentDailyReportForm;
import com.example.dailyreport.infrastructure.dto.AccountAndCourseAndClient;
import com.example.dailyreport.infrastructure.dto.AccountAndStudentsReports;
import com.example.dailyreport.infrastructure.mapper.AccountAndCourseAndClientMapper;
import com.example.dailyreport.infrastructure.mapper.AccountAndStudentsReportsMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentReportPastService {

	private final AccountAndCourseAndClientMapper accountAndCourseAndClientMapper;
	private final AccountAndStudentsReportsMapper accountAndStudentsReportsMapper;

	/**
	 * アカウント一覧取得
	 * @return アカウント一覧
	 */
	public List<AccountAndCourseAndClient> viewAccountList(LoginUser loginUser) {

		AccountAndCourseAndClient account = new AccountAndCourseAndClient();

		switch (loginUser.getUser().getRole()) {
		case 1:
			break;
		case 2:
		case 3:
		case 4:
		case 5:
			account.setCourseNameId(loginUser.getUser().getCourseNameId());
			break;
		}
		account.setRole(5);

		return accountAndCourseAndClientMapper.findAllAccount(account);
	}

	public List<AccountAndStudentsReports> pastStudentDailyReport(
			PastStudentDailyReportForm pastStudentDailyReportForm) {

		AccountAndStudentsReports reports = new AccountAndStudentsReports();

		if (pastStudentDailyReportForm.getCourseNameId() != null) {
			reports.setCourseNameId(pastStudentDailyReportForm.getCourseNameId());
		}
		if (pastStudentDailyReportForm.getClientNameId() != null) {
			reports.setClientNameId(pastStudentDailyReportForm.getClientNameId());
		}
		if (pastStudentDailyReportForm.getId() != null) {
			reports.setId(pastStudentDailyReportForm.getId());
		}
		if (pastStudentDailyReportForm.getUnderstanding() != null) {
			reports.setUnderstanding(pastStudentDailyReportForm.getUnderstanding());
		}
		if (pastStudentDailyReportForm.getTeacherSupport() != null) {
			reports.setTeacherSupport(pastStudentDailyReportForm.getTeacherSupport());
		}
		if (pastStudentDailyReportForm.getFromDate() != null) {
			reports.setFromDate(pastStudentDailyReportForm.getFromDate());
		}
		if (pastStudentDailyReportForm.getToDate() != null) {
			reports.setToDate(pastStudentDailyReportForm.getToDate());
		}

		return this.accountAndStudentsReportsMapper.searchStudentReports(reports);
	}

}
