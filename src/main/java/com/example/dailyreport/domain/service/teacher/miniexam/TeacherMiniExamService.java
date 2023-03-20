package com.example.dailyreport.domain.service.teacher.miniexam;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.dailyreport.application.common.utils.LocalDateNow;
import com.example.dailyreport.application.form_validation.TeacherMiniExamForm;
import com.example.dailyreport.infrastructure.dto.AccountAndUnittestsAndUnittestsname;
import com.example.dailyreport.infrastructure.entity.teacher.miniexam.TeacherMiniExam;
import com.example.dailyreport.infrastructure.mapper.AccountAndUnittestsAndUnittestsnameMapper;
import com.example.dailyreport.infrastructure.repository.teacher.miniexam.TeacherMiniExamRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TeacherMiniExamService {

	private final TeacherMiniExamRepository teacherMiniExamRepository;
	private final AccountAndUnittestsAndUnittestsnameMapper accountAndUnittestsAndUnittestsnameMapper;

	/**
	 * 存在チェック
	 * @param userId テーブル「unittests」 カラム「USER_ID」
	 * @param testId テーブル「unittests」 カラム「TEST_ID」
	 * @return       true or false
	 */
	public boolean existsByUserIdAndTestId(Integer userId, Integer testId) {

		return teacherMiniExamRepository.existsByUserIdAndTestId(userId, testId);
	}

	/**
	 * 単元テスト登録処理
	 * @param teacherMiniExamForm Formクラス
	 */
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

	/**
	 * 講師と同じ講座の受講生単元テスト情報
	 * @return 単元テスト情報
	 */
	public List<AccountAndUnittestsAndUnittestsname> findStudentUnitTests() {

		return this.accountAndUnittestsAndUnittestsnameMapper.findStudentUnitTests();
	}

	/**
	 * 単元テスト情報（1件）
	 * @param id テーブル「unittests」 カラム「ID」
	 * @return   単元テスト情報（1件）
	 */
	public Optional<TeacherMiniExam> findById(Integer id) {

		return this.teacherMiniExamRepository.findById(id);
	}

	/**
	 * 単元テスト編集画面
	 * @param id                  テーブル「unittests」 カラム「ID」
	 * @param teacherMiniExamForm Formクラス
	 * @return                    TeacherMiniExamFormクラス
	 */
	public TeacherMiniExamForm viewUpdateTeacherMiniExam(Integer id, TeacherMiniExamForm teacherMiniExamForm) {

		Optional<TeacherMiniExam> miniexamOptional = this.findById(id);
		miniexamOptional.ifPresent(miniexam -> {
			teacherMiniExamForm.setId(miniexam.getId());
			teacherMiniExamForm.setUserId(miniexam.getUserId());
			teacherMiniExamForm.setTestId(miniexam.getTestId());
			teacherMiniExamForm.setTestDate(miniexam.getTestDate());
			teacherMiniExamForm.setScore(miniexam.getScore());
			teacherMiniExamForm.setScoreAverage(miniexam.getScoreAverage());
		});

		return teacherMiniExamForm;
	}

	/**
	 * 単元テスト更新処理
	 * @param teacherMiniExamForm Formクラス
	 */
	public void updateTeacherMiniExam(TeacherMiniExamForm teacherMiniExamForm) {

		Optional<TeacherMiniExam> miniexamOptional = this.findById(teacherMiniExamForm.getId());
		miniexamOptional.ifPresent(miniexam -> {
			miniexam.setScore(teacherMiniExamForm.getScore());
			miniexam.setScoreAverage(teacherMiniExamForm.getScoreAverage());
			miniexam.setUpdatedAt(LocalDateNow.getLocalDateNow());

			this.teacherMiniExamRepository.save(miniexam);
		});
	}

}
