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
import com.example.dailyreport.application.common.utils.TeacherSupport;
import com.example.dailyreport.application.common.utils.UnderStand;
import com.example.dailyreport.application.form_validation.PastStudentDailyReportForm;
import com.example.dailyreport.domain.service.admin.AdminClientService;
import com.example.dailyreport.domain.service.admin.AdminCourseService;
import com.example.dailyreport.domain.service.common.CommonService;
import com.example.dailyreport.domain.service.common.StudentReportPastService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/past")
public class StudentReportPastController {

	private final CommonService commonService;
	private final AdminCourseService adminCourseService;
	private final AdminClientService adminClientService;
	private final StudentReportPastService studentReportPastService;

	/**
	 * 受講生日報過去検索画面表示
	 * @param model                      Modelクラス
	 * @param loginUser                  ログイン中のユーザ情報
	 * @param pastStudentDailyReportForm Formクラス
	 * @return                           受講生日報過去検索画面
	 */
	@GetMapping("/student-daily-report")
	public String viewPastStudentDailyReport(Model model, @AuthenticationPrincipal LoginUser loginUser,
			@ModelAttribute("pastStudentDailyReportForm") PastStudentDailyReportForm pastStudentDailyReportForm) {

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
		// 理解度
		model.addAttribute("underMap", UnderStand.selectUnderStandMap());
		// 講師対応
		model.addAttribute("teacherMap", TeacherSupport.selectTeacherSupportMap());

		return "common/past/studentdailyreport";
	}

	/**
	 * 受講生日報過去検索後画面表示
	 * @param model                      Modelクラス
	 * @param loginUser                  ログイン中のユーザ情報
	 * @param pastStudentDailyReportForm Formクラス
	 * @return                           受講生日報過去検索後画面
	 */
	@PostMapping("/student-daily-report")
	public String pastStudentDailyReport(Model model, @AuthenticationPrincipal LoginUser loginUser,
			@ModelAttribute("pastStudentDailyReportForm") PastStudentDailyReportForm pastStudentDailyReportForm) {

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
		// 理解度
		model.addAttribute("underMap", UnderStand.selectUnderStandMap());
		// 講師対応
		model.addAttribute("teacherMap", TeacherSupport.selectTeacherSupportMap());

		if (pastStudentDailyReportForm.getFromDate() != null && pastStudentDailyReportForm.getToDate() != null
				&& !(pastStudentDailyReportForm.getFromDate().isBefore(pastStudentDailyReportForm.getToDate()))) {

			return "redirect:/past/student-daily-report?errordate";
		}
		// 受講生日報
		model.addAttribute("studentreports",
				this.studentReportPastService.pastStudentDailyReport(loginUser, pastStudentDailyReportForm));

		return "common/past/studentdailyreport";
	}

}
