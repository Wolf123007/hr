package cn.mldn.util;

import java.lang.reflect.Array;
import java.util.HashSet;
import java.util.Set;

public class SplitIdUtil {
	public static Set<Integer> splitInteger(String params) {
		Set<Integer> all = new HashSet<Integer>();
		if (params == null || "".equals(params)) {
			return all;
		}
		String result [] = params.split("\\|");
		for (int i = 0; i < result.length; i++) {
			all.add(Integer.parseInt(result[i]));
		}
		return all;
	}
	
	public static Set<String> splitString(String params) {
		Set<String> all = new HashSet<String>();
		if (params == null || "".equals(params)) {
			return all;
		}
		String result [] = params.split("\\|");
		for (int i = 0; i < result.length; i++) {
			all.add(result[i]);
		}
		return all;
	}
}
