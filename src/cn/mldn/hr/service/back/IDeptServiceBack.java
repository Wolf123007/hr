package cn.mldn.hr.service.back;

import java.util.List;
import java.util.Set;

import cn.mldn.hr.vo.Dept;

public interface IDeptServiceBack {
	/**
	 * 在进行数据增加的时候需要执行以下操作：<br>
	 * <li>首先要判断增加的部门名称是否已经存在</li>
	 * <li>由于每个部门创建初期人数都是0，所以必须将current设置为0</li>
	 * <li>进行数据的保存</li>
	 * @param vo 包含了要增加数据的VO类对象
	 * @return 增加成功返回true，失败返回false
	 * @throws Exception
	 */
	public boolean insert(Dept vo) throws Exception;
	public boolean update(Dept vo) throws Exception;
	public boolean delete(Set<Integer> ids) throws Exception;
	public List<Dept> list() throws Exception;
}
