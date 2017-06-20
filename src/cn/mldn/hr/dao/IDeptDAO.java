package cn.mldn.hr.dao;

import java.sql.SQLException;
import java.util.Map;
import java.util.Set;

import cn.mldn.hr.vo.Dept;
import cn.mldn.util.dao.IDAO;

public interface IDeptDAO extends IDAO<Integer, Dept> {
	/**
	 * 根据部门名称查询部门数据
	 * @param dname 部门名称
	 * @return 部门数据以Dept类对象的形式返回，如果没有数据返回null
	 * @throws SQLException
	 */
	public Dept findByDname(String dname) throws SQLException;
	
	/**
	 * 排除掉指定的部门编号查询部门数据
	 * @param dname 要判断的部门名称
	 * @param did 要排除的部门编号
	 * @return 如果查询到部门数据，则返回部门对象，否则返回null
	 * @throws SQLException
	 */
	public Dept findByDname(String dname, Integer did) throws SQLException;

	/**
	 * 增加雇员的统计人数
	 * @param did
	 * @param val
	 * @return
	 * @throws SQLException
	 */
	public boolean doUpdateAddCurrent(Integer did, Integer val) throws SQLException;
	
	/**
	 * 减少雇员的统计人数
	 * @param did
	 * @param val
	 * @return
	 * @throws SQLException
	 */
	public boolean doUpdateSubCurrent(Integer did, Integer val) throws SQLException;

	/**
	 * 表示的是根据雇员编号查询出这些雇员所在的部门的编号，以及每个部门的人数
	 * @param eids 要离职的一组雇员编号
	 * @return 包含有部门与雇员编号的集合，其中key表示的是部门编号，而value保存的是此部门的人数
	 * @throws SQLException
	 */
	public Map<Integer, Integer> findAllByEmployee(Set<Integer> eids) throws SQLException;
	
	
	
}
