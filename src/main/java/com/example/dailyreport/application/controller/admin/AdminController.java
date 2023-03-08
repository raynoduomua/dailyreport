package com.example.dailyreport.application.controller.admin;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.dailyreport.application.common.security.LoginUser;
import com.example.dailyreport.application.common.utils.LocalDateNow;
import com.example.dailyreport.domain.service.admin.AdminAccountService;
import com.example.dailyreport.domain.service.common.CommonService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
@Slf4j
public class AdminController {

	private final CommonService commonService;
	private final AdminAccountService adminAccountService;

	@GetMapping("/home")
	public String viewHome(Model model, @AuthenticationPrincipal LoginUser loginUser) {

		// ログイン中のユーザ情報取得
		model.addAttribute("loginAccount", this.commonService.viewAccountOneList(loginUser));

		// 本日日付
		model.addAttribute("today", LocalDateNow.getLocalDateNow());

		// 受講生レコード数
		Integer count = this.adminAccountService.countByRole(5);
		log.info("count: {}", count);

		return "admin/home";
	}

}
