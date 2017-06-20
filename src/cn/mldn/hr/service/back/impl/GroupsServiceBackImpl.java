package cn.mldn.hr.service.back.impl;

import java.util.List;
import java.util.Set;

import cn.mldn.hr.dbc.DatabaseConnection;
import cn.mldn.hr.factory.DAOFactory;
import cn.mldn.hr.service.back.IGroupsServiceBack;
import cn.mldn.hr.vo.Groups;

public class GroupsServiceBackImpl implements IGroupsServiceBack {
	private DatabaseConnection dbc = new DatabaseConnection();
	@Override
	public boolean insert(Groups vo) throws Exception {
		try {
			return DAOFactory.getIGroupsDAOInstance(this.dbc.getConnection()).doCreate(vo);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

	@Override
	public boolean update(Groups vo) throws Exception {
		try {
			return DAOFactory.getIGroupsDAOInstance(this.dbc.getConnection()).doUpdate(vo);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

	@Override
	public boolean delete(Set<Integer> ids) throws Exception {
		try {
			return DAOFactory.getIGroupsDAOInstance(this.dbc.getConnection()).doRemoveBatch(ids);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

	@Override
	public List<Groups> list() throws Exception {
		try {
			return DAOFactory.getIGroupsDAOInstance(this.dbc.getConnection()).findAll();
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

}
