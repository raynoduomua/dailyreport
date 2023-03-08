package com.example.dailyreport.infrastructure.repository.student;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.dailyreport.infrastructure.entity.student.StudentReport;

@Repository
public interface StudentReportRepository extends JpaRepository<StudentReport, Integer> {

	/**
	 * 今週受講生日報取得
	 * @param userId 受講生ID
	 * @param Monday 今週月曜日
	 * @param Friday 今週金曜日
	 * @return       今週受講生日報
	 */
	List<StudentReport> findByUserIdAndStudentsDateBetween(Integer userId, LocalDate Monday, LocalDate Friday);

	/**
	 * 本日の受講生日報が存在するか
	 * @param userId       受講生ID
	 * @param studentsDate 本日日付
	 * @return             true or false
	 */
	boolean existsByUserIdAndStudentsDate(Integer userId, LocalDate studentsDate);

	/**
	 * 当日受講生日報取得
	 * @param userId       受講生ID
	 * @param studentsDate 本日日付
	 * @return             本日の受講生日報
	 */
	Optional<StudentReport> findByUserIdAndStudentsDate(Integer userId, LocalDate studentsDate);

}
