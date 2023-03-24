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
public class UserDataLoaderTeacher implements ApplicationRunner {

	private final AdminAccountRepository adminAccountRepository;
	private final PasswordEncoder passwordEncoder;

	@Override
	public void run(ApplicationArguments args) throws Exception {

		Account main = new Account();
		main.setName("main");
		main.setNameKana("メインコウシ");
		main.setLoginId("main");
		main.setPassword(passwordEncoder.encode("password"));
		main.setClientNameId(1);
		main.setCourseNameId(2);
		main.setRole(2);
		main.setCreatedAt(LocalDateNow.getLocalDateNow());
		main.setUpdatedAt(null);

		Account sub = new Account();
		sub.setName("sub");
		sub.setNameKana("サブコウシ");
		sub.setLoginId("sub");
		sub.setPassword(passwordEncoder.encode("password"));
		sub.setClientNameId(1);
		sub.setCourseNameId(2);
		sub.setRole(3);
		sub.setCreatedAt(LocalDateNow.getLocalDateNow());
		sub.setUpdatedAt(null);

		Account main2 = new Account();
		main2.setName("main");
		main2.setNameKana("メインコウシ");
		main2.setLoginId("main2");
		main2.setPassword(passwordEncoder.encode("password"));
		main2.setClientNameId(1);
		main2.setCourseNameId(3);
		main2.setRole(2);
		main2.setCreatedAt(LocalDateNow.getLocalDateNow());
		main2.setUpdatedAt(null);

		Account sub2 = new Account();
		sub2.setName("sub");
		sub2.setNameKana("サブコウシ");
		sub2.setLoginId("sub2");
		sub2.setPassword(passwordEncoder.encode("password"));
		sub2.setClientNameId(1);
		sub2.setCourseNameId(3);
		sub2.setRole(3);
		sub2.setCreatedAt(LocalDateNow.getLocalDateNow());
		sub2.setUpdatedAt(null);

		//		if (this.adminAccountRepository.findByLoginId(main.getLoginId()).isEmpty()) {
		//			this.adminAccountRepository.save(main);
		//			this.adminAccountRepository.save(sub);
		//			this.adminAccountRepository.save(main2);
		//			this.adminAccountRepository.save(sub2);
		//		}

	}
}
