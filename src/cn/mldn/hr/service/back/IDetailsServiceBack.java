package cn.mldn.hr.service.back;

import java.util.Map;

public interface IDetailsServiceBack {
	public Map<String, Object> list(int cid, int currentPage, int lineSize, String column, String keyWord) throws Exception;
}
