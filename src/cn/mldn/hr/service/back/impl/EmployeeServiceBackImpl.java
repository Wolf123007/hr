package cn.mldn.hr.service.back.impl;

import java.util.HashMap;
import java.util.Map;

import cn.mldn.hr.dbc.DatabaseConnection;
import cn.mldn.hr.factory.DAOFactory;
import cn.mldn.hr.service.back.IEmployeeServiceBack;

public class EmployeeServiceBackImpl implements IEmployeeServiceBack {
	private DatabaseConnection dbc = new DatabaseConnection();
	@Override
	public Map<String, Object> listByStatus(int currentPage, int lineSize,
			String column, String keyWord, int status) throws Exception {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("allEmployees", DAOFactory.getIEmployeeDAOInstance(this.dbc.getConnection()).findAllSplitByStatus(status, currentPage, lineSize, column, keyWord));
			map.put("employeeCount", DAOFactory.getIEmployeeDAOInstance(this.dbc.getConnection()).getAllCountByStatus(status, column, keyWord));
			return map;
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

}
