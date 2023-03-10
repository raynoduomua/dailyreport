package com.example.dailyreport.infrastructure.repository.teacher;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.dailyreport.infrastructure.entity.teacher.TeacherWeeklyReport;

public interface TeacherWeeklyReportRepository extends JpaRepository<TeacherWeeklyReport, Integer> {

	boolean existsByCourseIdAndUserIdAndClassWeekBetween(Integer courseId, Integer userId, LocalDate Monday,
			LocalDate Friday);

}
