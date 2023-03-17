package com.example.dailyreport.domain.service.teacher.miniexam;

import org.springframework.stereotype.Service;

import com.example.dailyreport.application.common.utils.LocalDateNow;
import com.example.dailyreport.application.form_validation.TeacherMiniExamForm;
import com.example.dailyreport.infrastructure.entity.teacher.miniexam.TeacherMiniExam;
import com.example.dailyreport.infrastructure.repository.teacher.miniexam.TeacherMiniExamRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TeacherMiniExamService {

	private final TeacherMiniExamRepository teacherMiniExamRepository;

	public boolean existsByUserIdAndTestId(Integer userId, Integer testId) {

		return teacherMiniExamRepository.existsByUserIdAndTestId(userId, testId);
	}

	public void saveTeacherMiniExam(TeacherMiniExamForm teacherMiniExamForm) {

		TeacherMiniExam miniExam = new TeacherMiniExam();
		miniExam.setUserId(teacherMiniExamForm.getUserId());
		miniExam.setTestId(teacherMiniExamForm.getTestId());
		miniExam.setScore(teacherMiniExamForm.getScore());
		miniExam.setScoreAverage(teacherMiniExamForm.getScoreAverage());
		miniExam.setTestDate(teacherMiniExamForm.getTestDate());
		miniExam.setCreatedAt(LocalDateNow.getLocalDateNow());
		miniExam.setUpdatedAt(null);

		this.teacherMiniExamRepository.save(miniExam);
	}

}
