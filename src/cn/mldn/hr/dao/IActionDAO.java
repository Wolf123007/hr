package cn.mldn.hr.dao;

import java.sql.SQLException;
import java.util.List;

import cn.mldn.hr.vo.Action;
import cn.mldn.util.dao.IDAO;

public interface IActionDAO extends IDAO<Integer, Action> {
	/**
	 * 根据权限组编号查询出此权限组中对应的所有权限
	 * @param gid 权限组编号
	 * @return 所有的权限以List集合返回
	 * @throws SQLException
	 */
	public List<Action> findAllByGroups(Integer gid) throws SQLException;
}
