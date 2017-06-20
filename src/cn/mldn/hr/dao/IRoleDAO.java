package cn.mldn.hr.dao;

import java.sql.SQLException;
import java.util.List;

import cn.mldn.hr.vo.Groups;
import cn.mldn.hr.vo.Role;
import cn.mldn.util.dao.IDAO;

public interface IRoleDAO extends IDAO<Integer, Role> {
	/**
	 * 在进行角色添加的时候，ID是自动增长的，但是由于需要与关系表进行保存，
	 * 所以需要取得最后一次增长ID
	 * @return 当前增长的ID内容
	 * @throws SQLException
	 */
	public Integer getId() throws SQLException;
	
	/**
	 * 向角色-权限组关系表中保存数据
	 * @param rid 角色编号
	 * @param groups 权限组的信息，主要包含权限组的ID内容
	 * @return
	 * @throws SQLException
	 */
	public boolean doCreateRoleGroups(Integer rid, List<Groups> groups) throws SQLException;
	
	/**
	 * 删除角色-权限组关系表中的对应数据（在增加角色的时候先删除其对应的权限组，再重新匹配它们的对应关系）
	 * @param rid 角色编号
	 * @return 删除成功返回true，否则返回false
	 * @throws SQLException
	 */
	public boolean doRemoveGroups(Integer rid) throws SQLException;

}
