package com.example.dailyreport.application.controller.teacher.miniexam;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.dailyreport.application.common.security.LoginUser;
import com.example.dailyreport.application.form_validation.GroupOrder;
import com.example.dailyreport.application.form_validation.TeacherMiniExamForm;
import com.example.dailyreport.domain.service.admin.AdminAccountService;
import com.example.dailyreport.domain.service.admin.AdminMiniExamService;
import com.example.dailyreport.domain.service.teacher.miniexam.TeacherMiniExamService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/teacher")
@Slf4j
public class TeacherMiniExamController {

	private final AdminAccountService adminAccountService;
	private final AdminMiniExamService adminMiniExamService;
	private final TeacherMiniExamService teacherMiniExamService;

	/**
	 * 単元テスト登録画面
	 * @param model               Modelクラス
	 * @param loginUser           ログイン中のユーザ情報
	 * @param teacherMiniExamForm Formクラス
	 * @return                    単元テスト登録画面
	 */
	@GetMapping("/create-mini-exam")
	public String viewTeacherMiniExam(Model model, @AuthenticationPrincipal LoginUser loginUser,
			@ModelAttribute("teacherMiniExamForm") TeacherMiniExamForm teacherMiniExamForm) {

		// 同じ講座の受講生取得
		model.addAttribute("students", this.adminAccountService.findByCourseNameIdAndRole(loginUser));
		// 単元テスト名取得
		model.addAttribute("miniexams", this.adminMiniExamService.viewMiniExamList());

		return "teacher/miniexam/saveminiexam";
	}

	/**
	 * 単元テスト登録処理
	 * @param model               Modelクラス
	 * @param loginUser           ログイン中のユーザ情報
	 * @param teacherMiniExamForm Formクラス
	 * @param bindingResult       バリデーションチェック
	 * @return                    単元テスト登録画面
	 */
	@PostMapping("/save-mini-exam")
	public String saveTeacherMiniExam(Model model, @AuthenticationPrincipal LoginUser loginUser,
			@Validated(GroupOrder.class) @ModelAttribute("teacherMiniExamForm") TeacherMiniExamForm teacherMiniExamForm,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {

			return this.viewTeacherMiniExam(model, loginUser, teacherMiniExamForm);
		}

		// 存在チェック
		if (this.teacherMiniExamService.existsByUserIdAndTestId(teacherMiniExamForm.getUserId(),
				teacherMiniExamForm.getTestId()) == false) {

			this.teacherMiniExamService.saveTeacherMiniExam(teacherMiniExamForm);

			return "redirect:/teacher/create-mini-exam?saveminiexam";
		} else {

			return "redirect:/teacher/create-mini-exam?saveminiexamerror";
		}

	}

}
