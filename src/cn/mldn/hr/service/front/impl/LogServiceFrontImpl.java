package cn.mldn.hr.service.front.impl;

import java.util.Date;
import java.util.List;

import cn.mldn.hr.dbc.DatabaseConnection;
import cn.mldn.hr.factory.DAOFactory;
import cn.mldn.hr.service.front.ILogServiceFront;
import cn.mldn.hr.vo.Log;

public class LogServiceFrontImpl implements ILogServiceFront {
	private DatabaseConnection dbc = new DatabaseConnection();
	@Override
	public List<Log> list(int dtid) throws Exception {
		try {
			return DAOFactory.getILogDAOInstance(this.dbc.getConnection()).findAllByDetails(dtid);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}
	@Override
	public boolean updateStatus(Log vo) throws Exception {
		if (vo.getStatus() == 0) {
			return false;
		}
		vo.setRecdate(new Date());
		try {
			return DAOFactory.getILogDAOInstance(this.dbc.getConnection()).doUpdateStatus(vo);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

}
