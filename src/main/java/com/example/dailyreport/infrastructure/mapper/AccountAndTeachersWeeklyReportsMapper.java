package com.example.dailyreport.infrastructure.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.dailyreport.infrastructure.dto.AccountAndTeachersWeeklyReports;

@Mapper
public interface AccountAndTeachersWeeklyReportsMapper {

	/**
	 * 対象受講生の講師週報取得
	 * @param reports dtoクラス
	 * @return
	 */
	List<AccountAndTeachersWeeklyReports> findbyUserIdAndCourseIdAndClassWeekBetween(
			AccountAndTeachersWeeklyReports reports);

}
