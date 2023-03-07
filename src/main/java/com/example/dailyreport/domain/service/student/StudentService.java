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

	public List<StudentReport> viewHome(LoginUser loginUser) {

		return studentReportRepository.findByUserIdAndStudentsDateBetween(loginUser.getUser().getId(),
				LocalDateNow.getLocalDateMonday(), LocalDateNow.getLocalDateFriday());
	}

	/**
	 * アカウント一覧取得
	 * @return アカウント一覧
	 */
	public List<AccountAndCourseAndClient> viewAccountOneList(LoginUser loginUser) {

		AccountAndCourseAndClient account = new AccountAndCourseAndClient();
		account.setId(loginUser.getUser().getId());

		return accountAndCourseAndClientMapper.findAllAccount(account);
	}

}
