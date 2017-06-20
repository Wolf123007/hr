package cn.mldn.hr.service.back;

import java.util.List;
import java.util.Set;

import cn.mldn.hr.vo.Jobs;

public interface IJobsServiceBack {
	
	public boolean insert(Jobs vo) throws Exception;
	public boolean update(Jobs vo) throws Exception;
	public boolean delete(Set<Integer> ids) throws Exception;
	public List<Jobs> list() throws Exception;
}
