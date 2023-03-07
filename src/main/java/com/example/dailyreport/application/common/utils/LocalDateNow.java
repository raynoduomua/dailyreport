package com.example.dailyreport.application.common.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class LocalDateNow {

	/**
	 * 現在日付取得
	 * @return 現在日付
	 */
	public static LocalDate getLocalDateNow() {

		LocalDate currentDate = LocalDate.now();

		return currentDate;
	}

	/**
	 * 今週月曜日取得
	 * @return 今週月曜日
	 */
	public static LocalDate getLocalDateMonday() {

		LocalDate Mon = getLocalDateNow().with(DayOfWeek.MONDAY);

		return Mon;
	}

	/**
	 * 今週金曜日取得
	 * @return 今週金曜日
	 */
	public static LocalDate getLocalDateFriday() {

		LocalDate Fri = getLocalDateNow().with(DayOfWeek.FRIDAY);

		return Fri;
	}

}
