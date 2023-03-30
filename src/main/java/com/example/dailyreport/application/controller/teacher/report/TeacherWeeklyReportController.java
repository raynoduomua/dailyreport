package com.example.dailyreport.application.controller.teacher.report;

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
import com.example.dailyreport.application.form_validation.TeacherWeeklyReportForm;
import com.example.dailyreport.domain.service.admin.AdminAccountService;
import com.example.dailyreport.domain.service.common.CommonService;
import com.example.dailyreport.domain.service.teacher.report.TeacherWeeklyReportService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/teacher")
public class TeacherWeeklyReportController {

	private final TeacherWeeklyReportService teacherWeeklyReportService;
	private final AdminAccountService adminAccountService;
	private final CommonService commonService;

	/**
	 * 講師週報作成画面表示
	 * @param model                   Modelクラス
	 * @param loginUser               ログイン中のユーザ情報
	 * @param teacherWeeklyReportForm Formクラス
	 * @return                        講師週報作成画面
	 */
	@GetMapping("/create-weekly-report")
	public String viewCreateTeacherWeeklyReport(Model model, @AuthenticationPrincipal LoginUser loginUser,
			@ModelAttribute("teacherWeeklyReportForm") TeacherWeeklyReportForm teacherWeeklyReportForm) {

		// 同じ講座の受講生取得
		model.addAttribute("students", this.adminAccountService.findByCourseNameIdAndRole(loginUser));
		// ログイン中のユーザ情報取得
		model.addAttribute("loginAccount", this.commonService.viewAccountOneList(loginUser));
		// 今週月曜日
		model.addAttribute("monday", LocalDateNow.getLocalDateMonday());
		// 今週金曜日
		model.addAttribute("friday", LocalDateNow.getLocalDateFriday());

		return "teacher/weekly/createweeklyreport";
	}

	/**
	 * 講師週報登録処理
	 * @param model                   Modelクラス
	 * @param loginUser               ログイン中のユーザ情報
	 * @param teacherWeeklyReportForm Foprmクラス
	 * @param bindingResult           バリデーションチェック
	 * @return                        存在しない：講師週報作成画面
	 *                                存在する　：講師週報作成画面
	 */
	@PostMapping("/save-weekly-report")
	public String saveTeacherWeeklyReport(Model model, @AuthenticationPrincipal LoginUser loginUser,
			@Validated(GroupOrder.class) @ModelAttribute("teacherWeeklyReportForm") TeacherWeeklyReportForm teacherWeeklyReportForm,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {

			return this.viewCreateTeacherWeeklyReport(model, loginUser, teacherWeeklyReportForm);
		}

		// 対象受講生の講師週報存在check
		if (this.teacherWeeklyReportService.existsByCourseIdAndUserIdAndClassWeekBetween(loginUser,
				teacherWeeklyReportForm) == false) {

			this.teacherWeeklyReportService.saveTeacherWeeklyReport(loginUser, teacherWeeklyReportForm);

			return "redirect:/teacher/create-weekly-report?saveweekly";
		} else {

			return "redirect:/teacher/create-weekly-report?saveweeklyerror";
		}

	}

	/**
	 * 講師と同じ講座の講師週報取得（対象：今週入力分）
	 * @param model     Modelクラス
	 * @param loginUser ログイン中のユーザ情報
	 * @return          講師週報編集一覧画面
	 */
	@GetMapping("/edit-weekly-report")
	public String viewEditTeacherWeeklyReport(Model model, @AuthenticationPrincipal LoginUser loginUser) {

		model.addAttribute("reports",
				this.teacherWeeklyReportService.findbyUserIdAndCourseIdAndClassWeekBetween(loginUser));
		// ログイン中のユーザ情報取得
		model.addAttribute("loginAccount", this.commonService.viewAccountOneList(loginUser));
		// 今週月曜日
		model.addAttribute("monday", LocalDateNow.getLocalDateMonday());
		// 今週金曜日
		model.addAttribute("friday", LocalDateNow.getLocalDateFriday());

		return "teacher/weekly/editweeklyreport";
	}

	/**
	 * 講師週報編集画面表示
	 * @param id                      テーブル「teachersweeklyreports」 カラム「userId」
	 * @param model                   Modelクラス
	 * @param loginUser               ログイン中のユーザ情報
	 * @param teacherWeeklyReportForm Formクラス
	 * @return                        講師週報編集画面
	 */
	@GetMapping("/serch-weekly-report/{id}")
	public String viewUpdateTeacherWeeklyReport(@PathVariable Integer id, Model model,
			@AuthenticationPrincipal LoginUser loginUser,
			@ModelAttribute("teacherWeeklyReportForm") TeacherWeeklyReportForm teacherWeeklyReportForm) {

		teacherWeeklyReportForm = this.teacherWeeklyReportService.viewUpdateTeacherWeeklyReport(id, loginUser,
				teacherWeeklyReportForm);
		// 同じ講座の受講生取得
		model.addAttribute("students", this.adminAccountService.findByCourseNameIdAndRole(loginUser));
		// ログイン中のユーザ情報取得
		model.addAttribute("loginAccount", this.commonService.viewAccountOneList(loginUser));
		// 今週月曜日
		model.addAttribute("monday", LocalDateNow.getLocalDateMonday());
		// 今週金曜日
		model.addAttribute("friday", LocalDateNow.getLocalDateFriday());

		return "teacher/weekly/updateweeklyreport";
	}

	/**
	 * 対象受講生の講師週報更新処理
	 * @param model                   Modelクラス
	 * @param loginUser               ログイン中のユーザ情報
	 * @param teacherWeeklyReportForm Formクラス
	 * @param bindingResult           バリデーションチェック
	 * @return                        講師週報編集一覧画面
	 */
	@PostMapping("/update-weekly-report")
	public String updateTeacherWeeklyReport(Model model, @AuthenticationPrincipal LoginUser loginUser,
			@Validated(GroupOrder.class) @ModelAttribute("teacherWeeklyReportForm") TeacherWeeklyReportForm teacherWeeklyReportForm,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {

			return viewUpdateTeacherWeeklyReport(teacherWeeklyReportForm.getUserId(), model, loginUser,
					teacherWeeklyReportForm);
		}

		this.teacherWeeklyReportService.updateTeacherWeeklyReport(teacherWeeklyReportForm);

		return "redirect:/teacher/edit-weekly-report?updateweekly";
	}

}
