package cn.mldn.hr.service.back;

import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.mldn.hr.vo.Role;

public interface IRoleServiceBack {
	/**
	 * 进行角色的增加扣篮，此操作需要执行如下步骤：<br>
	 * 	<li>执行IRoleDAO.doCreate()进行角色的保存</li>
	 * 	<li>执行IRoleDAO.getId()取得增长后的ID内容</li>
	 * 	<li>调用IRoleDAO.doCreateRoleAndGroups()方法保存角色与权限组的关系</li>
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public boolean insert(Role vo) throws Exception;
	
	/**
	 * 查询出所有的权限组信息
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> insertPre() throws Exception;
	
	/**
	 * 执行更新前的数据查询操作，本操作需要执行如下内容：<br>
	 * 	<li>调用IRoleDAO.findById()方法根据角色编号取出角色信息</li>
	 * 	<li>调用IGroupsDAO.findAllByRole()方法取出一个角色的所有权限组</li>
	 * 	<li>调用IGroupsDAO.findAll()方法取出所有的权限组信息</li>
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> updatePre(int rid) throws Exception;
	
	/**
	 * 修改角色信息，包含如下内容：<br>
	 * 	<li>调用IRoleDAO.doUpdate()进行角色的更新，ID不变</li>
	 * 	<li>调用IRoleDAO.doRemoveGroups()方法删除关系表中的对应数据</li>
	 * 	<li>调用IRoleDAO.doCreateRoleAndGroups()方法保存角色与权限组的关系</li>
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	public boolean update(Role vo) throws Exception;
	
	
	public List<Role> list() throws Exception;
	
	public boolean delete(Set<Integer> ids) throws Exception;

}
