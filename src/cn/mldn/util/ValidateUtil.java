package cn.mldn.util;

import javax.servlet.http.HttpServletRequest;

public class ValidateUtil {
	/**
	 * 验证输入数据是否为空
	 * @param data 输入数据
	 * @return 如果是null返回false，否则返回true
	 */
	public static boolean validateEmpty(String data) { // request.getParamter("mid")
		if (data == null || "".equals(data)) {
			return false;
		}
		return true;
	}
	
	/**
	 * 进行数据的正则操作验证
	 * @param data 要验证的数据
	 * @param regex 要执行验证的正则表达式
	 * @return 验证通过返回true，否则返回false
	 */
	public static boolean validateRegex(String data, String regex) {
		if (validateEmpty(data)) { // 数据不为空
			return data.matches(regex); // 正则验证
		}
		
		return false;
	}
	
	/**
	 * 验证两个是否相同，不区分大小写
	 * @param dataa 数据一
	 * @param datab 数据二
	 * @return 数据相同返回true，否则返回false
	 */
	public static boolean validateSame(String dataa, String datab) {
		if (validateEmpty(dataa) && validateEmpty(datab)) {
			return dataa.equalsIgnoreCase(datab);
		}
		return false;
	}
	
}













