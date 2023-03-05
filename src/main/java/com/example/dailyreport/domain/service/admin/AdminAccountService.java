package com.example.dailyreport.domain.service.admin;

import org.springframework.stereotype.Service;

import com.example.dailyreport.application.common.utils.LocalDateNow;
import com.example.dailyreport.application.form_validation.AccountForm;
import com.example.dailyreport.infrastructure.entity.admin.account.Account;
import com.example.dailyreport.infrastructure.repository.admin.AdminAccountRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminAccountService {

	private final AdminAccountRepository adminAccountRepository;

	/**
	 * アカウント登録処理
	 * @param accountForm Formクラス
	 */
	public void saveAccount(AccountForm accountForm) {

		Account account = new Account();
		account.setName(accountForm.getName());
		account.setNameKana(accountForm.getNameKana());
		account.setLoginId(accountForm.getLoginId());
		account.setPassword(accountForm.getPassword());
		account.setClientNameId(accountForm.getClientNameId());
		account.setCourseNameId(accountForm.getCourseNameId());
		account.setRole(accountForm.getRole());
		account.setCreatedAt(LocalDateNow.getLocalDateNow());
		account.setUpdatedAt(null);

		this.adminAccountRepository.save(account);
	}

}
