package com.example.dailyreport.infrastructure.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.dailyreport.infrastructure.dto.AccountAndUnittestsAndUnittestsname;

@Mapper
public interface AccountAndUnittestsAndUnittestsnameMapper {

	List<AccountAndUnittestsAndUnittestsname> findStudentUnitTests();

}
