package com.example.dailyreport.application.controller.student;

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
import com.example.dailyreport.application.common.utils.TeacherSupport;
import com.example.dailyreport.application.common.utils.UnderStand;
import com.example.dailyreport.application.form_validation.GroupOrder;
import com.example.dailyreport.application.form_validation.SearchDateForm;
import com.example.dailyreport.application.form_validation.StudentCreateReportForm;
import com.example.dailyreport.domain.service.common.CommonService;
import com.example.dailyreport.domain.service.student.StudentReportService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentReportController {

	private final StudentReportService studentReportService;
	private final CommonService commonService;

	/**
	 * 受講生日報作成画面表示
	 * @param model                   Modelクラス
	 * @param loginUser               ログイン中のユーザ情報
	 * @param studentCreateReportForm Formクラス
	 * @param searchDateForm          Formクラス
	 * @return                        受講生日報作成画面
	 */
	@GetMapping("/create-report")
	public String viewCreateStudentDailyReport(Model model, @AuthenticationPrincipal LoginUser loginUser,
			@ModelAttribute("studentCreateReportForm") StudentCreateReportForm studentCreateReportForm,
			@ModelAttribute("searchDateForm") SearchDateForm searchDateForm) {

		// ログイン中のユーザ情報取得
		model.addAttribute("loginAccount", commonService.viewAccountOneList(loginUser));
		// 本日日付
		model.addAttribute("today", LocalDateNow.getLocalDateNow());
		// 理解度
		model.addAttribute("underMap", UnderStand.selectUnderStandMap());
		// 講師対応
		model.addAttribute("teacherMap", TeacherSupport.selectTeacherSupportMap());

		return "student/report/createdailyreport";
	}

	/**
	 * 受講生日報検索後画面表示
	 * @param model                   Modelクラス
	 * @param loginUser               ログイン中のユーザ情報
	 * @param studentCreateReportForm Formクラス
	 * @param searchDateForm          Formクラス
	 * @param bindingResult           バリデーションチェック
	 * @return                        受講生日報検索後画面
	 */
	@PostMapping("/search-report")
	public String searchStudentDailyReport(Model model, @AuthenticationPrincipal LoginUser loginUser,
			@ModelAttribute("studentCreateReportForm") StudentCreateReportForm studentCreateReportForm,
			@Validated(GroupOrder.class) @ModelAttribute("searchDateForm") SearchDateForm searchDateForm,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {

			return this.viewCreateStudentDailyReport(model, loginUser, studentCreateReportForm, searchDateForm);
		}

		if (searchDateForm.getStudentsDate().isAfter(LocalDateNow.getLocalDateNow())) {

			return "redirect:/student/create-report?searcherror";
		}

		// ログイン中のユーザ情報取得
		model.addAttribute("loginAccount", commonService.viewAccountOneList(loginUser));
		// 本日日付
		model.addAttribute("today", LocalDateNow.getLocalDateNow());
		// 理解度
		model.addAttribute("underMap", UnderStand.selectUnderStandMap());
		// 講師対応
		model.addAttribute("teacherMap", TeacherSupport.selectTeacherSupportMap());

		studentCreateReportForm = this.studentReportService.searchStudentDailyReport(loginUser,
				studentCreateReportForm);

		return "student/report/createdailyreport";
	}

	/**
	 * 受講生日報登録・更新処理
	 * @param model                   Modelクラス
	 * @param loginUser               ログイン中のユーザ情報
	 * @param studentCreateReportForm Formクラス
	 * @param bindingResult           バリデーションチェック
	 * @return                        false：登録
	 *                                true ：更新
	 */
	@PostMapping("/save-report")
	public String saveStudentDailyReport(Model model, @AuthenticationPrincipal LoginUser loginUser,
			@ModelAttribute("searchDateForm") SearchDateForm searchDateForm,
			@Validated(GroupOrder.class) @ModelAttribute("studentCreateReportForm") StudentCreateReportForm studentCreateReportForm,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {

			return this.searchStudentDailyReport(model, loginUser, studentCreateReportForm, searchDateForm,
					bindingResult);
		}

		// 該当日の受講生日報存在check
		if (this.studentReportService.existsByStudentsDate(loginUser, studentCreateReportForm) == false) {

			this.studentReportService.saveStudentDailyReport(loginUser, studentCreateReportForm);

			return "redirect:/student/home?save";
		} else {

			this.studentReportService.updateStudentDailyReport(loginUser, studentCreateReportForm);

			return "redirect:/student/home?update";
		}
	}

}
