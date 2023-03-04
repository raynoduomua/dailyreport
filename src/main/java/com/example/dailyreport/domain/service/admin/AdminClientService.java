package com.example.dailyreport.domain.service.admin;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.dailyreport.application.common.utils.LocalDateNow;
import com.example.dailyreport.application.form_validation.ClientForm;
import com.example.dailyreport.infrastructure.entity.admin.Client;
import com.example.dailyreport.infrastructure.repository.admin.AdminClientRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminClientService {

	private final AdminClientRepository adminClientRepository;

	/**
	 * クライアント名登録処理
	 * @param clientForm Formクラス
	 */
	public void saveCourse(ClientForm clientForm) {

		Client client = new Client();
		client.setClientName(clientForm.getClientname());
		client.setCreatedAt(LocalDateNow.getLocalDateNow());

		this.adminClientRepository.save(client);
	}

	/**
	 * クライアント名一覧取得
	 * @return クライアント名一覧
	 */
	public List<Client> viewClientList() {

		return this.adminClientRepository.findAll();
	}

}
