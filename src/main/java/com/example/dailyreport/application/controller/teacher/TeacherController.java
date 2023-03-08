package com.example.dailyreport.application.controller.teacher;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.dailyreport.application.common.security.LoginUser;
import com.example.dailyreport.application.common.utils.LocalDateNow;
import com.example.dailyreport.domain.service.common.CommonService;
import com.example.dailyreport.domain.service.teacher.TeacherService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/teacher")
@Slf4j
public class TeacherController {

	private final CommonService commonService;
	private final TeacherService teacherService;

	@GetMapping("/home")
	public String viewHome(Model model, @AuthenticationPrincipal LoginUser loginUser) {

		// ログイン中のユーザ情報取得
		model.addAttribute("loginAccount", this.commonService.viewAccountOneList(loginUser));

		// 本日日付
		model.addAttribute("today", LocalDateNow.getLocalDateNow());

		if (LocalDateNow.geLocalDateTimeHour() <= 14) {

			model.addAttribute("studentreports",
					this.teacherService.findStudentReports(loginUser, LocalDateNow.getLocalDateNow().minusDays(1)));
		} else {

			model.addAttribute("studentreports",
					this.teacherService.findStudentReports(loginUser, LocalDateNow.getLocalDateNow()));
		}

		return "teacher/home";
	}

}
