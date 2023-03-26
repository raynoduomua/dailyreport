package com.example.dailyreport.infrastructure.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.dailyreport.infrastructure.dto.AccountAndCourseAndClient;

@Mapper
public interface AccountAndCourseAndClientMapper {

	List<AccountAndCourseAndClient> findAllAccount(AccountAndCourseAndClient search);

}
