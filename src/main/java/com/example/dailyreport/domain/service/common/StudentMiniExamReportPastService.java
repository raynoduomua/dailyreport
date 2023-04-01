package com.example.dailyreport.domain.service.common;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.dailyreport.application.common.security.LoginUser;
import com.example.dailyreport.application.form_validation.PastStudentMiniExamReportForm;
import com.example.dailyreport.infrastructure.dto.AccountAndUnittestsAndUnittestsname;
import com.example.dailyreport.infrastructure.mapper.AccountAndUnittestsAndUnittestsnameMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentMiniExamReportPastService {

	private final AccountAndUnittestsAndUnittestsnameMapper accountAndUnittestsAndUnittestsnameMapper;

	/**
	 * 単元テスト一覧取得
	 * @param loginUser                     ログイン中のユーザ情報
	 * @param pastStudentMiniExamReportForm Formクラス
	 * @return                              単元テスト一覧
	 */
	public List<AccountAndUnittestsAndUnittestsname> pastStudentMiniExamReport(LoginUser loginUser,
			PastStudentMiniExamReportForm pastStudentMiniExamReportForm) {

		AccountAndUnittestsAndUnittestsname report = new AccountAndUnittestsAndUnittestsname();

		if (pastStudentMiniExamReportForm.getCourseNameId() != null) {
			report.setCourseNameId(pastStudentMiniExamReportForm.getCourseNameId());
		}
		if (pastStudentMiniExamReportForm.getClientNameId() != null) {
			report.setClientNameId(pastStudentMiniExamReportForm.getClientNameId());
		}
		if (pastStudentMiniExamReportForm.getId() != null) {
			report.setUserId(pastStudentMiniExamReportForm.getId());
		}

		switch (loginUser.getUser().getRole()) {
		case 2:
		case 3:
		case 4:
			report.setCourseNameId(loginUser.getUser().getCourseNameId());
			break;
		case 5:
			report.setUserId(loginUser.getUser().getId());
			break;
		}

		return this.accountAndUnittestsAndUnittestsnameMapper.searchStudentUnitTests(report);
	}

}
