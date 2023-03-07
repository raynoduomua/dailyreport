package com.example.dailyreport.application.common.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.dailyreport.infrastructure.entity.admin.account.Account;

public class LoginUser implements UserDetails {

	//	Entityクラス
	private final Account account;

	//	コンストラクタ
	public LoginUser(Account account) {
		this.account = account;
	}

	// Entityクラスである、Userオブジェクトのゲッター
	public Account getUser() {
		return this.account;
	}

	// ユーザーの認証に使用されるパスワードを返却する
	@Override
	public String getPassword() {
		return this.account.getPassword();
	}

	// ユーザーの認証に使用されるユーザー名を返却する
	@Override
	public String getUsername() {
		return this.account.getLoginId();
	}

	// ユーザーに付与された権限を返却する
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		// 付与する権限を変更する
		switch (this.account.getRole()) {
		// admin
		case 1:
			return AuthorityUtils.createAuthorityList("ADMIN", "TEACHER", "CLIENT", "STUDENT");
		// main講師
		// sub講師
		case 2:
		case 3:
			return AuthorityUtils.createAuthorityList("TEACHER");
		// client
		case 4:
			return AuthorityUtils.createAuthorityList("CLIENT");
		// student
		default:
			return AuthorityUtils.createAuthorityList("STUDENT");
		}

	}

	// アカウントの有効期限の状態を判定する
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	// アカウントのロック状態を判定する
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	// 資格情報の有効期限の状態を判定する
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	// 有効なユーザかを判定する
	@Override
	public boolean isEnabled() {
		return true;
	}

}
