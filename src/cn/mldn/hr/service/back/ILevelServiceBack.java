package cn.mldn.hr.service.back;

import java.util.List;
import java.util.Set;

import cn.mldn.hr.vo.Level;

public interface ILevelServiceBack {
	
	public boolean insert(Level vo) throws Exception;
	public boolean update(Level vo) throws Exception;
	public boolean delete(Set<Integer> ids) throws Exception;
	public List<Level> list() throws Exception;
}
