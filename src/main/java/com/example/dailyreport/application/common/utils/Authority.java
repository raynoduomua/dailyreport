package com.example.dailyreport.application.common.utils;

import java.util.LinkedHashMap;
import java.util.Map;

public class Authority {

	public static Map<String, Integer> selectAuthorityMap() {

		Map<String, Integer> AuthorityMap = new LinkedHashMap<>();

		AuthorityMap.put("管理者", 1);
		AuthorityMap.put("メイン講師", 2);
		AuthorityMap.put("アブ講師", 3);
		AuthorityMap.put("クライアント", 4);
		AuthorityMap.put("受講生", 5);

		return AuthorityMap;
	}

}
