package cn.mldn.hr.dao;

import java.sql.SQLException;
import java.util.List;

import cn.mldn.hr.vo.Groups;
import cn.mldn.util.dao.IDAO;

public interface IGroupsDAO extends IDAO<Integer, Groups> {
	/**
	 * 根据角色编号查询出所有此角色对应的权限组信息，首先要通过role_groups表查询出所有的权限组ID
	 * 而后再根据groups表中查询出具备有指定gid的所有权限组数据
	 * @param rid 角色编号
	 * @return 返回全部的权限组信息，以集合的方式返回，如果没有权限组存在，则集合长度为0
	 * @throws SQLException
	 */
	public List<Groups> findAllByRole(Integer rid) throws SQLException;
}
