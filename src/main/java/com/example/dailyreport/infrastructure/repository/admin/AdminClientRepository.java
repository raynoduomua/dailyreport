package com.example.dailyreport.infrastructure.repository.admin;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.dailyreport.infrastructure.entity.admin.Client;

public interface AdminClientRepository extends JpaRepository<Client, Integer> {

}
