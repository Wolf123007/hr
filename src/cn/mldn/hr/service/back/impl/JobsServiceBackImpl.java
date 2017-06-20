package cn.mldn.hr.service.back.impl;

import java.util.List;
import java.util.Set;

import cn.mldn.hr.dbc.DatabaseConnection;
import cn.mldn.hr.factory.DAOFactory;
import cn.mldn.hr.service.back.IJobsServiceBack;
import cn.mldn.hr.vo.Jobs;

public class JobsServiceBackImpl implements IJobsServiceBack {
	private DatabaseConnection dbc = new DatabaseConnection();
	@Override
	public boolean insert(Jobs vo) throws Exception {
		try {
			if (DAOFactory.getIJobsDAOInstance(this.dbc.getConnection()).findByTitle(vo.getTitle()) == null) {
				return DAOFactory.getIJobsDAOInstance(this.dbc.getConnection()).doCreate(vo);
			}
			return false;
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

	@Override
	public boolean update(Jobs vo) throws Exception {
		try {
			if (DAOFactory.getIJobsDAOInstance(this.dbc.getConnection()).findByTitle(vo.getTitle(), vo.getJid()) == null) {
				return DAOFactory.getIJobsDAOInstance(this.dbc.getConnection()).doUpdate(vo);
			}
			return false;
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

	@Override
	public boolean delete(Set<Integer> ids) throws Exception {
		try {
			return DAOFactory.getIJobsDAOInstance(this.dbc.getConnection()).doRemoveBatch(ids);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

	@Override
	public List<Jobs> list() throws Exception {
		try {
			return DAOFactory.getIJobsDAOInstance(this.dbc.getConnection()).findAll();
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

}
