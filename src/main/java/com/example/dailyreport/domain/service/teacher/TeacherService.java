package com.example.dailyreport.domain.service.teacher;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.dailyreport.application.common.security.LoginUser;
import com.example.dailyreport.infrastructure.dto.AccountAndStudentsReports;
import com.example.dailyreport.infrastructure.mapper.AccountAndStudentsReportsMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TeacherService {

	private final AccountAndStudentsReportsMapper accountAndStudentsReportsMapper;

	/**
	 * 受講生日報取得
	 * @param loginUser    ログイン中のユーザ情報
	 * @param studentsDate 受講日
	 * @return             受講生日報
	 */
	public List<AccountAndStudentsReports> findStudentReports(LoginUser loginUser, LocalDate studentsDate) {

		AccountAndStudentsReports reports = new AccountAndStudentsReports();
		reports.setCourseNameId(loginUser.getUser().getCourseNameId());
		reports.setStudentsDate(studentsDate);

		return this.accountAndStudentsReportsMapper.findStudentReports(reports);
	}

}
