package cn.mldn.hr.service.back;

import java.util.List;
import java.util.Set;

import cn.mldn.hr.vo.Groups;

public interface IGroupsServiceBack {
	public boolean insert(Groups vo) throws Exception;
	
	public boolean update(Groups vo) throws Exception;
	
	public boolean delete(Set<Integer> ids) throws Exception;
	
	public List<Groups> list() throws Exception;
}
