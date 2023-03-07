package com.example.dailyreport.domain.service.student;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.dailyreport.application.common.security.LoginUser;
import com.example.dailyreport.application.common.utils.LocalDateNow;
import com.example.dailyreport.infrastructure.dto.AccountAndCourseAndClient;
import com.example.dailyreport.infrastructure.entity.student.StudentReport;
import com.example.dailyreport.infrastructure.mapper.AccountAndCourseAndClientMapper;
import com.example.dailyreport.infrastructure.repository.student.StudentReportRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentService {

	private final StudentReportRepository studentReportRepository;
	private final AccountAndCourseAndClientMapper accountAndCourseAndClientMapper;

	/**
	 * 今週の受講生日報情報取得
	 * @param loginUser ログイン中のユーザ情報
	 * @return          今週の受講生日報
	 */
	public List<StudentReport> viewHome(LoginUser loginUser) {

		return studentReportRepository.findByUserIdAndStudentsDateBetween(loginUser.getUser().getId(),
				LocalDateNow.getLocalDateMonday(), LocalDateNow.getLocalDateFriday());
	}

	/**
	 * ログイン中のユーザ情報取得
	 * @param loginUser ログイン中のユーザ情報
	 * @return          ログイン中のユーザ情報
	 */
	public List<AccountAndCourseAndClient> viewAccountOneList(LoginUser loginUser) {

		AccountAndCourseAndClient account = new AccountAndCourseAndClient();
		account.setId(loginUser.getUser().getId());

		return accountAndCourseAndClientMapper.findAllAccount(account);
	}

}
