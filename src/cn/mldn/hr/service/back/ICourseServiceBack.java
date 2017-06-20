package cn.mldn.hr.service.back;

import java.util.Map;

public interface ICourseServiceBack {
	public Map<String, Object> list(int currentPage, int lineSize, String column, String keyWord) throws Exception;
	
	public Map<String, Object> listByEmployee(int eid, int currentPage, int lineSize, String column, String keyWord) throws Exception;
	
	
}
