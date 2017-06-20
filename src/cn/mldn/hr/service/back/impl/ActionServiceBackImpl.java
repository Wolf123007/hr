package cn.mldn.hr.service.back.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import cn.mldn.hr.dbc.DatabaseConnection;
import cn.mldn.hr.factory.DAOFactory;
import cn.mldn.hr.service.back.IActionServiceBack;
import cn.mldn.hr.vo.Action;

public class ActionServiceBackImpl implements IActionServiceBack {
	private DatabaseConnection dbc = new DatabaseConnection();
	@Override
	public Map<String, Object> inserPre() throws Exception {
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
	public boolean insert(Action vo) throws Exception {
		try {
			return DAOFactory.getIActionDAOInstance(this.dbc.getConnection()).doCreate(vo);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

	@Override
	public boolean update(Action vo) throws Exception {
		try {
			return DAOFactory.getIActionDAOInstance(this.dbc.getConnection()).doUpdate(vo);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

	@Override
	public boolean delete(Set<Integer> ids) throws Exception {
		try {
			return DAOFactory.getIActionDAOInstance(this.dbc.getConnection()).doRemoveBatch(ids);
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
			map.put("allGroups", DAOFactory.getIGroupsDAOInstance(this.dbc.getConnection()).findAll());
			map.put("allActions", DAOFactory.getIActionDAOInstance(this.dbc.getConnection()).findAll());
			return map;
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

}
