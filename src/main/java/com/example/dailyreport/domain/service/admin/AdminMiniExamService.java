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

	public void saveMiniExam(MiniExamNameForm miniExamNameForm) {

		MiniExamName miniExamName = new MiniExamName();
		miniExamName.setTestName(miniExamNameForm.getTestName());
		miniExamName.setCreatedAt(LocalDateNow.getLocalDateNow());
		miniExamName.setUpdatedAt(null);

		this.adminMiniExamRepository.save(miniExamName);
	}

	public List<MiniExamName> viewMiniExamList() {

		return this.adminMiniExamRepository.findAll();
	}

	public Optional<MiniExamName> findById(Integer id) {

		return this.adminMiniExamRepository.findById(id);
	}

	public MiniExamNameForm viewupdateMiniExam(Integer id, MiniExamNameForm miniExamNameForm) {

		Optional<MiniExamName> miniExamOptional = this.findById(id);
		miniExamOptional.ifPresent(miniexam -> {
			miniExamNameForm.setId(miniexam.getId());
			miniExamNameForm.setTestName(miniexam.getTestName());
		});

		return miniExamNameForm;
	}

	public void updateMiniExam(MiniExamNameForm miniExamNameForm) {

		Optional<MiniExamName> miniexamOptional = this.findById(miniExamNameForm.getId());
		miniexamOptional.ifPresent(miniexam -> {
			miniexam.setTestName(miniExamNameForm.getTestName());
			miniexam.setUpdatedAt(LocalDateNow.getLocalDateNow());

			this.adminMiniExamRepository.save(miniexam);
		});
	}

}
