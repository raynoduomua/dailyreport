package com.example.dailyreport.domain.service.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.dailyreport.application.common.security.LoginUser;
import com.example.dailyreport.application.common.utils.LocalDateNow;
import com.example.dailyreport.application.form_validation.AccountForm;
import com.example.dailyreport.infrastructure.dto.AccountAndCourseAndClient;
import com.example.dailyreport.infrastructure.entity.admin.account.Account;
import com.example.dailyreport.infrastructure.mapper.AccountAndCourseAndClientMapper;
import com.example.dailyreport.infrastructure.repository.admin.AdminAccountRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminAccountService {

	private final AdminAccountRepository adminAccountRepository;
	private final AccountAndCourseAndClientMapper accountAndCourseAndClientMapper;

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

	/**
	 * アカウント一覧取得
	 * @return アカウント一覧
	 */
	public List<AccountAndCourseAndClient> viewAccountList() {

		AccountAndCourseAndClient account = new AccountAndCourseAndClient();

		return accountAndCourseAndClientMapper.findAllAccount(account);
	}

	/**
	 * アカウント1件取得
	 * @param id テーブル「accounts」 カラム「ID」
	 * @return   アカウント1件
	 */
	public Optional<Account> viewupdateAccount(Integer id) {

		return this.adminAccountRepository.findById(id);
	}

	/**
	 * アカウント更新処理
	 * @param accountForm Formクラス
	 */
	public void updateAccount(AccountForm accountForm) {

		Optional<Account> accountOptional = this.viewupdateAccount(accountForm.getId());
		accountOptional.ifPresent(account -> {
			account.setName(accountForm.getName());
			account.setNameKana(accountForm.getNameKana());
			account.setLoginId(accountForm.getLoginId());
			account.setPassword(accountForm.getPassword());
			account.setClientNameId(accountForm.getClientNameId());
			account.setCourseNameId(accountForm.getCourseNameId());
			account.setRole(accountForm.getRole());
			account.setUpdatedAt(LocalDateNow.getLocalDateNow());

			this.adminAccountRepository.save(account);
		});
	}

	/**
	 * 講座IDと同じ受講生の取得
	 * @param loginUser ログイン中のユーザ情報
	 * @return          講座IDと同じ受講生
	 */
	public List<Account> findByCourseNameIdAndRole(LoginUser loginUser) {

		return this.adminAccountRepository.findByCourseNameIdAndRole(loginUser.getUser().getCourseNameId(), 5);
	}

}
