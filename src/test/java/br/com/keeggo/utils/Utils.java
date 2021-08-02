package br.com.keeggo.utils;


import java.util.LinkedHashMap;
import java.util.Map;

public class Utils {
	
	
	public static LinkedHashMap<String, String> trataMap(Map<String, String> _map) {
		LinkedHashMap<String, String> map = new LinkedHashMap<>(_map);
		
		for (String key : map.keySet()) {
			if (map.get(key) == null) {
				map.put(key, "");
			}
		}
		return map;
	}
}
