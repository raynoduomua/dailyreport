package com.example.dailyreport.application.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.dailyreport.application.form_validation.GroupOrder;
import com.example.dailyreport.application.form_validation.MiniExamNameForm;
import com.example.dailyreport.domain.service.admin.AdminMiniExamService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminMiniExamController {

	private final AdminMiniExamService adminMiniExamService;

	/**
	 * 単元テスト名作成画面
	 * @param miniExamNameForm Formクラス
	 * @return                 単元テスト名作成画面
	 */
	@GetMapping("/create-mini-exam")
	public String viewCreateMiniExam(@ModelAttribute("miniExamNameForm") MiniExamNameForm miniExamNameForm) {

		return "admin/miniexam/createminiexam";
	}

	/**
	 * 単元テスト名登録処理
	 * @param miniExamNameForm Formクラス
	 * @param bindingResult    バリデーションチェック
	 * @return                 単元テスト名作成画面
	 */
	@PostMapping("/save-mini-exam")
	public String saveMiniExam(
			@Validated(GroupOrder.class) @ModelAttribute("miniExamNameForm") MiniExamNameForm miniExamNameForm,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {

			return viewCreateMiniExam(miniExamNameForm);
		}

		this.adminMiniExamService.saveMiniExam(miniExamNameForm);

		return "redirect:/admin/create-mini-exam?save";
	}

	/**
	 * 単元テスト名一覧画面
	 * @param model Modelクラス
	 * @return      単元テスト名一覧画面
	 */
	@GetMapping("/list-mini-exam")
	public String viewMiniExamList(Model model) {

		model.addAttribute("tests", this.adminMiniExamService.viewMiniExamList());

		return "admin/list/miniexam";
	}

	@GetMapping("/edit-mini-exam/{id}")
	public String viewupdateMiniExam(@PathVariable Integer id,
			@ModelAttribute("miniExamNameForm") MiniExamNameForm miniExamNameForm) {

		miniExamNameForm = this.adminMiniExamService.viewupdateMiniExam(id, miniExamNameForm);

		return "admin/miniexam/updateminiexam";
	}

	@PostMapping("/update-mini-exam")
	public String updateMiniExam(
			@Validated(GroupOrder.class) @ModelAttribute("miniExamNameForm") MiniExamNameForm miniExamNameForm,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {

			return this.viewupdateMiniExam(miniExamNameForm.getId(), miniExamNameForm);
		}

		this.adminMiniExamService.updateMiniExam(miniExamNameForm);

		return "redirect:/admin/list-mini-exam?update";
	}

}
