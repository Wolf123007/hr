package cn.mldn.hr.dao;

import java.sql.SQLException;
import java.util.List;

import cn.mldn.hr.vo.Details;
import cn.mldn.util.dao.IDAO;

public interface IDetailsDAO extends IDAO<Integer, Details> {
	/**
	 * 取得增长后ID内容
	 * @return
	 * @throws SQLException
	 */
	public Integer getId() throws SQLException;
	
	/**
	 * 根据课程编号列出所有的详情信息
	 * @param cid 课程编号
	 * @param currentPage
	 * @param lineSize
	 * @param column
	 * @param keyWord
	 * @return
	 * @throws SQLException
	 */
	public List<Details> findAllSplitByCourse(Integer cid, Integer currentPage, Integer lineSize, 
			String column, String keyWord) throws SQLException;
	
	/**
	 * 根据课程编号查询参加此课程的全部个数
	 * @param cid
	 * @param column
	 * @param keyWord
	 * @return
	 * @throws SQLException
	 */
	public Integer getAllCountByCourse(Integer cid, String column, String keyWord) throws SQLException;
	
	/**
	 * 修改课程详情列表中的分数
	 * @param vo
	 * @return
	 * @throws SQLException
	 */
	public boolean doUpdateScore(Details vo) throws SQLException;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
