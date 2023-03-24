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
public class UserDataLoaderAdmin implements ApplicationRunner {

	private final AdminAccountRepository adminAccountRepository;
	private final PasswordEncoder passwordEncoder;

	@Override
	public void run(ApplicationArguments args) throws Exception {

		Account admin = new Account();
		admin.setName("admin");
		admin.setNameKana("アドミン");
		admin.setLoginId("admin");
		admin.setPassword(passwordEncoder.encode("password"));
		admin.setClientNameId(1);
		admin.setCourseNameId(1);
		admin.setRole(1);
		admin.setCreatedAt(LocalDateNow.getLocalDateNow());
		admin.setUpdatedAt(null);

		//		if (this.adminAccountRepository.findByLoginId(admin.getLoginId()).isEmpty()) {
		//			this.adminAccountRepository.save(admin);
		//		}

	}

}
