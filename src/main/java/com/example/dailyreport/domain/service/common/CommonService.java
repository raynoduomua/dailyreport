package com.example.dailyreport.domain.service.common;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.dailyreport.application.common.security.LoginUser;
import com.example.dailyreport.infrastructure.dto.AccountAndCourseAndClient;
import com.example.dailyreport.infrastructure.mapper.AccountAndCourseAndClientMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommonService {

	private final AccountAndCourseAndClientMapper accountAndCourseAndClientMapper;

	/**
	 * ログイン中のユーザ情報取得
	 * @param loginUser ログイン中のユーザ情報
	 * @return          ログイン中のユーザ情報
	 */
	public List<AccountAndCourseAndClient> viewAccountOneList(LoginUser loginUser) {

		AccountAndCourseAndClient account = new AccountAndCourseAndClient();
		account.setId(loginUser.getUser().getId());

		return accountAndCourseAndClientMapper.findAllAccount(account);
	}

}
