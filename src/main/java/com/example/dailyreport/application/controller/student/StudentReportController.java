package com.example.dailyreport.application.controller.student;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

	@GetMapping("/create-report")
	public String viewCreateStudentDailyReport(Model model,
			@ModelAttribute("studentCreateReportForm") StudentCreateReportForm studentCreateReportForm) {

		// 理解度
		model.addAttribute("underMap", UnderStand.selectUnderStandMap());
		// 講師対応
		model.addAttribute("teacherMap", TeacherSupport.selectTeacherSupportMap());

		return "student/createdailyreport";
	}

	@PostMapping("/save-report")
	public String saveStudentDailyReport(Model model,
			@Validated(GroupOrder.class) @ModelAttribute("studentCreateReportForm") StudentCreateReportForm studentCreateReportForm,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {

			return viewCreateStudentDailyReport(model, studentCreateReportForm);
		}

		log.info("studentCreateReportForm: {}", studentCreateReportForm);
		this.studentReportService.saveStudentDailyReport(studentCreateReportForm);

		return "redirect:/student/create-report";
	}

}
