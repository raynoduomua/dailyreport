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
public class UserDataLoaderStudent implements ApplicationRunner {

	private final AdminAccountRepository adminAccountRepository;
	private final PasswordEncoder passwordEncoder;

	@Override
	public void run(ApplicationArguments args) throws Exception {

		Account student1 = new Account();
		student1.setName("佐藤 一郎");
		student1.setNameKana("サトウ イチロウ");
		student1.setLoginId("Sato");
		student1.setPassword(passwordEncoder.encode("password"));
		student1.setClientNameId(2);
		student1.setCourseNameId(2);
		student1.setRole(5);
		student1.setCreatedAt(LocalDateNow.getLocalDateNow());
		student1.setUpdatedAt(null);

		Account student2 = new Account();
		student2.setName("鈴木 二郎");
		student2.setNameKana("スズキ ジロウ");
		student2.setLoginId("Suzuki");
		student2.setPassword(passwordEncoder.encode("password"));
		student2.setClientNameId(2);
		student2.setCourseNameId(2);
		student2.setRole(5);
		student2.setCreatedAt(LocalDateNow.getLocalDateNow());
		student2.setUpdatedAt(null);

		Account student3 = new Account();
		student3.setName("高橋 三郎");
		student3.setNameKana("ヤマダ サブロウ");
		student3.setLoginId("Takahashi");
		student3.setPassword(passwordEncoder.encode("password"));
		student3.setClientNameId(3);
		student3.setCourseNameId(3);
		student3.setRole(5);
		student3.setCreatedAt(LocalDateNow.getLocalDateNow());
		student3.setUpdatedAt(null);

		Account student4 = new Account();
		student4.setName("田中 四郎");
		student4.setNameKana("タナカ シロウ");
		student4.setLoginId("Tanaka");
		student4.setPassword(passwordEncoder.encode("password"));
		student4.setClientNameId(3);
		student4.setCourseNameId(3);
		student4.setRole(5);
		student4.setCreatedAt(LocalDateNow.getLocalDateNow());
		student4.setUpdatedAt(null);

		if (this.adminAccountRepository.findByLoginId(student1.getLoginId()).isEmpty()) {
			this.adminAccountRepository.save(student1);
			this.adminAccountRepository.save(student2);
			this.adminAccountRepository.save(student3);
			this.adminAccountRepository.save(student4);
		}

	}
}
