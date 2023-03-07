package com.example.dailyreport;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.dailyreport.application.common.security.LoginUser;

@Controller
@RequestMapping("/dailyreport")
public class MainController {

	/**
	 * ログイン画面表示
	 * @return ログイン画面
	 */
	@GetMapping("/loginForm")
	public String viewLogin() {

		return "loginForm";
	}

	@GetMapping("/loginFork")
	public String loginFork(@AuthenticationPrincipal LoginUser loginUser) {

		// 権限によって遷移先を変更する
		switch (loginUser.getUser().getRole()) {
		// admin
		case 1:
			return "redirect:/admin/home";
		// main講師
		// sub講師
		case 2:
		case 3:
			return "redirect:/teacher/home";
		// client
		case 4:
			return "redirect:/client/home";
		// student
		default:
			return "redirect:/student/home";
		}

	}

}
