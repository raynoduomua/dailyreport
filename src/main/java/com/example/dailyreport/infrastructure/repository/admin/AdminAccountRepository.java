package com.example.dailyreport.infrastructure.repository.admin;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.dailyreport.infrastructure.entity.admin.account.Account;

@Repository
public interface AdminAccountRepository extends JpaRepository<Account, Integer> {

	Optional<Account> findByLoginId(String loginId);

}
