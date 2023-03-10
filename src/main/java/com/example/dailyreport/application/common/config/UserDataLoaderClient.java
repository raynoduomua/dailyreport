package com.example.dailyreport.application.common.config;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.dailyreport.application.common.utils.LocalDateNow;
import com.example.dailyreport.infrastructure.entity.admin.account.Account;
import com.example.dailyreport.infrastructure.repository.admin.AdminAccountRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserDataLoaderClient implements ApplicationRunner {

	private final AdminAccountRepository adminAccountRepository;
	private final PasswordEncoder passwordEncoder;

	@Override
	public void run(ApplicationArguments args) throws Exception {

		Account client1 = new Account();
		client1.setName("株式会社 あいうえお");
		client1.setNameKana("カブシキカイシャ アイウエオ");
		client1.setLoginId("aiueo");
		client1.setPassword(passwordEncoder.encode("password"));
		client1.setClientNameId(2);
		client1.setCourseNameId(2);
		client1.setRole(4);
		client1.setCreatedAt(LocalDateNow.getLocalDateNow());
		client1.setUpdatedAt(null);

		Account client2 = new Account();
		client2.setName("株式会社 かきくけこ");
		client2.setNameKana("カブシキカイシャ カキクケコ");
		client2.setLoginId("kakikukelo");
		client2.setPassword(passwordEncoder.encode("password"));
		client2.setClientNameId(3);
		client2.setCourseNameId(3);
		client2.setRole(4);
		client2.setCreatedAt(LocalDateNow.getLocalDateNow());
		client2.setUpdatedAt(null);

		if (this.adminAccountRepository.findByLoginId(client1.getLoginId()).isEmpty()) {
			this.adminAccountRepository.save(client1);
			this.adminAccountRepository.save(client2);
		}

	}
}
