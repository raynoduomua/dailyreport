package com.example.dailyreport.application.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.dailyreport.application.common.utils.Authority;
import com.example.dailyreport.application.form_validation.AccountForm;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminAccountController {

	/**
	 * アカウント登録画面表示
	 * http://localhost:8080/admin/create-account
	 * @param model
	 * @param accountForm
	 * @return
	 */
	@GetMapping("/create-account")
	public String viewCreateAccount(Model model, @ModelAttribute("accountForm") AccountForm accountForm) {

		model.addAttribute("AuthorityMap", Authority.selectAuthorityMap());

		return "admin/account/createaccount";
	}
}
