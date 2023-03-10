package com.example.dailyreport.application.controller.teacher;

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
import com.example.dailyreport.application.form_validation.TeacherWeeklyReportForm;
import com.example.dailyreport.domain.service.admin.AdminAccountService;
import com.example.dailyreport.domain.service.common.CommonService;
import com.example.dailyreport.domain.service.teacher.TeacherWeeklyReportService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/teacher")
@Slf4j
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
		// 本日日付
		model.addAttribute("today", LocalDateNow.getLocalDateNow());

		return "teacher/weekly/createweeklyreport";
	}

	@PostMapping("/save-weekly-report")
	public String saveTeacherWeeklyReport(Model model, @AuthenticationPrincipal LoginUser loginUser,
			@Validated(GroupOrder.class) @ModelAttribute("teacherWeeklyReportForm") TeacherWeeklyReportForm teacherWeeklyReportForm,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {

			return this.viewCreateTeacherWeeklyReport(model, loginUser, teacherWeeklyReportForm);
		}

		if (this.teacherWeeklyReportService.existsByCourseIdAndUserIdAndClassWeekBetween(loginUser,
				teacherWeeklyReportForm) == false) {

			log.info("teacherWeeklyReportForm: {}", teacherWeeklyReportForm);
			this.teacherWeeklyReportService.saveTeacherWeeklyReport(loginUser, teacherWeeklyReportForm);

			return "redirect:/teacher/create-weekly-report?saveweekly";
		} else {

			return "redirect:/teacher/create-weekly-report?saveweeklyerror";
		}

	}

}
