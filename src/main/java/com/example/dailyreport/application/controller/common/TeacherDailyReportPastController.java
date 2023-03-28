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
import com.example.dailyreport.application.form_validation.PastTeacherDailyReportForm;
import com.example.dailyreport.domain.service.admin.AdminCourseService;
import com.example.dailyreport.domain.service.common.CommonService;
import com.example.dailyreport.domain.service.common.TeacherDailyReportPastService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/past")
public class TeacherDailyReportPastController {

	private final CommonService commonService;
	private final AdminCourseService adminCourseService;
	private final TeacherDailyReportPastService teacherDailyReportPastService;

	@GetMapping("/teacher-daily-report")
	public String viewPastTeacherDailyReport(Model model, @AuthenticationPrincipal LoginUser loginUser,
			@ModelAttribute("pastTeacherDailyReportForm") PastTeacherDailyReportForm pastTeacherDailyReportForm) {

		// ログイン中のユーザ情報取得
		model.addAttribute("loginAccount", this.commonService.viewAccountOneList(loginUser));
		// 本日日付
		model.addAttribute("today", LocalDateNow.getLocalDateNow());
		// 講座名一覧
		model.addAttribute("courses", this.adminCourseService.viewCourseList());

		return "common/past/teacherdailyreport";
	}

	@PostMapping("/teacher-daily-report")
	public String pastTeacherDailyReport(Model model, @AuthenticationPrincipal LoginUser loginUser,
			@ModelAttribute("pastTeacherDailyReportForm") PastTeacherDailyReportForm pastTeacherDailyReportForm) {

		// ログイン中のユーザ情報取得
		model.addAttribute("loginAccount", this.commonService.viewAccountOneList(loginUser));
		// 本日日付
		model.addAttribute("today", LocalDateNow.getLocalDateNow());
		// 講座名一覧
		model.addAttribute("courses", this.adminCourseService.viewCourseList());
		// 講師日報
		model.addAttribute("teacherreports",
				this.teacherDailyReportPastService.pastTeacherDailyReport(loginUser, pastTeacherDailyReportForm));

		return "common/past/teacherdailyreport";
	}

}
