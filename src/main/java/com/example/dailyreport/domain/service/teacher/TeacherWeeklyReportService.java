package com.example.dailyreport.domain.service.teacher;

import org.springframework.stereotype.Service;

import com.example.dailyreport.application.common.security.LoginUser;
import com.example.dailyreport.application.common.utils.LocalDateNow;
import com.example.dailyreport.application.form_validation.TeacherWeeklyReportForm;
import com.example.dailyreport.infrastructure.entity.teacher.TeacherWeeklyReport;
import com.example.dailyreport.infrastructure.repository.teacher.TeacherWeeklyReportRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TeacherWeeklyReportService {

	private final TeacherWeeklyReportRepository teacherWeeklyReportRepository;

	public boolean existsByCourseIdAndUserIdAndClassWeekBetween(LoginUser loginUser,
			TeacherWeeklyReportForm teacherWeeklyReportForm) {

		return this.teacherWeeklyReportRepository
				.existsByCourseIdAndUserIdAndClassWeekBetween(loginUser.getUser().getCourseNameId(),
						teacherWeeklyReportForm.getUserId(), LocalDateNow.getLocalDateMonday(),
						LocalDateNow.getLocalDateFriday());
	}

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

}
