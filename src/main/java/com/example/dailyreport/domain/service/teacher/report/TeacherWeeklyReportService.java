package com.example.dailyreport.domain.service.teacher.report;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.dailyreport.application.common.security.LoginUser;
import com.example.dailyreport.application.common.utils.LocalDateNow;
import com.example.dailyreport.application.form_validation.TeacherWeeklyReportForm;
import com.example.dailyreport.infrastructure.dto.AccountAndTeachersWeeklyReports;
import com.example.dailyreport.infrastructure.entity.teacher.report.TeacherWeeklyReport;
import com.example.dailyreport.infrastructure.mapper.AccountAndTeachersWeeklyReportsMapper;
import com.example.dailyreport.infrastructure.repository.teacher.report.TeacherWeeklyReportRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TeacherWeeklyReportService {

	private final TeacherWeeklyReportRepository teacherWeeklyReportRepository;
	private final AccountAndTeachersWeeklyReportsMapper accountAndTeachersWeeklyReportsMapper;

	/**
	 * 対象受講生の講師週報存在check
	 * @param loginUser               ログイン中のユーザ情報
	 * @param teacherWeeklyReportForm Formクラス
	 * @return                        true or false
	 */
	public boolean existsByCourseIdAndUserIdAndClassWeekBetween(LoginUser loginUser,
			TeacherWeeklyReportForm teacherWeeklyReportForm) {

		return this.teacherWeeklyReportRepository
				.existsByCourseIdAndUserIdAndClassWeekBetween(loginUser.getUser().getCourseNameId(),
						teacherWeeklyReportForm.getUserId(), LocalDateNow.getLocalDateMonday(),
						LocalDateNow.getLocalDateFriday());
	}

	/**
	 * 対象受講生の講師週報登録処理
	 * @param loginUser               ログイン中のユーザ情報
	 * @param teacherWeeklyReportForm Formクラス
	 */
	public void saveTeacherWeeklyReport(LoginUser loginUser, TeacherWeeklyReportForm teacherWeeklyReportForm) {

		TeacherWeeklyReport report = new TeacherWeeklyReport();
		report.setWeeklyReports(teacherWeeklyReportForm.getWeeklyReports());
		report.setCourseId(loginUser.getUser().getCourseNameId());
		report.setUserId(teacherWeeklyReportForm.getUserId());
		report.setClassWeek(LocalDateNow.getLocalDateNow());
		report.setCreatedAt(LocalDateNow.getLocalDateNow());
		report.setUpdatedAt(null);

		this.teacherWeeklyReportRepository.save(report);
	}

	/**
	 * 講師と同じ講座の講師週報取得（対象：今週入力分）
	 * @return 講師週報
	 */
	public List<AccountAndTeachersWeeklyReports> findbyUserIdAndCourseIdAndClassWeekBetween() {

		AccountAndTeachersWeeklyReports report = new AccountAndTeachersWeeklyReports();
		report.setMonday(LocalDateNow.getLocalDateMonday());
		report.setFriday(LocalDateNow.getLocalDateFriday());

		return this.accountAndTeachersWeeklyReportsMapper.findbyUserIdAndCourseIdAndClassWeekBetween(report);
	}

	/**
	 * 対象受講生の講師週報取得
	 * @param id                      テーブル「teachersweeklyreports」 カラム「userId」
	 * @param loginUser               ログイン中のユーザ情報
	 * @param teacherWeeklyReportForm Formクラス
	 * @return                        teacherWeeklyReportForm
	 */
	public TeacherWeeklyReportForm viewUpdateTeacherWeeklyReport(Integer id, LoginUser loginUser,
			TeacherWeeklyReportForm teacherWeeklyReportForm) {

		AccountAndTeachersWeeklyReports report = new AccountAndTeachersWeeklyReports();
		report.setUserId(id);
		report.setMonday(LocalDateNow.getLocalDateMonday());
		report.setFriday(LocalDateNow.getLocalDateFriday());

		List<AccountAndTeachersWeeklyReports> reportList = this.accountAndTeachersWeeklyReportsMapper
				.findbyUserIdAndCourseIdAndClassWeekBetween(report);
		teacherWeeklyReportForm.setId(reportList.get(0).getId());
		teacherWeeklyReportForm.setWeeklyReports(reportList.get(0).getWeeklyReports());
		teacherWeeklyReportForm.setUserId(reportList.get(0).getUserId());

		return teacherWeeklyReportForm;
	}

	/**
	 * 対象受講生の講師週報更新処理
	 * @param teacherWeeklyReportForm Formクラス
	 */
	public void updateTeacherWeeklyReport(TeacherWeeklyReportForm teacherWeeklyReportForm) {

		Optional<TeacherWeeklyReport> reportOptional = this.teacherWeeklyReportRepository
				.findById(teacherWeeklyReportForm.getId());
		reportOptional.ifPresent(report -> {
			report.setWeeklyReports(teacherWeeklyReportForm.getWeeklyReports());
			report.setUpdatedAt(LocalDateNow.getLocalDateNow());

			this.teacherWeeklyReportRepository.save(report);
		});
	}

}
