package com.example.dailyreport.application.controller.teacher.miniexam;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.dailyreport.application.common.security.LoginUser;
import com.example.dailyreport.application.common.utils.LocalDateNow;
import com.example.dailyreport.application.form_validation.GroupOrder;
import com.example.dailyreport.application.form_validation.TeacherMiniExamForm;
import com.example.dailyreport.domain.service.admin.AdminAccountService;
import com.example.dailyreport.domain.service.admin.AdminMiniExamService;
import com.example.dailyreport.domain.service.common.CommonService;
import com.example.dailyreport.domain.service.teacher.miniexam.TeacherMiniExamService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/teacher")
public class TeacherMiniExamController {

	private final AdminAccountService adminAccountService;
	private final AdminMiniExamService adminMiniExamService;
	private final TeacherMiniExamService teacherMiniExamService;
	private final CommonService commonService;

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

		// ログイン中のユーザ情報取得
		model.addAttribute("loginAccount", this.commonService.viewAccountOneList(loginUser));
		// 本日日付
		model.addAttribute("today", LocalDateNow.getLocalDateNow());
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

	/**
	 * 単元テスト編集画面
	 * @param model     Modelクラス
	 * @param loginUser ログイン中のユーザ情報
	 * @return          単元テスト編集画面
	 */
	@GetMapping("/edit-mini-exam")
	public String viewEditTeacherMiniExam(Model model, @AuthenticationPrincipal LoginUser loginUser) {

		// ログイン中のユーザ情報取得
		model.addAttribute("loginAccount", this.commonService.viewAccountOneList(loginUser));
		// 本日日付
		model.addAttribute("today", LocalDateNow.getLocalDateNow());
		// 単元テスト一覧
		model.addAttribute("miniexams", this.teacherMiniExamService.findStudentUnitTests());

		return "teacher/miniexam/editminiexam";
	}

	/**
	 * 単元テスト編集画面
	 * @param id                  テーブル「unittests」 カラム「ID」
	 * @param model               Modelクラス
	 * @param loginUser           ログイン中のユーザ情報
	 * @param teacherMiniExamForm Formクラス
	 * @return                    単元テスト編集画面
	 */
	@GetMapping("/serch-mini-exam/{id}")
	public String viewUpdateTeacherMiniExam(@PathVariable Integer id, Model model,
			@AuthenticationPrincipal LoginUser loginUser,
			@ModelAttribute("teacherMiniExamForm") TeacherMiniExamForm teacherMiniExamForm) {

		// ログイン中のユーザ情報取得
		model.addAttribute("loginAccount", this.commonService.viewAccountOneList(loginUser));
		// 本日日付
		model.addAttribute("today", LocalDateNow.getLocalDateNow());
		// 同じ講座の受講生取得
		model.addAttribute("students", this.adminAccountService.findByCourseNameIdAndRole(loginUser));
		// 単元テスト名取得
		model.addAttribute("miniexams", this.adminMiniExamService.viewMiniExamList());

		teacherMiniExamForm = this.teacherMiniExamService.viewUpdateTeacherMiniExam(id, teacherMiniExamForm);

		return "teacher/miniexam/updateminiexam";
	}

	/**
	 * 単元テスト更新処理
	 * @param model               Modelクラス
	 * @param loginUser           ログイン中のユーザ情報
	 * @param teacherMiniExamForm Formクラス
	 * @param bindingResult       バリデーションチェック
	 * @return                    単元テスト編集画面
	 */
	@PostMapping("/update-mini-exam")
	public String updateTeacherMiniExam(Model model, @AuthenticationPrincipal LoginUser loginUser,
			@Validated(GroupOrder.class) @ModelAttribute("teacherMiniExamForm") TeacherMiniExamForm teacherMiniExamForm,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {

			return this.viewUpdateTeacherMiniExam(teacherMiniExamForm.getId(), model, loginUser, teacherMiniExamForm);
		}

		this.teacherMiniExamService.updateTeacherMiniExam(teacherMiniExamForm);

		return "redirect:/teacher/edit-mini-exam?updateminiexam";
	}

}
