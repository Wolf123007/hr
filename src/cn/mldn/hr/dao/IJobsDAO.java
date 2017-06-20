package cn.mldn.hr.dao;

import java.sql.SQLException;

import cn.mldn.hr.vo.Dept;
import cn.mldn.hr.vo.Jobs;
import cn.mldn.util.dao.IDAO;

public interface IJobsDAO extends IDAO<Integer, Jobs> {

	/**
	 * 根据职位名称查询出职位信息
	 * @param title 职位名称
	 * @return 如果查询到数据则返回对象，否则返回null
	 * @throws SQLException
	 */
	public Jobs findByTitle(String title) throws SQLException;

	/**
	 * 根据职位名称并且排队指定的jid数据查询出Jobs信息
	 * @param title
	 * @param jid
	 * @return
	 * @throws SQLException
	 */
	public Jobs findByTitle(String title, Integer jid) throws SQLException;

}
