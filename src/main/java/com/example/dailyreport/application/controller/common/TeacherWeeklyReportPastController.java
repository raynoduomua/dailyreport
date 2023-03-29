package com.example.dailyreport.application.controller.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/past")
public class TeacherWeeklyReportPastController {

	@GetMapping("/teacher-weekly-report")
	public String viewPastTeacherWeeklyReport() {

		return "common/past/teacherweeklyreport";
	}
}
