package com.example.dailyreport.domain.service.student;

import org.springframework.stereotype.Service;

import com.example.dailyreport.application.common.utils.LocalDateNow;
import com.example.dailyreport.application.form_validation.StudentCreateReportForm;
import com.example.dailyreport.infrastructure.entity.student.StudentReport;
import com.example.dailyreport.infrastructure.repository.student.StudentReportRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentReportService {

	private final StudentReportRepository studentReportRepository;

	public void saveStudentDailyReport(StudentCreateReportForm studentCreateReportForm) {

		StudentReport report = new StudentReport();
		report.setUserId(6);
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

}
