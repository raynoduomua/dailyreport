package com.example.dailyreport.infrastructure.repository.admin;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.dailyreport.infrastructure.entity.admin.MiniExamName;

public interface AdminMiniExamRepository extends JpaRepository<MiniExamName, Integer> {

}
