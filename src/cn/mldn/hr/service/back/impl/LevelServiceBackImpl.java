package cn.mldn.hr.service.back.impl;

import java.util.List;
import java.util.Set;

import cn.mldn.hr.dbc.DatabaseConnection;
import cn.mldn.hr.factory.DAOFactory;
import cn.mldn.hr.service.back.ILevelServiceBack;
import cn.mldn.hr.vo.Level;

public class LevelServiceBackImpl implements ILevelServiceBack {
	private DatabaseConnection dbc = new DatabaseConnection();
	@Override
	public boolean insert(Level vo) throws Exception {
		try {
			if (vo.getLosal() >= vo.getHisal()) {
				return false;
			}
			if (DAOFactory.getILevelDAOInstance(this.dbc.getConnection()).findByTitle(vo.getTitle()) == null) {
				return DAOFactory.getILevelDAOInstance(this.dbc.getConnection()).doCreate(vo);
			}
			return false;
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

	@Override
	public boolean update(Level vo) throws Exception {
		try {
			if (vo.getLosal() >= vo.getHisal()) {
				return false;
			}
			if (DAOFactory.getILevelDAOInstance(this.dbc.getConnection()).findByTitle(vo.getTitle(), vo.getLevid()) == null) {
				return DAOFactory.getILevelDAOInstance(this.dbc.getConnection()).doUpdate(vo);
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
			return DAOFactory.getILevelDAOInstance(this.dbc.getConnection()).doRemoveBatch(ids);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

	@Override
	public List<Level> list() throws Exception {
		try {
			return DAOFactory.getILevelDAOInstance(this.dbc.getConnection()).findAll();
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

}
