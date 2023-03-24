package com.example.dailyreport.application.controller.admin;

import java.util.List;
import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.dailyreport.application.common.security.LoginUser;
import com.example.dailyreport.application.common.utils.LocalDateNow;
import com.example.dailyreport.domain.service.admin.AdminService;
import com.example.dailyreport.domain.service.common.CommonService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

	private final AdminService adminService;
	private final CommonService commonService;

	@GetMapping("/home")
	public String viewHome(Model model, @AuthenticationPrincipal LoginUser loginUser) {

		// ログイン中のユーザ情報取得
		model.addAttribute("loginAccount", this.commonService.viewAccountOneList(loginUser));
		// 本日日付
		model.addAttribute("today", LocalDateNow.getLocalDateNow());
		// 受講生日報数
		Map<String, List<Integer>> courseStudentMap = this.adminService.countCourseStudent();
		model.addAttribute("courseStudentMap", courseStudentMap);
		// 講師日報数
		Map<String, List<Integer>> courseTeacherDailyReportMap = this.adminService.countCourseTeacherDailyReport();
		model.addAttribute("courseTeacherDailyReportMap", courseTeacherDailyReportMap);

		return "admin/home";
	}

}
