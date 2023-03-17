package com.example.dailyreport.infrastructure.repository.teacher.miniexam;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.dailyreport.infrastructure.entity.teacher.miniexam.TeacherMiniExam;

@Repository
public interface TeacherMiniExamRepository extends JpaRepository<TeacherMiniExam, Integer> {

	boolean existsByUserIdAndTestId(Integer userId, Integer testId);

}
