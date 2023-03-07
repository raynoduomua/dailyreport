package com.example.dailyreport.application.controller.student;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.dailyreport.application.common.security.LoginUser;
import com.example.dailyreport.application.common.utils.LocalDateNow;
import com.example.dailyreport.domain.service.student.StudentService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {

	private final StudentService studentService;

	/**
	 * 受講生Home画面表示
	 * @param model     Modelクラス
	 * @param loginUser ログイン中のユーザ情報
	 * @return          受講生Home画面
	 */
	@GetMapping("/home")
	public String viewHome(Model model, @AuthenticationPrincipal LoginUser loginUser) {

		// ログイン中のユーザ情報取得
		model.addAttribute("loginAccount", studentService.viewAccountOneList(loginUser));

		// 本日日付
		model.addAttribute("today", LocalDateNow.getLocalDateNow());

		// 今週の受講生日報
		model.addAttribute("reports", studentService.viewHome(loginUser));

		return "student/home";
	}

}
