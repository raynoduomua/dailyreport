package com.example.dailyreport.application.common.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.dailyreport.infrastructure.repository.admin.AdminAccountRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

	private final AdminAccountRepository adminAccountRepository;

	@Override
	public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {

		// ユーザ名を検索します（ユーザが存在しない場合は、例外をスローします）
		var user = adminAccountRepository.findByLoginId(loginId)
				.orElseThrow(() -> new UsernameNotFoundException(loginId + " not found"));

		// ユーザ情報を返します
		return new LoginUser(user);

	}

}
