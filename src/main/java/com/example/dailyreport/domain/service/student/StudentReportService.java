package com.example.dailyreport.domain.service.student;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.dailyreport.application.common.security.LoginUser;
import com.example.dailyreport.application.common.utils.LocalDateNow;
import com.example.dailyreport.application.form_validation.StudentCreateReportForm;
import com.example.dailyreport.infrastructure.entity.student.StudentReport;
import com.example.dailyreport.infrastructure.repository.student.StudentReportRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentReportService {

	private final StudentReportRepository studentReportRepository;

	/**
	 * 受講生日報登録
	 * @param loginUser               ログイン中のユーザ情報
	 * @param studentCreateReportForm Formクラス
	 */
	public void saveStudentDailyReport(LoginUser loginUser, StudentCreateReportForm studentCreateReportForm) {

		StudentReport report = new StudentReport();
		report.setUserId(loginUser.getUser().getId());
		report.setLearningContents(studentCreateReportForm.getLearningContents());
		report.setUnderstanding(studentCreateReportForm.getUnderstanding());
		report.setUnderstandingDetail(studentCreateReportForm.getUnderstandingDetail());
		report.setTeacherSupport(studentCreateReportForm.getTeacherSupport());
		report.setQuestion(studentCreateReportForm.getQuestion());
		report.setStudentsDate(LocalDateNow.getLocalDateNow());
		report.setCreatedAt(LocalDateNow.getLocalDateNow());
		report.setUpdatedAt(null);

		this.studentReportRepository.save(report);
	}

	/**
	 * 本日の受講生日報が存在するか
	 * @param loginUser ログイン中のユーザ情報
	 * @return          true or false
	 */
	public boolean existsByStudentsDate(LoginUser loginUser) {

		return this.studentReportRepository.existsByUserIdAndStudentsDate(loginUser.getUser().getId(),
				LocalDateNow.getLocalDateNow());
	}

	/**
	 * 当日受講生日報取得
	 * @param loginUser ログイン中のユーザ情報
	 * @return          当日受講生日報
	 */
	public Optional<StudentReport> findByUserIdAndStudentsDate(LoginUser loginUser) {

		Optional<StudentReport> studentOptional = this.studentReportRepository
				.findByUserIdAndStudentsDate(loginUser.getUser().getId(), LocalDateNow.getLocalDateNow());

		return studentOptional;
	}

	/**
	 * 当日受講生日報取得し、Formにセット
	 * @param loginUser               ログイン中のユーザ情報
	 * @param studentCreateReportForm Formクラス
	 * @return                        Formクラス
	 */
	public StudentCreateReportForm viewUpdateStudentDailyReport(LoginUser loginUser,
			StudentCreateReportForm studentCreateReportForm) {

		Optional<StudentReport> studentOptional = this.findByUserIdAndStudentsDate(loginUser);
		studentOptional.ifPresent(report -> {
			studentCreateReportForm.setId(report.getId());
			studentCreateReportForm.setUserId(report.getUserId());
			studentCreateReportForm.setLearningContents(report.getLearningContents());
			studentCreateReportForm.setUnderstanding(report.getUnderstanding());
			studentCreateReportForm.setUnderstandingDetail(report.getUnderstandingDetail());
			studentCreateReportForm.setTeacherSupport(report.getTeacherSupport());
			studentCreateReportForm.setQuestion(report.getQuestion());

		});

		return studentCreateReportForm;
	}

	/**
	 * 受講生日報更新処理
	 * @param loginUser               ログイン中のユーザ情報
	 * @param studentCreateReportForm Formクラス
	 */
	public void updateStudentDailyReport(LoginUser loginUser, StudentCreateReportForm studentCreateReportForm) {

		Optional<StudentReport> studentOptional = this.findByUserIdAndStudentsDate(loginUser);
		studentOptional.ifPresent(report -> {
			report.setLearningContents(studentCreateReportForm.getLearningContents());
			report.setUnderstanding(studentCreateReportForm.getUnderstanding());
			report.setUnderstandingDetail(studentCreateReportForm.getUnderstandingDetail());
			report.setTeacherSupport(studentCreateReportForm.getTeacherSupport());
			report.setQuestion(studentCreateReportForm.getQuestion());
			report.setUpdatedAt(LocalDateNow.getLocalDateNow());

			this.studentReportRepository.save(report);
		});
	}

}
