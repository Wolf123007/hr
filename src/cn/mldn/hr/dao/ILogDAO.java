package cn.mldn.hr.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import cn.mldn.hr.vo.Log;
import cn.mldn.util.dao.IDAO;

public interface ILogDAO extends IDAO<Integer, Log> {
	/**
	 * 进行数据的批处理操作，批量增加日志信息
	 * @param vos 包含了所有的日志对象
	 * @return 增加成功返回true，否则返回false
	 * @throws SQLException
	 */
	public boolean doCreateBatch(Set<Log> vos) throws SQLException;
	
	/**
	 * 进行考勤的列表显示
	 * @param dtid
	 * @return
	 * @throws SQLException
	 */
	public List<Log> findAllByDetails(Integer dtid) throws SQLException;
	
	/**
	 * 更新考勤表中的状态
	 * @param vo
	 * @return
	 * @throws SQLException
	 */
	public boolean doUpdateStatus(Log vo) throws SQLException;
}
