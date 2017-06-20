package cn.mldn.hr.service.front;

import java.util.List;

import cn.mldn.hr.vo.Log;

public interface ILogServiceFront {
	public List<Log> list(int dtid) throws Exception;
	
	public boolean updateStatus(Log vo) throws Exception;
}
