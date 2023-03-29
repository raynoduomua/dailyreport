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
	 * 該当日受講生日報取得し、Formにセット
	 * @param loginUser               ログイン中のユーザ情報
	 * @param studentCreateReportForm Formクラス
	 * @return                        studentCreateReportForm
	 */
	public StudentCreateReportForm searchStudentDailyReport(LoginUser loginUser,
			StudentCreateReportForm studentCreateReportForm) {

		Optional<StudentReport> studentOptional = this.findByUserIdAndStudentsDate(loginUser, studentCreateReportForm);
		studentOptional.ifPresent(report -> {
			studentCreateReportForm.setId(report.getId());
			studentCreateReportForm.setUserId(report.getUserId());
			studentCreateReportForm.setLearningContents(report.getLearningContents());
			studentCreateReportForm.setUnderstanding(report.getUnderstanding());
			studentCreateReportForm.setUnderstandingDetail(report.getUnderstandingDetail());
			studentCreateReportForm.setTeacherSupport(report.getTeacherSupport());
			studentCreateReportForm.setQuestion(report.getQuestion());
			studentCreateReportForm.setStudentsDate(report.getStudentsDate());
		});

		return studentCreateReportForm;
	}

	/**
	 * 該当日受講生日報取得
	 * @param loginUser               ログイン中のユーザ情報
	 * @param studentCreateReportForm formクラス
	 * @return                        StudentReport
	 */
	public Optional<StudentReport> findByUserIdAndStudentsDate(LoginUser loginUser,
			StudentCreateReportForm studentCreateReportForm) {

		return this.studentReportRepository
				.findByUserIdAndStudentsDate(loginUser.getUser().getId(), studentCreateReportForm.getStudentsDate());
	}

	/**
	 */
	/**
	 * 該当日の受講生日報が存在するか
	 * @param loginUser               ログイン中のユーザ情報
	 * @param studentCreateReportForm Formクラス
	 * @return                        true or false
	 */
	public boolean existsByStudentsDate(LoginUser loginUser, StudentCreateReportForm studentCreateReportForm) {

		return this.studentReportRepository.existsByUserIdAndStudentsDate(loginUser.getUser().getId(),
				studentCreateReportForm.getStudentsDate());
	}

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
		report.setStudentsDate(studentCreateReportForm.getStudentsDate());
		report.setCreatedAt(LocalDateNow.getLocalDateNow());
		report.setUpdatedAt(null);

		this.studentReportRepository.save(report);
	}

	/**
	 * 受講生日報更新処理
	 * @param loginUser               ログイン中のユーザ情報
	 * @param studentCreateReportForm Formクラス
	 */
	public void updateStudentDailyReport(LoginUser loginUser, StudentCreateReportForm studentCreateReportForm) {

		Optional<StudentReport> studentOptional = this.findByUserIdAndStudentsDate(loginUser, studentCreateReportForm);
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
