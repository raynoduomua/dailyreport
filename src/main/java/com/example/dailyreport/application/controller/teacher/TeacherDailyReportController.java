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
import com.example.dailyreport.application.form_validation.TeacherDailyReportForm;
import com.example.dailyreport.domain.service.common.CommonService;
import com.example.dailyreport.domain.service.teacher.TeacherDailyReportService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@RequestMapping("/teacher")
@Slf4j
public class TeacherDailyReportController {

	private final CommonService commonService;
	private final TeacherDailyReportService teacherDailyReportService;

	@GetMapping("/create-daily-report")
	public String viewCreateTeacherDailyReport(Model model, @AuthenticationPrincipal LoginUser loginUser,
			@ModelAttribute("teacherDailyReportForm") TeacherDailyReportForm teacherDailyReportForm) {

		// ログイン中のユーザ情報取得
		model.addAttribute("loginAccount", this.commonService.viewAccountOneList(loginUser));

		// 本日日付
		model.addAttribute("today", LocalDateNow.getLocalDateNow());

		return "teacher/daily/createdailyreport";
	}

	/**
	 * 講師日報登録処理
	 * @param model                  Modelクラス
	 * @param loginUser              ログイン中のユーザ情報
	 * @param teacherDailyReportForm Formクラス
	 * @param bindingResult          バリデーションチェック
	 * @return                       講師日報登録作成画面
	 */
	@PostMapping("/save-daily-report")
	public String saveTeacherDailyReport(Model model, @AuthenticationPrincipal LoginUser loginUser,
			@Validated(GroupOrder.class) @ModelAttribute("teacherDailyReportForm") TeacherDailyReportForm teacherDailyReportForm,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {

			return this.viewCreateTeacherDailyReport(model, loginUser, teacherDailyReportForm);
		}

		// 本日の講師日報存在check
		if (this.teacherDailyReportService.existsByCourseIdAndClassDate(loginUser) == false) {

			this.teacherDailyReportService.saveTeacherDailyReport(loginUser, teacherDailyReportForm);

			return "redirect:/teacher/create-daily-report?save";
		} else {

			return "redirect:/teacher/create-daily-report?saveerror";
		}

	}

}
