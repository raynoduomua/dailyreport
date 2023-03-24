package com.example.dailyreport.application.common.security;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

	@Bean
	public PasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(auth -> auth
				// 「cssやjs、imagesなどの静的リソース」をアクセス可能にします
				.requestMatchers(PathRequest.toStaticResources()
						.atCommonLocations())
				.permitAll()
				.mvcMatchers("/dailyreport/loginForm").permitAll()
				.mvcMatchers("/admin/**").hasAnyAuthority("ADMIN")
				.mvcMatchers("/teacher/**").hasAnyAuthority("TEACHER")
				.mvcMatchers("/student/**").hasAnyAuthority("STUDENT")
				.anyRequest().authenticated())
				.formLogin(login -> login
						.loginProcessingUrl("/login")
						.loginPage("/loginForm")
						.usernameParameter("loginId")
						.passwordParameter("password")
						.defaultSuccessUrl("/dailyreport/loginFork", true)
						.failureUrl("/dailyreport/loginForm?error"))
				// ログアウトの設定
				.logout(logout -> logout
						// ログアウト時のURLを指定
						.logoutRequestMatcher(new AntPathRequestMatcher("/dailyreport/logout"))
						.logoutSuccessUrl("/dailyreport/loginForm?logout")
						.permitAll())
				// Remember-Meの認証を許可します
				// これを設定すると、ブラウザを閉じて、
				// 再度開いた場合でも「ログインしたまま」にできます
				.rememberMe();

		return http.build();
	}

}
