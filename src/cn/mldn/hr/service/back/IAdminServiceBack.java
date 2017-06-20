package cn.mldn.hr.service.back;

import java.util.Map;
import java.util.Set;

import cn.mldn.hr.vo.Admin;

public interface IAdminServiceBack {
	/**
	 * 增加之前查询出所有的角色信息
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> insertPre() throws Exception;
	
	public boolean insert(Admin vo) throws Exception;
	
	public boolean update(Admin vo) throws Exception;
	
	public Map<String, Object> list() throws Exception;
	
	public boolean delete(Set<String> ids) throws Exception;
}
