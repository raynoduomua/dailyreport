package com.example.dailyreport.domain.service.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.dailyreport.application.common.utils.LocalDateNow;
import com.example.dailyreport.application.form_validation.CourseForm;
import com.example.dailyreport.infrastructure.entity.admin.Course;
import com.example.dailyreport.infrastructure.repository.admin.AdminCourseRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminCourseService {

	private final AdminCourseRepository adminCourseRepository;

	/**
	 * 講座名登録処理
	 * @param courseForm Formクラス
	 */
	public void saveCourse(CourseForm courseForm) {

		Course course = new Course();
		course.setCourseName(courseForm.getCoursename());
		course.setCreatedAt(LocalDateNow.getLocalDateNow());

		this.adminCourseRepository.save(course);
	}

	/**
	 * 講座名一覧取得
	 * @return 講座名一覧
	 */
	public List<Course> viewCourseList() {

		return this.adminCourseRepository.findAll();
	}

	/**
	 * 講座名1件取得
	 * @param id テーブル「coursesname」 カラム「ID」
	 * @return   講座名1件
	 */
	public Optional<Course> viewupdateCourse(Integer id) {

		return this.adminCourseRepository.findById(id);
	}

	/**
	 * 講座名更新処理
	 * @param courseForm Formクラス
	 */
	public void updateCourse(CourseForm courseForm) {

		Optional<Course> courOptional = this.viewupdateCourse(courseForm.getId());
		courOptional.ifPresent(course -> {
			course.setCourseName(courseForm.getCoursename());
			course.setUpdatedAt(LocalDateNow.getLocalDateNow());

			this.adminCourseRepository.save(course);
		});
	}

	/**
	 * 講座数取得
	 * @return 講座数
	 */
	public Integer countCourse() {

		return (int) this.adminCourseRepository.count();
	}

}
