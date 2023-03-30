package com.example.dailyreport.domain.service.common;

import java.time.DayOfWeek;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.dailyreport.application.common.security.LoginUser;
import com.example.dailyreport.application.form_validation.PastTeacherWeeklyReportForm;
import com.example.dailyreport.infrastructure.dto.AccountAndTeachersWeeklyReports;
import com.example.dailyreport.infrastructure.mapper.AccountAndTeachersWeeklyReportsMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TeacherWeeklyReportPastService {

	private final AccountAndTeachersWeeklyReportsMapper accountAndTeachersWeeklyReportsMapper;

	public List<AccountAndTeachersWeeklyReports> pastTeacherWeeklyReport(LoginUser loginUser,
			PastTeacherWeeklyReportForm pastTeacherWeeklyReportForm) {

		AccountAndTeachersWeeklyReports reports = new AccountAndTeachersWeeklyReports();
		if (pastTeacherWeeklyReportForm.getCourseNameId() != null) {
			reports.setCourseId(pastTeacherWeeklyReportForm.getCourseNameId());
		}
		if (pastTeacherWeeklyReportForm.getClientNameId() != null) {
			reports.setClientId(pastTeacherWeeklyReportForm.getClientNameId());
		}
		if (pastTeacherWeeklyReportForm.getId() != null) {
			reports.setUserId(pastTeacherWeeklyReportForm.getId());
		}
		if (pastTeacherWeeklyReportForm.getFromDate() != null) {
			reports.setFromDate(pastTeacherWeeklyReportForm.getFromDate());
		}
		if (pastTeacherWeeklyReportForm.getToDate() != null) {
			reports.setToDate(pastTeacherWeeklyReportForm.getToDate());
		}

		switch (loginUser.getUser().getRole()) {
		case 2:
		case 3:
		case 4:
			reports.setCourseId(loginUser.getUser().getCourseNameId());
			break;
		}

		List<AccountAndTeachersWeeklyReports> listReports = this.accountAndTeachersWeeklyReportsMapper
				.searchTeachersWeeklyReports(reports);

		if (listReports.size() > 0) {
			for (int i = 0; i < listReports.size(); i++) {
				listReports.get(i).setMonday(listReports.get(i).getClassWeek().with(DayOfWeek.MONDAY));
				listReports.get(i).setFriday(listReports.get(i).getClassWeek().with(DayOfWeek.FRIDAY));
			}
		}

		return listReports;
	}

}
