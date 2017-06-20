package cn.mldn.hr.service.back.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.mldn.hr.dbc.DatabaseConnection;
import cn.mldn.hr.factory.DAOFactory;
import cn.mldn.hr.service.back.IRoleServiceBack;
import cn.mldn.hr.vo.Role;

public class RoleServiceBackImpl implements IRoleServiceBack {
	private DatabaseConnection dbc = new DatabaseConnection();
	@Override
	public boolean insert(Role vo) throws Exception {
		try {
			// 1、首先要进行角色数据的保存
			if (DAOFactory.getIRoleDAOInstance(this.dbc.getConnection()).doCreate(vo)) {
				// 2、取出当前示增长后的ID内容
				int rid = DAOFactory.getIRoleDAOInstance(this.dbc.getConnection()).getId();
				// 3、向关系表中保存数据
				return DAOFactory.getIRoleDAOInstance(this.dbc.getConnection()).doCreateRoleGroups(rid, vo.getGroups());
			}
			return false;
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

	@Override
	public Map<String, Object> insertPre() throws Exception {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("allGroups", DAOFactory.getIGroupsDAOInstance(this.dbc.getConnection()).findAll());
			return map;
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

	@Override
	public Map<String, Object> updatePre(int rid) throws Exception {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			// 1、判断要更新的角色信息是否存在
			Role role = DAOFactory.getIRoleDAOInstance(this.dbc.getConnection()).findById(rid);
			if (role != null) {
				// 2、查询出所有的权限组
				map.put("allGroups", DAOFactory.getIGroupsDAOInstance(this.dbc.getConnection()).findAll());
				// 3、查询该角色所具备的权限组信息
				role.setGroups(DAOFactory.getIGroupsDAOInstance(this.dbc.getConnection()).findAllByRole(rid));
			}
			map.put("role", role);
			return map;
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

	@Override
	public boolean update(Role vo) throws Exception {
		try {
			// 1、更新已有的角色数据
			if (DAOFactory.getIRoleDAOInstance(this.dbc.getConnection()).doUpdate(vo)) {
				// 2、删除相应的角色_权限组对应关系
				DAOFactory.getIRoleDAOInstance(this.dbc.getConnection()).doRemoveGroups(vo.getRid());
				// 3、重新保存本角色对应的权限组
				return DAOFactory.getIRoleDAOInstance(this.dbc.getConnection()).doCreateRoleGroups(vo.getRid(), vo.getGroups());
			}
			return false;
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

	@Override
	public List<Role> list() throws Exception {
		try {
			return DAOFactory.getIRoleDAOInstance(this.dbc.getConnection()).findAll();
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

	@Override
	public boolean delete(Set<Integer> ids) throws Exception {
		try {
			return DAOFactory.getIRoleDAOInstance(this.dbc.getConnection()).doRemoveBatch(ids);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

}
