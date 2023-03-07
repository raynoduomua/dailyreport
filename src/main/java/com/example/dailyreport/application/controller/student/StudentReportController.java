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
import com.example.dailyreport.application.common.utils.TeacherSupport;
import com.example.dailyreport.application.common.utils.UnderStand;
import com.example.dailyreport.application.form_validation.GroupOrder;
import com.example.dailyreport.application.form_validation.StudentCreateReportForm;
import com.example.dailyreport.domain.service.student.StudentReportService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/student")
@Slf4j
public class StudentReportController {

	private final StudentReportService studentReportService;

	/**
	 * 受講生日報作成画面表示
	 * @param model                   Modelクラス
	 * @param studentCreateReportForm Formクラス
	 * @return                        日報作成画面
	 */
	@GetMapping("/create-report")
	public String viewCreateStudentDailyReport(Model model,
			@ModelAttribute("studentCreateReportForm") StudentCreateReportForm studentCreateReportForm) {

		// 理解度
		model.addAttribute("underMap", UnderStand.selectUnderStandMap());
		// 講師対応
		model.addAttribute("teacherMap", TeacherSupport.selectTeacherSupportMap());

		return "student/createdailyreport";
	}

	/**
	 * 受講生日報登録処理
	 * @param model                   Modelクラス
	 * @param loginUser               ログイン中のユーザ情報
	 * @param studentCreateReportForm Formクラス
	 * @param bindingResult           バリデーションチェック
	 * @return                        受講生Home画面
	 */
	@PostMapping("/save-report")
	public String saveStudentDailyReport(Model model, @AuthenticationPrincipal LoginUser loginUser,
			@Validated(GroupOrder.class) @ModelAttribute("studentCreateReportForm") StudentCreateReportForm studentCreateReportForm,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {

			return viewCreateStudentDailyReport(model, studentCreateReportForm);
		}

		if (this.studentReportService.existsByStudentsDate(loginUser) == false) {

			this.studentReportService.saveStudentDailyReport(loginUser, studentCreateReportForm);

			return "redirect:/student/home?save";
		} else {

			return "redirect:/student/create-report?error";
		}

	}

	/**
	 * 受講生日報編集画面
	 * @param model                   Modelクラス
	 * @param loginUser               ログイン中のユーザ情報
	 * @param studentCreateReportForm Formクラス
	 * @return
	 */
	@GetMapping("/edit-report")
	public String viewUpdateStudentDailyReport(Model model, @AuthenticationPrincipal LoginUser loginUser,
			@ModelAttribute("studentCreateReportForm") StudentCreateReportForm studentCreateReportForm) {

		if (this.studentReportService.existsByStudentsDate(loginUser) == false) {

			return "redirect:/student/create-report?editerror";
		}

		studentCreateReportForm = this.studentReportService.viewUpdateStudentDailyReport(loginUser,
				studentCreateReportForm);

		// 理解度
		model.addAttribute("underMap", UnderStand.selectUnderStandMap());
		// 講師対応
		model.addAttribute("teacherMap", TeacherSupport.selectTeacherSupportMap());

		return "student/updatedailyreport";
	}

}
