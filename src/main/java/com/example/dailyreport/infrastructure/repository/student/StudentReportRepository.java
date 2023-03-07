package com.example.dailyreport.infrastructure.repository.student;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.dailyreport.infrastructure.entity.student.StudentReport;

@Repository
public interface StudentReportRepository extends JpaRepository<StudentReport, Integer> {

	List<StudentReport> findByUserIdAndStudentsDateBetween(Integer userId, LocalDate Monday, LocalDate Friday);

}
