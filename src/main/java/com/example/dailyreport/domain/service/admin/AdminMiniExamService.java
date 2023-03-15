package com.example.dailyreport.domain.service.admin;

import java.util.List;

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

}
