package com.example.dailyreport.application.controller.admin;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.dailyreport.application.form_validation.CourseForm;
import com.example.dailyreport.application.form_validation.GroupOrder;
import com.example.dailyreport.domain.service.admin.AdminCourseService;
import com.example.dailyreport.infrastructure.entity.admin.Course;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminCourseController {

	private final AdminCourseService adminCourseService;

	/**
	 * 講座名登録画面
	 * http://localhost:8080/admin/create-course
	 * @param courseForm Fornmクラス
	 * @return           講座名登録画面
	 */
	@GetMapping("/create-course")
	public String viewCreateCourse(@ModelAttribute("courseForm") CourseForm courseForm) {

		return "admin/course/createcourse";
	}

	/**
	 * 講座名登録処理
	 * @param courseForm    Formクラス
	 * @param bindingResult バリデーションチェック
	 * @return              講座名登録画面
	 */
	@PostMapping("/save-course")
	public String saveCourse(@Validated(GroupOrder.class) @ModelAttribute("courseForm") CourseForm courseForm,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return viewCreateCourse(courseForm);
		}

		this.adminCourseService.saveCourse(courseForm);

		return "redirect:/admin/create-course?save";
	}

	/**
	 * 講座名一覧画面
	 * http://localhost:8080/admin/list-course
	 * @param model Modelクラス
	 * @return      講座名一覧画面
	 */
	@GetMapping("/list-course")
	public String viewCourseList(Model model) {

		model.addAttribute("courses", this.adminCourseService.viewCourseList());

		return "admin/list/course";
	}

	/**
	 * 講座名編集画面
	 * @param id         テーブル「coursesname」 カラム「ID」
	 * @param courseForm Formクラス
	 * @return           講座名編集画面
	 */
	@GetMapping("/edit/{id}")
	public String viewupdateCourse(@PathVariable Integer id, @ModelAttribute("courseForm") CourseForm courseForm) {

		Optional<Course> courseOptional = this.adminCourseService.viewupdateCourse(id);
		courseOptional.ifPresent(course -> {
			courseForm.setId(course.getId());
			courseForm.setCoursename(course.getCourseName());
		});

		return "admin/course/updatecourse";
	}

	/**
	 * 講座名更新処理
	 * @param courseForm    Formクラス
	 * @param bindingResult バリデーションチェック
	 * @return              講座名一覧画面
	 */
	@PostMapping("/update-course")
	public String updateCourse(@Validated(GroupOrder.class) @ModelAttribute("courseForm") CourseForm courseForm,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return viewupdateCourse(courseForm.getId(), courseForm);
		}

		this.adminCourseService.updateCourse(courseForm);

		return "redirect:/admin/list-course?update";
	}

}
