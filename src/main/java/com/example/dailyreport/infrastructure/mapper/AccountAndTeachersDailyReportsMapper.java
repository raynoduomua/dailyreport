package com.example.dailyreport.infrastructure.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.dailyreport.infrastructure.dto.AccountAndTeachersDailyReports;

@Mapper
public interface AccountAndTeachersDailyReportsMapper {

	List<AccountAndTeachersDailyReports> searchTeachersDailyReports(AccountAndTeachersDailyReports reports);

}
