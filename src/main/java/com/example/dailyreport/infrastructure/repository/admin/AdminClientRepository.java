package com.example.dailyreport.infrastructure.repository.admin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.dailyreport.infrastructure.entity.admin.Client;

@Repository
public interface AdminClientRepository extends JpaRepository<Client, Integer> {

	List<Client> findByClientName(String clientName);

}
