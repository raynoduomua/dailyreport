package com.example.dailyreport.application.controller.common;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.dailyreport.application.common.security.LoginUser;
import com.example.dailyreport.application.common.utils.LocalDateNow;
import com.example.dailyreport.application.common.utils.TeacherSupport;
import com.example.dailyreport.application.common.utils.UnderStand;
import com.example.dailyreport.application.form_validation.PastStudentDailyReportForm;
import com.example.dailyreport.domain.service.admin.AdminClientService;
import com.example.dailyreport.domain.service.admin.AdminCourseService;
import com.example.dailyreport.domain.service.common.CommonService;
import com.example.dailyreport.domain.service.common.StudentReportPastService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/past")
@Slf4j
public class StudentReportPastController {

	private final CommonService commonService;
	private final AdminCourseService adminCourseService;
	private final AdminClientService adminClientService;
	private final StudentReportPastService studentReportPastService;

	@GetMapping("/student-daily-report")
	public String viewPastStudentDailyReport(Model model, @AuthenticationPrincipal LoginUser loginUser,
			@ModelAttribute("pastStudentDailyReportForm") PastStudentDailyReportForm pastStudentDailyReportForm) {

		// ログイン中のユーザ情報取得
		model.addAttribute("loginAccount", this.commonService.viewAccountOneList(loginUser));
		// 本日日付
		model.addAttribute("today", LocalDateNow.getLocalDateNow());
		// 講座名一覧
		model.addAttribute("courses", this.adminCourseService.viewCourseList());
		// クライアント名一覧
		model.addAttribute("clients", this.adminClientService.viewClientList());
		// 受講生一覧
		model.addAttribute("students", this.studentReportPastService.viewAccountList(loginUser));
		// 理解度
		model.addAttribute("underMap", UnderStand.selectUnderStandMap());
		// 講師対応
		model.addAttribute("teacherMap", TeacherSupport.selectTeacherSupportMap());

		return "common/past/studentdailyreport";
	}

	@PostMapping("/student-daily-report")
	public String pastStudentDailyReport(Model model, @AuthenticationPrincipal LoginUser loginUser,
			@ModelAttribute("pastStudentDailyReportForm") PastStudentDailyReportForm pastStudentDailyReportForm) {

		log.info("pastStudentDailyReportForm: {}", pastStudentDailyReportForm);

		// ログイン中のユーザ情報取得
		model.addAttribute("loginAccount", this.commonService.viewAccountOneList(loginUser));
		// 本日日付
		model.addAttribute("today", LocalDateNow.getLocalDateNow());
		// 講座名一覧
		model.addAttribute("courses", this.adminCourseService.viewCourseList());
		// クライアント名一覧
		model.addAttribute("clients", this.adminClientService.viewClientList());
		// 受講生一覧
		model.addAttribute("students", this.studentReportPastService.viewAccountList(loginUser));
		// 理解度
		model.addAttribute("underMap", UnderStand.selectUnderStandMap());
		// 講師対応
		model.addAttribute("teacherMap", TeacherSupport.selectTeacherSupportMap());

		return "common/past/studentdailyreport";
	}

}
