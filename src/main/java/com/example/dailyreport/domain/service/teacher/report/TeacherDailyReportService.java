package com.example.dailyreport.domain.service.teacher.report;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.dailyreport.application.common.security.LoginUser;
import com.example.dailyreport.application.common.utils.LocalDateNow;
import com.example.dailyreport.application.form_validation.TeacherDailyReportForm;
import com.example.dailyreport.infrastructure.entity.teacher.report.TeacherDailyReport;
import com.example.dailyreport.infrastructure.repository.teacher.report.TeacherDailyReportRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TeacherDailyReportService {

	private final TeacherDailyReportRepository teacherDailyReportRepository;

	/**
	 * 該当日講師日報取得し、Formにセット
	 * @param loginUser              ログイン中のユーザ情報
	 * @param teacherDailyReportForm Formクラス
	 * @return                       teacherDailyReportForm
	 */
	public TeacherDailyReportForm searchTeacherDailyReport(LoginUser loginUser,
			TeacherDailyReportForm teacherDailyReportForm) {

		Optional<TeacherDailyReport> reportOptional = this.findByCourseIdAndClassDate(loginUser,
				teacherDailyReportForm);
		reportOptional.ifPresent(report -> {
			teacherDailyReportForm.setId(report.getId());
			teacherDailyReportForm.setDailyReports(report.getDailyReports());
			teacherDailyReportForm.setStudentsDate(report.getClassDate());

		});

		return teacherDailyReportForm;
	}

	/**
	 * 該当日講師日報取得
	 * @param loginUser              ログイン中のユーザ情報
	 * @param teacherDailyReportForm formクラス
	 * @return                       TeacherDailyReport
	 */
	public Optional<TeacherDailyReport> findByCourseIdAndClassDate(LoginUser loginUser,
			TeacherDailyReportForm teacherDailyReportForm) {

		return this.teacherDailyReportRepository.findByCourseIdAndClassDate(loginUser.getUser().getCourseNameId(),
				teacherDailyReportForm.getStudentsDate());
	}

	/**
	 * 該当日の講師日報が存在するか
	 * @param loginUser              ログイン中のユーザ情報
	 * @param teacherDailyReportForm Formクラス
	 * @return                       true or false
	 */
	public boolean existsByCourseIdAndClassDate(LoginUser loginUser, TeacherDailyReportForm teacherDailyReportForm) {

		return this.teacherDailyReportRepository.existsByCourseIdAndClassDate(loginUser.getUser().getCourseNameId(),
				teacherDailyReportForm.getStudentsDate());
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
		report.setClassDate(teacherDailyReportForm.getStudentsDate());
		report.setCreatedAt(LocalDateNow.getLocalDateNow());
		report.setUpdatedAt(null);

		this.teacherDailyReportRepository.save(report);
	}

	/**
	 * 講師日報更新処理
	 * @param loginUser              ログイン中のユーザ情報
	 * @param teacherDailyReportForm Formクラス
	 */
	public void updateTeacherDailyReport(LoginUser loginUser,
			TeacherDailyReportForm teacherDailyReportForm) {

		Optional<TeacherDailyReport> reportOptional = this.findByCourseIdAndClassDate(loginUser,
				teacherDailyReportForm);
		reportOptional.ifPresent(report -> {
			report.setDailyReports(teacherDailyReportForm.getDailyReports());
			report.setUpdatedAt(LocalDateNow.getLocalDateNow());

			this.teacherDailyReportRepository.save(report);
		});
	}

}
