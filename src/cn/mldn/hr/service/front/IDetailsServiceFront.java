package cn.mldn.hr.service.front;

import java.util.Map;

import cn.mldn.hr.vo.Details;

public interface IDetailsServiceFront {
	public Map<String, Object> list(int cid, int currentPage, int lineSize,
			String column, String keyWord) throws Exception;
	
	public boolean updateScore(Details vo) throws Exception;
}
