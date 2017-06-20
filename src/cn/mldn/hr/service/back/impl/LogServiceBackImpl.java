package cn.mldn.hr.service.back.impl;

import java.util.List;

import cn.mldn.hr.dbc.DatabaseConnection;
import cn.mldn.hr.factory.DAOFactory;
import cn.mldn.hr.service.back.ILogServiceBack;
import cn.mldn.hr.vo.Log;

public class LogServiceBackImpl implements ILogServiceBack {
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

}
