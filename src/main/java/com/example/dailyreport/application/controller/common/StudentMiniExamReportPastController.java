package com.example.dailyreport.application.controller.common;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.dailyreport.application.common.security.LoginUser;
import com.example.dailyreport.application.common.utils.LocalDateNow;
import com.example.dailyreport.application.form_validation.PastStudentMiniExamReportForm;
import com.example.dailyreport.domain.service.admin.AdminClientService;
import com.example.dailyreport.domain.service.admin.AdminCourseService;
import com.example.dailyreport.domain.service.common.CommonService;
import com.example.dailyreport.domain.service.common.StudentMiniExamReportPastService;
import com.example.dailyreport.domain.service.common.StudentReportPastService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/past")
public class StudentMiniExamReportPastController {

	private final CommonService commonService;
	private final AdminCourseService adminCourseService;
	private final AdminClientService adminClientService;
	private final StudentReportPastService studentReportPastService;
	private final StudentMiniExamReportPastService studentMiniExamReportPastService;

	/**
	 * 単元テスト結果画面
	 * @param model                         Modelクラス
	 * @param loginUser                     ログイン中のユーザ情報
	 * @param pastStudentMiniExamReportForm Formクラス
	 * @return                              単元テスト結果画面
	 */
	@GetMapping("/student-mini-exam-report")
	public String viewPastStudentMiniExamReport(Model model, @AuthenticationPrincipal LoginUser loginUser,
			@ModelAttribute("pastStudentMiniExamReportForm") PastStudentMiniExamReportForm pastStudentMiniExamReportForm) {

		// ログイン中のユーザ情報取得
		model.addAttribute("loginAccount", this.commonService.viewAccountOneList(loginUser));
		// 本日日付
		model.addAttribute("today", LocalDateNow.getLocalDateNow());
		// 講座名一覧
		model.addAttribute("courses", this.adminCourseService.viewCourseList());
		// クライアント名一覧
		model.addAttribute("clients", this.adminClientService.viewClientList());
		// 受講生一覧
		model.addAttribute("students", this.studentReportPastService.viewAccountList(loginUser));

		return "common/past/miniexam";
	}

	/**
	 * 単元テスト結果検索
	 * @param model                         Modelクラス
	 * @param loginUser                     ログイン中のユーザ情報
	 * @param pastStudentMiniExamReportForm Formクラス
	 * @return                              単元テスト結果画面
	 */
	@PostMapping("/student-mini-exam-report")
	public String pastStudentMiniExamReport(Model model, @AuthenticationPrincipal LoginUser loginUser,
			@ModelAttribute("pastStudentMiniExamReportForm") PastStudentMiniExamReportForm pastStudentMiniExamReportForm) {

		// ログイン中のユーザ情報取得
		model.addAttribute("loginAccount", this.commonService.viewAccountOneList(loginUser));
		// 本日日付
		model.addAttribute("today", LocalDateNow.getLocalDateNow());
		// 講座名一覧
		model.addAttribute("courses", this.adminCourseService.viewCourseList());
		// クライアント名一覧
		model.addAttribute("clients", this.adminClientService.viewClientList());
		// 受講生一覧
		model.addAttribute("students", this.studentReportPastService.viewAccountList(loginUser));
		// 単元テスト一覧
		model.addAttribute("studentreports", this.studentMiniExamReportPastService.pastStudentMiniExamReport(loginUser,
				pastStudentMiniExamReportForm));

		return "common/past/miniexam";
	}
}
