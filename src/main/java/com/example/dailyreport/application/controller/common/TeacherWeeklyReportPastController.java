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
import com.example.dailyreport.application.form_validation.PastTeacherWeeklyReportForm;
import com.example.dailyreport.domain.service.admin.AdminClientService;
import com.example.dailyreport.domain.service.admin.AdminCourseService;
import com.example.dailyreport.domain.service.common.CommonService;
import com.example.dailyreport.domain.service.common.StudentReportPastService;
import com.example.dailyreport.domain.service.common.TeacherWeeklyReportPastService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/past")
public class TeacherWeeklyReportPastController {

	private final CommonService commonService;
	private final AdminCourseService adminCourseService;
	private final AdminClientService adminClientService;
	private final StudentReportPastService studentReportPastService;
	private final TeacherWeeklyReportPastService teacherWeeklyReportPastService;

	@GetMapping("/teacher-weekly-report")
	public String viewPastTeacherWeeklyReport(Model model, @AuthenticationPrincipal LoginUser loginUser,
			@ModelAttribute("pastTeacherWeeklyReportForm") PastTeacherWeeklyReportForm pastTeacherWeeklyReportForm) {

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

		return "common/past/teacherweeklyreport";
	}

	@PostMapping("/teacher-weekly-report")
	public String pastTeacherWeeklyReport(Model model, @AuthenticationPrincipal LoginUser loginUser,
			@ModelAttribute("pastTeacherWeeklyReportForm") PastTeacherWeeklyReportForm pastTeacherWeeklyReportForm) {

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

		if (pastTeacherWeeklyReportForm.getFromDate() != null && pastTeacherWeeklyReportForm.getToDate() != null
				&& !(pastTeacherWeeklyReportForm.getFromDate().isBefore(pastTeacherWeeklyReportForm.getToDate()))) {

			return "redirect:/past/teacher-weekly-report?errordate";
		}

		model.addAttribute("studentreports",
				this.teacherWeeklyReportPastService.pastTeacherWeeklyReport(loginUser, pastTeacherWeeklyReportForm));

		return "common/past/teacherweeklyreport";
	}

}
