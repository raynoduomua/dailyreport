package com.example.dailyreport.application.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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

	@GetMapping("/create-mini-exam")
	public String viewCreateMiniExam(@ModelAttribute("miniExamNameForm") MiniExamNameForm miniExamNameForm) {

		return "admin/miniexam/createminiexam";
	}

	@PostMapping("/save-mini-exam")
	public String saveMiniExam(@Validated(GroupOrder.class) @ModelAttribute MiniExamNameForm miniExamNameForm,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {

			return viewCreateMiniExam(miniExamNameForm);
		}

		this.adminMiniExamService.saveMiniExam(miniExamNameForm);

		return "redirect:/admin/create-mini-exam?save";
	}

	@GetMapping("/list-mini-exam")
	public String viewMiniExamList(Model model) {

		model.addAttribute("tests", this.adminMiniExamService.viewMiniExamList());

		return "admin/list/miniexam";
	}

}
