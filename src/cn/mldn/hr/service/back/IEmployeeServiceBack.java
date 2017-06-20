package cn.mldn.hr.service.back;

import java.util.Map;

public interface IEmployeeServiceBack {
	
	/**
	 * 根据雇员的在职与离职状态进行列出
	 * @param currentPage
	 * @param lineSize
	 * @param column
	 * @param keyWord
	 * @param status
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> listByStatus(int currentPage, int lineSize, String column, String keyWord, int status) throws Exception;

}
