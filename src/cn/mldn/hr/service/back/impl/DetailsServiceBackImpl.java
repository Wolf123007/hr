package cn.mldn.hr.service.back.impl;

import java.util.HashMap;
import java.util.Map;

import cn.mldn.hr.dbc.DatabaseConnection;
import cn.mldn.hr.factory.DAOFactory;
import cn.mldn.hr.service.back.IDetailsServiceBack;

public class DetailsServiceBackImpl implements IDetailsServiceBack {
	private DatabaseConnection dbc = new DatabaseConnection();
	@Override
	public Map<String, Object> list(int cid, int currentPage, int lineSize,
			String column, String keyWord) throws Exception {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("allDetails", DAOFactory.getIDetailsDAOInstance(this.dbc.getConnection()).findAllSplitByCourse(cid, currentPage, lineSize, column, keyWord));
			map.put("detailsCount", DAOFactory.getIDetailsDAOInstance(this.dbc.getConnection()).getAllCountByCourse(cid, column, keyWord));
			map.put("course", DAOFactory.getICourseDAOInstance(this.dbc.getConnection()).findById(cid));
			return map;
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

}
