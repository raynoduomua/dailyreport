package com.example.dailyreport.application.controller.teacher.report;

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
import com.example.dailyreport.application.common.utils.LocalDateNow;
import com.example.dailyreport.application.form_validation.GroupOrder;
import com.example.dailyreport.application.form_validation.SearchDateForm;
import com.example.dailyreport.application.form_validation.TeacherDailyReportForm;
import com.example.dailyreport.domain.service.common.CommonService;
import com.example.dailyreport.domain.service.teacher.report.TeacherDailyReportService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/teacher")
public class TeacherDailyReportController {

	private final CommonService commonService;
	private final TeacherDailyReportService teacherDailyReportService;

	/**
	 * 講師日報作成画面表示
	 * @param model                  Modelクラス
	 * @param loginUser              ログイン中のユーザ情報
	 * @param teacherDailyReportForm Formクラス
	 * @param searchDateForm         Formクラス
	 * @return                       講師日報作成画面
	 */
	@GetMapping("/create-daily-report")
	public String viewCreateTeacherDailyReport(Model model, @AuthenticationPrincipal LoginUser loginUser,
			@ModelAttribute("teacherDailyReportForm") TeacherDailyReportForm teacherDailyReportForm,
			@ModelAttribute("searchDateForm") SearchDateForm searchDateForm) {

		// ログイン中のユーザ情報取得
		model.addAttribute("loginAccount", this.commonService.viewAccountOneList(loginUser));
		// 本日日付
		model.addAttribute("today", LocalDateNow.getLocalDateNow());

		return "teacher/daily/createdailyreport";
	}

	/**
	 * 講師日報検索後画面表示
	 * @param model                  Modelクラス
	 * @param loginUser              ログイン中のユーザ情報
	 * @param teacherDailyReportForm Formクラス
	 * @param searchDateForm         Formクラス
	 * @param bindingResult          バリデーションチェック
	 * @return                       講師日報検索後画面
	 */
	@PostMapping("/search-report")
	public String searchTeacherDailyReport(Model model, @AuthenticationPrincipal LoginUser loginUser,
			@ModelAttribute("teacherDailyReportForm") TeacherDailyReportForm teacherDailyReportForm,
			@Validated(GroupOrder.class) @ModelAttribute("searchDateForm") SearchDateForm searchDateForm,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {

			return this.viewCreateTeacherDailyReport(model, loginUser, teacherDailyReportForm, searchDateForm);
		}

		if (searchDateForm.getStudentsDate().isAfter(LocalDateNow.getLocalDateNow())) {

			return "redirect:/teacher/create-daily-report?searcherror";
		}

		// ログイン中のユーザ情報取得
		model.addAttribute("loginAccount", this.commonService.viewAccountOneList(loginUser));
		// 本日日付
		model.addAttribute("today", LocalDateNow.getLocalDateNow());

		teacherDailyReportForm = this.teacherDailyReportService.searchTeacherDailyReport(loginUser,
				teacherDailyReportForm);

		return "teacher/daily/createdailyreport";
	}

	/**
	 */
	/**
	 * 講師日報登録処理
	 * @param model                  Modelクラス
	 * @param loginUser              ログイン中のユーザ情報
	 * @param searchDateForm         Formクラス
	 * @param teacherDailyReportForm Formクラス
	 * @param bindingResult          バリデーションチェック
	 * @return                       false：登録
	 *                               true ：更新
	 */
	@PostMapping("/save-daily-report")
	public String saveTeacherDailyReport(Model model, @AuthenticationPrincipal LoginUser loginUser,
			@ModelAttribute("searchDateForm") SearchDateForm searchDateForm,
			@Validated(GroupOrder.class) @ModelAttribute("teacherDailyReportForm") TeacherDailyReportForm teacherDailyReportForm,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {

			return this.searchTeacherDailyReport(model, loginUser, teacherDailyReportForm, searchDateForm,
					bindingResult);
		}

		// 該当日の講師日報存在check
		if (this.teacherDailyReportService.existsByCourseIdAndClassDate(loginUser, teacherDailyReportForm) == false) {

			this.teacherDailyReportService.saveTeacherDailyReport(loginUser, teacherDailyReportForm);

			return "redirect:/teacher/create-daily-report?save";
		} else {

			this.teacherDailyReportService.updateTeacherDailyReport(loginUser, teacherDailyReportForm);

			return "redirect:/teacher/create-daily-report?update";
		}

	}

}
