package com.example.dailyreport.domain.service.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.dailyreport.application.common.utils.LocalDateNow;
import com.example.dailyreport.application.form_validation.MiniExamNameForm;
import com.example.dailyreport.infrastructure.entity.admin.MiniExamName;
import com.example.dailyreport.infrastructure.repository.admin.AdminMiniExamRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminMiniExamService {

	private final AdminMiniExamRepository adminMiniExamRepository;

	/**
	 * 単元テスト名登録処理
	 * @param miniExamNameForm Formクラス
	 */
	public void saveMiniExam(MiniExamNameForm miniExamNameForm) {

		MiniExamName miniExamName = new MiniExamName();
		miniExamName.setTestName(miniExamNameForm.getTestName());
		miniExamName.setCreatedAt(LocalDateNow.getLocalDateNow());
		miniExamName.setUpdatedAt(null);

		this.adminMiniExamRepository.save(miniExamName);
	}

	/**
	 * 単元テスト名一覧取得
	 * @return 単元テスト名一覧
	 */
	public List<MiniExamName> viewMiniExamList() {

		return this.adminMiniExamRepository.findAll();
	}

	/**
	 * 単元テスト名1件取得
	 * @param id テーブル「unittestsname」 カラム「ID」
	 * @return   単元テスト名1件
	 */
	public Optional<MiniExamName> findById(Integer id) {

		return this.adminMiniExamRepository.findById(id);
	}

	/**
	 * 単元テスト名編集
	 * @param id               テーブル「unittestsname」 カラム「ID」
	 * @param miniExamNameForm Formクラス
	 * @return                 単元テスト名
	 */
	public MiniExamNameForm viewupdateMiniExam(Integer id, MiniExamNameForm miniExamNameForm) {

		Optional<MiniExamName> miniExamOptional = this.findById(id);
		miniExamOptional.ifPresent(miniexam -> {
			miniExamNameForm.setId(miniexam.getId());
			miniExamNameForm.setTestName(miniexam.getTestName());
		});

		return miniExamNameForm;
	}

	/**
	 * 単元テスト名更新処理
	 * @param miniExamNameForm Formクラス
	 */
	public void updateMiniExam(MiniExamNameForm miniExamNameForm) {

		Optional<MiniExamName> miniexamOptional = this.findById(miniExamNameForm.getId());
		miniexamOptional.ifPresent(miniexam -> {
			miniexam.setTestName(miniExamNameForm.getTestName());
			miniexam.setUpdatedAt(LocalDateNow.getLocalDateNow());

			this.adminMiniExamRepository.save(miniexam);
		});
	}

}
