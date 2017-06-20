package cn.mldn.hr.dao;

import java.sql.SQLException;

import cn.mldn.hr.vo.Dept;
import cn.mldn.hr.vo.Level;
import cn.mldn.util.dao.IDAO;

public interface ILevelDAO extends IDAO<Integer, Level> {
	public Level findByTitle(String title) throws SQLException;
	
	public Level findByTitle(String title, Integer levid) throws SQLException;

}
