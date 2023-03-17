package com.example.dailyreport.infrastructure.repository.admin;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.dailyreport.infrastructure.entity.admin.MiniExamName;

@Repository
public interface AdminMiniExamRepository extends JpaRepository<MiniExamName, Integer> {

	List<MiniExamName> findByTestName(String testName);

}
