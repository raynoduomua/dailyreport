package com.example.dailyreport.infrastructure.repository.admin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.dailyreport.infrastructure.entity.admin.account.Account;

@Repository
public interface AdminAccountRepository extends JpaRepository<Account, Integer> {

	List<Account> findByLoginId(String loginId);

}
