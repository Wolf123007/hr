package cn.mldn.hr.service.back;

import java.util.List;

import cn.mldn.hr.vo.Log;

public interface ILogServiceBack {
	public List<Log> list(int dtid) throws Exception;
}
