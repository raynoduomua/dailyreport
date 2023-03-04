package com.example.dailyreport.domain.service.admin;

import java.util.List;
import java.util.Optional;

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

	/**
	 * クライアント名1件取得
	 * @param id テーブル「clientsname」 カラム「ID」
	 * @return   クライアント名1件
	 */
	public Optional<Client> viewupdateClient(Integer id) {

		return this.adminClientRepository.findById(id);
	}

	/**
	 * クライアント名更新処理
	 * @param clientForm Formクラス
	 */
	public void updateClient(ClientForm clientForm) {

		Optional<Client> clieOptional = this.viewupdateClient(clientForm.getId());
		clieOptional.ifPresent(client -> {
			client.setClientName(clientForm.getClientname());
			client.setUpdatedAt(LocalDateNow.getLocalDateNow());

			this.adminClientRepository.save(client);
		});
	}

}
