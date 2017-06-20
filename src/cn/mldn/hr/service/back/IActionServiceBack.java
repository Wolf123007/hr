package cn.mldn.hr.service.back;

import java.util.Map;
import java.util.Set;

import cn.mldn.hr.vo.Action;


public interface IActionServiceBack {
	public Map<String, Object> inserPre() throws Exception;
	public boolean insert(Action vo) throws Exception;
	public boolean update(Action vo) throws Exception;
	public boolean delete(Set<Integer> ids) throws Exception;
	/**
	 * 要包含全部的权限信息以及所有的权限组信息
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> list() throws Exception;
}
