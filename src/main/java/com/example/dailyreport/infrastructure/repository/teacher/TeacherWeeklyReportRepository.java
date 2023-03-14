package com.example.dailyreport.infrastructure.repository.teacher;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.dailyreport.infrastructure.entity.teacher.TeacherWeeklyReport;

public interface TeacherWeeklyReportRepository extends JpaRepository<TeacherWeeklyReport, Integer> {

	/**
	 * 対象受講生の講師週報存在チェック
	 * @param courseId テーブル「teachersweeklyreports」 カラム「COURSE_ID」
	 * @param userId   テーブル「teachersweeklyreports」 カラム「USER_ID」
	 * @param Monday   今週月曜日
	 * @param Friday   今週金曜日
	 * @return         true or false
	 */
	boolean existsByCourseIdAndUserIdAndClassWeekBetween(Integer courseId, Integer userId, LocalDate Monday,
			LocalDate Friday);

}
