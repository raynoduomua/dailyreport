package com.example.dailyreport.infrastructure.repository.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.dailyreport.infrastructure.entity.student.StudentReport;

@Repository
public interface StudentReportRepository extends JpaRepository<StudentReport, Integer> {

}
