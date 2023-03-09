package com.example.dailyreport.domain.service.teacher;

import org.springframework.stereotype.Service;

import com.example.dailyreport.application.common.security.LoginUser;
import com.example.dailyreport.application.common.utils.LocalDateNow;
import com.example.dailyreport.application.form_validation.TeacherDailyReportForm;
import com.example.dailyreport.infrastructure.entity.teacher.TeacherDailyReport;
import com.example.dailyreport.infrastructure.repository.teacher.TeacherDailyReportRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TeacherDailyReportService {

	private final TeacherDailyReportRepository teacherDailyReportRepository;

	/**
	 * 本日の講師日報が存在するか
	 * @param loginUser ログイン中のユーザ情報
	 * @return          true or false
	 */
	public boolean existsByCourseIdAndClassDate(LoginUser loginUser) {

		return this.teacherDailyReportRepository.existsByCourseIdAndClassDate(loginUser.getUser().getCourseNameId(),
				LocalDateNow.getLocalDateNow());
	}

	/**
	 * 講師日報登録
	 * @param loginUser              ログイン中のユーザ情報
	 * @param teacherDailyReportForm Formクラス
	 */
	public void saveTeacherDailyReport(LoginUser loginUser, TeacherDailyReportForm teacherDailyReportForm) {

		TeacherDailyReport report = new TeacherDailyReport();
		report.setDailyReports(teacherDailyReportForm.getDailyReports());
		report.setCourseId(loginUser.getUser().getCourseNameId());
		report.setClassDate(LocalDateNow.getLocalDateNow());
		report.setCreatedAt(LocalDateNow.getLocalDateNow());
		report.setUpdatedAt(null);

		this.teacherDailyReportRepository.save(report);
	}

}
