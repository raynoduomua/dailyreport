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

@Controller
@RequiredArgsConstructor
@RequestMapping("/teacher")
public class TeacherController {

	private final CommonService commonService;
	private final TeacherService teacherService;

	/**
	 * 講師Home画面表示
	 * @param model     formクラス
	 * @param loginUser ログイン中のユーザ情報
	 * @return          講師Home画面
	 */
	@GetMapping("/home")
	public String viewHome(Model model, @AuthenticationPrincipal LoginUser loginUser) {

		// ログイン中のユーザ情報取得
		model.addAttribute("loginAccount", this.commonService.viewAccountOneList(loginUser));
		// 本日日付
		model.addAttribute("today", LocalDateNow.getLocalDateNow());

		if (LocalDateNow.geLocalDateTimeHour() <= 13) {

			// 13時59分まで受講生日報前日分取得
			model.addAttribute("studentreports",
					this.teacherService.findStudentReports(loginUser, LocalDateNow.getLocalDateNow().minusDays(1)));
		} else {

			// 14時以降受講生日報当日分取得
			model.addAttribute("studentreports",
					this.teacherService.findStudentReports(loginUser, LocalDateNow.getLocalDateNow()));

		}
		return "teacher/home";
	}

}
