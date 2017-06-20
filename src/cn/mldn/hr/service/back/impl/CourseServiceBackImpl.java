package cn.mldn.hr.service.back.impl;

import java.util.HashMap;
import java.util.Map;

import cn.mldn.hr.dbc.DatabaseConnection;
import cn.mldn.hr.factory.DAOFactory;
import cn.mldn.hr.service.back.ICourseServiceBack;

public class CourseServiceBackImpl implements ICourseServiceBack {
	private DatabaseConnection dbc = new DatabaseConnection();
	

	@Override
	public Map<String, Object> list(int currentPage, int lineSize,
			String column, String keyWord) throws Exception {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("allCourses", DAOFactory.getICourseDAOInstance(this.dbc.getConnection()).findAllSplit(currentPage, lineSize, column, keyWord));
			map.put("courseCount", DAOFactory.getICourseDAOInstance(this.dbc.getConnection()).getAllCount(column, keyWord));
			return map;
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}


	@Override
	public Map<String, Object> listByEmployee(int eid, int currentPage,
			int lineSize, String column, String keyWord) throws Exception {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("allCourses", DAOFactory.getICourseDAOInstance(this.dbc.getConnection()).findAllSplitByEmployee(eid, currentPage, lineSize, column, keyWord));
			map.put("courseCount", DAOFactory.getICourseDAOInstance(this.dbc.getConnection()).getAllCountByEmployee(eid, column, keyWord));
			return map;
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
