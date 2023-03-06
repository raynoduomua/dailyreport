package com.example.dailyreport.application.common.utils;

import java.util.LinkedHashMap;
import java.util.Map;

public class UnderStand {

	public static Map<String, Integer> selectUnderStandMap() {

		Map<String, Integer> understandMap = new LinkedHashMap<>();

		understandMap.put("良く理解できた", 5);
		understandMap.put("概ね理解できた", 4);
		understandMap.put("普通", 3);
		understandMap.put("少し難しかった", 2);
		understandMap.put("とても難しかった", 1);

		return understandMap;
	}

}
