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
public class UserDataLoader implements ApplicationRunner {

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

		Account client1 = new Account();
		client1.setName("株式会社 あいうえお");
		client1.setNameKana("カブシキカイシャ アイウエオ");
		client1.setLoginId("aiueo");
		client1.setPassword(passwordEncoder.encode("password"));
		client1.setClientNameId(2);
		client1.setCourseNameId(1);
		client1.setRole(4);
		client1.setCreatedAt(LocalDateNow.getLocalDateNow());
		client1.setUpdatedAt(null);

		Account client2 = new Account();
		client2.setName("株式会社 かきくけこ");
		client2.setNameKana("カブシキカイシャ カキクケコ");
		client2.setLoginId("kakikukelo");
		client2.setPassword(passwordEncoder.encode("password"));
		client2.setClientNameId(3);
		client2.setCourseNameId(1);
		client2.setRole(4);
		client2.setCreatedAt(LocalDateNow.getLocalDateNow());
		client2.setUpdatedAt(null);

		Account student1 = new Account();
		student1.setName("山田 一郎");
		student1.setNameKana("ヤマダ イチロウ");
		student1.setLoginId("Yamada");
		student1.setPassword(passwordEncoder.encode("password"));
		student1.setClientNameId(2);
		student1.setCourseNameId(2);
		student1.setRole(5);
		student1.setCreatedAt(LocalDateNow.getLocalDateNow());
		student1.setUpdatedAt(null);

		Account student2 = new Account();
		student2.setName("田中 二郎");
		student2.setNameKana("タナカ ジロウ");
		student2.setLoginId("Tanaka");
		student2.setPassword(passwordEncoder.encode("password"));
		student2.setClientNameId(3);
		student2.setCourseNameId(3);
		student2.setRole(5);
		student2.setCreatedAt(LocalDateNow.getLocalDateNow());
		student2.setUpdatedAt(null);

		if (this.adminAccountRepository.findByLoginId(admin.getLoginId()).isEmpty()) {
			this.adminAccountRepository.save(admin);
			this.adminAccountRepository.save(main);
			this.adminAccountRepository.save(sub);
			this.adminAccountRepository.save(client1);
			this.adminAccountRepository.save(client2);
			this.adminAccountRepository.save(student1);
			this.adminAccountRepository.save(student2);
		}

	}

}
