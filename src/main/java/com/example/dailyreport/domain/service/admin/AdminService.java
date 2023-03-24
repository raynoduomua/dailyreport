package com.example.dailyreport.domain.service.admin;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.dailyreport.application.common.utils.LocalDateNow;
import com.example.dailyreport.infrastructure.dto.AccountAndStudentsReports;
import com.example.dailyreport.infrastructure.entity.admin.Course;
import com.example.dailyreport.infrastructure.entity.admin.account.Account;
import com.example.dailyreport.infrastructure.mapper.AccountAndStudentsReportsMapper;
import com.example.dailyreport.infrastructure.repository.admin.AdminAccountRepository;
import com.example.dailyreport.infrastructure.repository.teacher.report.TeacherDailyReportRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class AdminService {

	private final AdminAccountRepository adminAccountRepository;
	private final AdminCourseService adminCourseService;
	private final AccountAndStudentsReportsMapper accountAndStudentsReportsMapper;
	private final TeacherDailyReportRepository teacherDailyReportRepository;

	/**
	 * 各講座の人数と受講生日報のMapw取得
	 * @return String 各講座名
	 *         List<Integer> 受講生日報入力人数
	 *                       各講座受講生人数
	 */
	public Map<String, List<Integer>> countCourseStudent() {

		Map<String, List<Integer>> courseMap = new LinkedHashMap<>();

		// 講座数
		Integer countCourse = this.adminCourseService.countCourse();

		for (int i = 2; i <= countCourse; i++) {

			List<Integer> courseList = new ArrayList<>();

			// 前日受講生日報数取得
			AccountAndStudentsReports reportsMinusDays = new AccountAndStudentsReports();
			reportsMinusDays.setCourseNameId(i);
			reportsMinusDays.setStudentsDate(LocalDateNow.getLocalDateNow().minusDays(1));
			List<AccountAndStudentsReports> reportListMinusDays = this.accountAndStudentsReportsMapper
					.findStudentReports(reportsMinusDays);
			courseList.add(reportListMinusDays.size());

			// 当日受講生日報数取得
			AccountAndStudentsReports reportsToday = new AccountAndStudentsReports();
			reportsToday.setCourseNameId(i);
			reportsToday.setStudentsDate(LocalDateNow.getLocalDateNow());
			List<AccountAndStudentsReports> reportListToday = this.accountAndStudentsReportsMapper
					.findStudentReports(reportsToday);
			courseList.add(reportListToday.size());

			// 講座受講生人数
			List<Account> accountList = this.adminAccountRepository.findByCourseNameIdAndRole(i, 5);
			courseList.add(accountList.size());

			// 講座名
			Optional<Course> courseOptional = this.adminCourseService.viewupdateCourse(i);
			String courseName = courseOptional.get().getCourseName();

			courseMap.put(courseName, courseList);
		}

		return courseMap;
	}

	public Map<String, List<Integer>> countCourseTeacherDailyReport() {

		Map<String, List<Integer>> courseMap = new LinkedHashMap<>();

		// 講座数
		Integer countCourse = this.adminCourseService.countCourse();

		for (int i = 2; i <= countCourse; i++) {

			List<Integer> courseList = new ArrayList<>();

			// 前日講師日報数取得
			Integer teacherDailyReportMinusDay = (int) this.teacherDailyReportRepository.countByCourseIdAndClassDate(i,
					LocalDateNow.getLocalDateNow().minusDays(1));
			courseList.add(teacherDailyReportMinusDay);

			// 当日講師日報数取得
			Integer teacherDailyReportToday = (int) this.teacherDailyReportRepository.countByCourseIdAndClassDate(i,
					LocalDateNow.getLocalDateNow());
			courseList.add(teacherDailyReportToday);

			// 講座名
			Optional<Course> courseOptional = this.adminCourseService.viewupdateCourse(i);
			String courseName = courseOptional.get().getCourseName();

			courseMap.put(courseName, courseList);
		}

		return courseMap;
	}

}
