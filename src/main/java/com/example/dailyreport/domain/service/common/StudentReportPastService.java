package com.example.dailyreport.domain.service.common;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.dailyreport.application.common.security.LoginUser;
import com.example.dailyreport.application.form_validation.PastStudentDailyReportForm;
import com.example.dailyreport.infrastructure.dto.AccountAndCourseAndClient;
import com.example.dailyreport.infrastructure.dto.AccountAndStudentsReports;
import com.example.dailyreport.infrastructure.mapper.AccountAndCourseAndClientMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentReportPastService {

	private final AccountAndCourseAndClientMapper accountAndCourseAndClientMapper;

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

	public void pastStudentDailyReport(PastStudentDailyReportForm pastStudentDailyReportForm) {

		AccountAndStudentsReports reports = new AccountAndStudentsReports();

	}
}
