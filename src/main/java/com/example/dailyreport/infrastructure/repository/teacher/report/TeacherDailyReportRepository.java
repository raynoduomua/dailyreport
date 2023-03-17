package com.example.dailyreport.infrastructure.repository.teacher.report;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.dailyreport.infrastructure.entity.teacher.report.TeacherDailyReport;

@Repository
public interface TeacherDailyReportRepository extends JpaRepository<TeacherDailyReport, Integer> {

	/**
	 * 本日の講師日報が存在するか
	 * @param courseId  講座名ID
	 * @param classDate 講義日
	 * @return          true or false
	 */
	boolean existsByCourseIdAndClassDate(Integer courseId, LocalDate classDate);

	Optional<TeacherDailyReport> findByCourseIdAndClassDate(Integer courseId, LocalDate classDate);

}
