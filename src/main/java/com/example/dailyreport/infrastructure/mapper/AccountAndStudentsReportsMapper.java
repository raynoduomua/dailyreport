package com.example.dailyreport.infrastructure.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.dailyreport.infrastructure.dto.AccountAndStudentsReports;

@Mapper
public interface AccountAndStudentsReportsMapper {

	List<AccountAndStudentsReports> findStudentReports(AccountAndStudentsReports reports);

}
