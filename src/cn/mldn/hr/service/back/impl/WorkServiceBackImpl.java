package cn.mldn.hr.service.back.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.mldn.hr.dbc.DatabaseConnection;
import cn.mldn.hr.factory.DAOFactory;
import cn.mldn.hr.service.back.IDeptServiceBack;
import cn.mldn.hr.service.back.ISalaryServiceBack;
import cn.mldn.hr.service.back.IWorkServiceBack;
import cn.mldn.hr.vo.Dept;

public class WorkServiceBackImpl implements IWorkServiceBack {
	private DatabaseConnection dbc = new DatabaseConnection();

	@Override
	public Map<String, Object> list(int currentPage, int lineSize,
			String column, String keyWord) throws Exception {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("allWorks", DAOFactory.getIWorkDAOInstance(this.dbc.getConnection()).findAllSplit(currentPage, lineSize, column, keyWord));
			map.put("workCount", DAOFactory.getIWorkDAOInstance(this.dbc.getConnection()).getAllCount(column, keyWord));
			return map;
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}
	

}
