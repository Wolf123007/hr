package cn.mldn.hr.service.back.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import cn.mldn.hr.dbc.DatabaseConnection;
import cn.mldn.hr.factory.DAOFactory;
import cn.mldn.hr.service.back.IAdminServiceBack;
import cn.mldn.hr.vo.Admin;

public class AdminServiceBackImpl implements IAdminServiceBack {
	private DatabaseConnection dbc = new DatabaseConnection();
	@Override
	public Map<String, Object> insertPre() throws Exception {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("allRoles", DAOFactory.getIRoleDAOInstance(this.dbc.getConnection()).findAll());
			return map;
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

	@Override
	public boolean insert(Admin vo) throws Exception {
		try {
			if (vo.getType() == 1) { // 有类型
				if (vo.getRole().getRid() == null) { // 没有角色
					return false;
				}
			}
			vo.setFlag(0); // 永远只能够增加普通管理员
			if (vo.getType() == 0) { // 没有角色
				vo.setRole(null);
			}
			if (DAOFactory.getIAdminDAOInstance(this.dbc.getConnection()).findById(vo.getAid()) == null) {
				return DAOFactory.getIAdminDAOInstance(this.dbc.getConnection()).doCreate(vo);
			}
			return false;
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

	@Override
	public boolean update(Admin vo) throws Exception {
		try {
			if (vo.getType() == 1) { // 有类型
				if (vo.getRole().getRid() == null) { // 没有角色
					return false;
				}
			}
			if (vo.getType() == 0) { // 没有角色
				vo.setRole(null);
			}
			return DAOFactory.getIAdminDAOInstance(this.dbc.getConnection()).doUpdate(vo);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

	@Override
	public Map<String, Object> list() throws Exception {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("allRoles", DAOFactory.getIRoleDAOInstance(this.dbc.getConnection()).findAll());
			map.put("allAdmins", DAOFactory.getIAdminDAOInstance(this.dbc.getConnection()).findAll());
			return map;
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

	@Override
	public boolean delete(Set<String> ids) throws Exception {
		try {
			return DAOFactory.getIAdminDAOInstance(this.dbc.getConnection()).doRemoveBatch(ids);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

}
