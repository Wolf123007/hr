package cn.mldn.hr.dao;

import java.sql.SQLException;
import java.util.List;

import cn.mldn.hr.vo.Course;
import cn.mldn.util.dao.IDAO;

public interface ICourseDAO extends IDAO<Integer, Course> {
	/**
	 * 取得增加后的ID内容
	 * @return
	 * @throws SQLException
	 */
	public Integer getId() throws SQLException;
	
	/**
	 * 查看一个雇员所参加的全部课程
	 * @param eid
	 * @param currentPage
	 * @param lineSize
	 * @param column
	 * @param keyWord
	 * @return
	 * @throws SQLException
	 */
	public List<Course> findAllSplitByEmployee(Integer eid, Integer currentPage, Integer lineSize, String column, String keyWord) throws SQLException;

	public Integer getAllCountByEmployee(Integer eid, String column, String keyWord) throws SQLException;

}
