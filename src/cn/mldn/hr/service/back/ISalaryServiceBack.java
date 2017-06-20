package cn.mldn.hr.service.back;

import java.util.Map;


public interface ISalaryServiceBack {
	
	public Map<String, Object> list(int currentPage, int lineSize, String column, String keyWord) throws Exception;


}
