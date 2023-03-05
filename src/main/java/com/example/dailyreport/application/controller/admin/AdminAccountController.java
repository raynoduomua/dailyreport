package com.example.dailyreport.application.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.dailyreport.application.common.utils.Authority;
import com.example.dailyreport.application.form_validation.AccountForm;
import com.example.dailyreport.application.form_validation.GroupOrder;
import com.example.dailyreport.domain.service.admin.AdminAccountService;
import com.example.dailyreport.domain.service.admin.AdminClientService;
import com.example.dailyreport.domain.service.admin.AdminCourseService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
@Slf4j
public class AdminAccountController {

	private final AdminAccountService adminAccountService;
	private final AdminClientService adminClientService;
	private final AdminCourseService adminCourseService;

	/**
	 * アカウント作成画面表示
	 * http://localhost:8080/admin/create-account
	 * @param model
	 * @param accountForm
	 * @return
	 */
	@GetMapping("/create-account")
	public String viewCreateAccount(Model model, @ModelAttribute("accountForm") AccountForm accountForm) {

		// クライアント名一覧
		model.addAttribute("clients", this.adminClientService.viewClientList());
		// 講座名一覧
		model.addAttribute("courses", this.adminCourseService.viewCourseList());
		// 権限Map
		model.addAttribute("AuthorityMap", Authority.selectAuthorityMap());

		return "admin/account/createaccount";
	}

	/**
	 * アカウント登録処理
	 * @param model         Modelクラス
	 * @param accountForm   formクラス
	 * @param bindingResult バリデーションチェック
	 * @return              アカウント作成画面表示
	 */
	@PostMapping("/save-account")
	public String saveAccount(Model model,
			@Validated(GroupOrder.class) @ModelAttribute("accountForm") AccountForm accountForm,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {

			return this.viewCreateAccount(model, accountForm);
		}

		log.info("accountForm: {}", accountForm);
		this.adminAccountService.saveAccount(accountForm);

		return "redirect:/admin/create-account?save";
	}

}
