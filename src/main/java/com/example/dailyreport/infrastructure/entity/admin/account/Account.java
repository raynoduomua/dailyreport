package com.example.dailyreport.infrastructure.entity.admin.account;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "accounts")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "NAME_KANA")
	private String nameKana;

	@Column(name = "LOGIN_ID")
	private String loginId;

	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "CLIENT_NAME_ID")
	private Integer clientNameId;

	@Column(name = "COURSE_NAME_ID")
	private Integer courseNameId;

	@Column(name = "ROLE")
	private Integer role;

}
