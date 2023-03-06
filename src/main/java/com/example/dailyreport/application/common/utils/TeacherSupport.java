package com.example.dailyreport.application.common.utils;

import java.util.LinkedHashMap;
import java.util.Map;

public class TeacherSupport {

	public static Map<String, Integer> selectTeacherSupportMap() {

		Map<String, Integer> studentMap = new LinkedHashMap<>();

		studentMap.put("とても丁寧だった", 5);
		studentMap.put("概ね丁寧だった", 4);
		studentMap.put("どちらともいえない", 3);
		studentMap.put("やや丁寧ではなかった", 2);
		studentMap.put("全く丁寧ではなかった", 1);

		return studentMap;
	}

}
