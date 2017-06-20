package cn.mldn.hr.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Set;

import cn.mldn.hr.vo.Employee;
import cn.mldn.util.dao.IDAO;

public interface IEmployeeDAO extends IDAO<Integer, Employee> {
	/**
	 * 设置雇员状态的变更操作，如果是离职状态，则日期设置为当前日期
	 * @param ids
	 * @param outdate
	 * @return 如果更新成功返回true，否则返回false
	 * @throws SQLException
	 */
	public boolean doUpdateStatus(Integer status, Set<Integer> ids, Date outdate) throws SQLException;

	/**
	 * 根据雇员状态进行分页数据显示
	 * @param status 雇员状态，在职（status = 1），离职（status = 0）
	 * @param currentPage
	 * @param lineSize
	 * @param column
	 * @param keyWord
	 * @return
	 * @throws SQLException
	 */
	public List<Employee> findAllSplitByStatus(Integer status, Integer currentPage, Integer lineSize, 
			String column, String keyWord) throws SQLException;

	/**
	 * 根据雇员状态取得雇员的信息量
	 * @param status
	 * @param column
	 * @param keyWord
	 * @return
	 * @throws SQLException
	 */
	public Integer getAllCountByStatus(Integer status, String column, String keyWord) throws SQLException;

	/**
	 * 更新雇员的工资数据
	 * @param vo 包含有新工资、新级别、雇员编号
	 * @return
	 * @throws SQLException
	 */
	public boolean doUpdateSal(Employee vo) throws SQLException;
	
	
	public boolean doUpdateDeptAndJob(Employee vo) throws SQLException;
	
	/**
	 * 根据部门编号查询所有的雇员信息
	 * @param dids
	 * @return
	 * @throws SQLException
	 */
	public List<Employee> findAllByDept(Set<Integer> dids) throws SQLException;
}
