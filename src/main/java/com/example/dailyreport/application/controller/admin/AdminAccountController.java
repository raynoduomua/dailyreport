package com.example.dailyreport.application.controller.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.dailyreport.application.common.utils.Authority;
import com.example.dailyreport.application.form_validation.AccountForm;
import com.example.dailyreport.application.form_validation.GroupOrder;
import com.example.dailyreport.domain.service.admin.AdminAccountService;
import com.example.dailyreport.domain.service.admin.AdminClientService;
import com.example.dailyreport.domain.service.admin.AdminCourseService;
import com.example.dailyreport.infrastructure.dto.AccountAndCourseAndClient;
import com.example.dailyreport.infrastructure.entity.admin.account.Account;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminAccountController {

	private final AdminAccountService adminAccountService;
	private final AdminClientService adminClientService;
	private final AdminCourseService adminCourseService;

	/**
	 * アカウント作成画面表示
	 * http://localhost:8080/admin/create-account
	 * @param model       Modelクラス
	 * @param accountForm formクラス
	 * @return            アカウント作成画面
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
	 * @return              アカウント作成画面
	 */
	@PostMapping("/save-account")
	public String saveAccount(Model model,
			@Validated(GroupOrder.class) @ModelAttribute("accountForm") AccountForm accountForm,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {

			return this.viewCreateAccount(model, accountForm);
		}

		this.adminAccountService.saveAccount(accountForm);

		return "redirect:/admin/create-account?save";
	}

	/**
	 * アカウント一覧画面表示
	 * @param model Modelクラス
	 * @return      アカウント一覧画面
	 */
	@GetMapping("/list-account")
	public String viewAccountList(Model model) {

		List<AccountAndCourseAndClient> accounts = this.adminAccountService.viewAccountList();
		model.addAttribute("accounts", accounts);

		return "admin/list/allaccount";
	}

	/**
	 * アカウント編集画面表示
	 * @param id          テーブル「accounts」 カラム「ID」
	 * @param model       Modelクラス
	 * @param accountForm Formクラス
	 * @return            アカウント編集画面
	 */
	@GetMapping("/edit-account/{id}")
	public String viewupdateAccount(@PathVariable Integer id, Model model,
			@ModelAttribute("accountForm") AccountForm accountForm) {

		// クライアント名一覧
		model.addAttribute("clients", this.adminClientService.viewClientList());
		// 講座名一覧
		model.addAttribute("courses", this.adminCourseService.viewCourseList());
		// 権限Map
		model.addAttribute("AuthorityMap", Authority.selectAuthorityMap());

		Optional<Account> accountOptional = this.adminAccountService.viewupdateAccount(id);
		accountOptional.ifPresent(account -> {
			accountForm.setId(account.getId());
			accountForm.setName(account.getName());
			accountForm.setNameKana(account.getNameKana());
			accountForm.setLoginId(account.getLoginId());
			accountForm.setClientNameId(account.getClientNameId());
			accountForm.setCourseNameId(account.getCourseNameId());
			accountForm.setRole(account.getRole());
		});

		return "admin/account/updateaccount";
	}

	/**
	 * アカウント更新処理
	 * @param model         Modelクラス
	 * @param accountForm   Formクラス
	 * @param bindingResult バリデーションチェック
	 * @return              アカウント一覧画面
	 */
	@PostMapping("/update-account")
	public String updateAccount(Model model,
			@Validated(GroupOrder.class) @ModelAttribute("accountForm") AccountForm accountForm,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {

			return viewupdateAccount(accountForm.getId(), model, accountForm);
		}

		this.adminAccountService.updateAccount(accountForm);

		return "redirect:/admin/list-account?update";
	}

}
