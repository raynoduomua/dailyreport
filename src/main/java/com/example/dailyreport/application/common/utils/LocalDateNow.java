package com.example.dailyreport.application.common.utils;

import java.time.LocalDate;

public class LocalDateNow {

	/**
	 * 現在日付取得
	 * 
	 * @return 現在日付
	 */
	public static LocalDate getLocalDateNow() {
		// 現在日付
		LocalDate currentDate = LocalDate.now();
		return currentDate;
	}

}
