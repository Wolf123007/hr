package cn.mldn.hr.service.front.impl;

import java.util.HashMap;
import java.util.Map;

import cn.mldn.hr.dbc.DatabaseConnection;
import cn.mldn.hr.factory.DAOFactory;
import cn.mldn.hr.service.front.IDetailsServiceFront;
import cn.mldn.hr.vo.Details;

public class DetailsServiceFrontImpl implements IDetailsServiceFront {
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
	@Override
	public boolean updateScore(Details vo) throws Exception {
		if (vo.getScore() < 0.0) {
			return false;
		}
		try {
			return DAOFactory.getIDetailsDAOInstance(this.dbc.getConnection()).doUpdateScore(vo);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

}
