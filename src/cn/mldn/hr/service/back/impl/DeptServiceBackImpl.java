package cn.mldn.hr.service.back.impl;

import java.util.List;
import java.util.Set;

import cn.mldn.hr.dbc.DatabaseConnection;
import cn.mldn.hr.factory.DAOFactory;
import cn.mldn.hr.service.back.IDeptServiceBack;
import cn.mldn.hr.vo.Dept;

public class DeptServiceBackImpl implements IDeptServiceBack {
	private DatabaseConnection dbc = new DatabaseConnection();
	@Override
	public boolean insert(Dept vo) throws Exception {
		try {
			if (DAOFactory.getIDeptDAOInstance(this.dbc.getConnection()).findByDname(vo.getDname())== null) { // 部门名称不存在
				vo.setCurrent(0); // 这样设置的目的是取消外部对数据的控制
				return DAOFactory.getIDeptDAOInstance(this.dbc.getConnection()).doCreate(vo);
			}
			return false;
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		
	}

	@Override
	public boolean update(Dept vo) throws Exception {
		try {
			if (DAOFactory.getIDeptDAOInstance(this.dbc.getConnection()).findByDname(vo.getDname(), vo.getDid()) == null) {
				return DAOFactory.getIDeptDAOInstance(this.dbc.getConnection()).doUpdate(vo);
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
			return DAOFactory.getIDeptDAOInstance(this.dbc.getConnection()).doRemoveBatch(ids);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

	@Override
	public List<Dept> list() throws Exception {
		try {
			return DAOFactory.getIDeptDAOInstance(this.dbc.getConnection()).findAll();
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

}
