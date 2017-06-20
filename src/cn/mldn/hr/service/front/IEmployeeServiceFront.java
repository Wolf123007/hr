package cn.mldn.hr.service.front;

import java.util.Map;
import java.util.Set;

import cn.mldn.hr.vo.Employee;

public interface IEmployeeServiceFront {
	
	/**
	 * 执行雇员增加前的数据查询操作，查询全部的职位、部门、级别
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> insertPre() throws Exception;
	
	/**
	 * 雇员增加操作，需要执行如下的功能；<br>
	 * <li>1、要根据雇员的部门编号查询出部门名称</li>
	 * <li>2、要根据雇员的职位编号查询出职位名称</li>
	 * <li>3、要根据雇员的工资等级查询出等级范围，确认雇员工资是否合理</li>
	 * <li>4、要判断雇员的状态是在职还是离职，如果是离职则需要设置离职时间</li>
	 * <li>5、要判断雇员编号是否存在</li>
	 * <li>6、进行雇员数据的保存</li>
	 * @param vo
	 * @return 
	 * @throws Exception
	 */
	public boolean insert(Employee vo) throws Exception;
	
	/**
	 * 更新前的查询操作，查询全部的职位、部门、级别信息
	 * @param eid
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> updatePre(int eid) throws Exception;
	
	/**
	 * 雇员增加操作，需要执行如下的功能；<br>
	 * <li>1、要根据雇员的部门编号查询出部门名称</li>
	 * <li>2、要根据雇员的职位编号查询出职位名称</li>
	 * <li>3、要根据雇员的工资等级查询出等级范围，确认雇员工资是否合理</li>
	 * <li>4、要判断雇员的状态是在职还是离职，如果是离职则需要设置离职时间</li>
	 * <li>5、进行雇员数据的更新</li>
	 * @param vo
	 * @return 
	 * @throws Exception
	 */
	public boolean update(Employee vo) throws Exception;
	
	/**
	 * 批量更新雇员的离职状态，离职日期就只设置为当前日期
	 * @param ids 当前的雇员编号
	 * @return
	 * @throws Exception
	 */
	public boolean updateOut(Set<Integer> ids) throws Exception;
	
	/**
	 * 全部雇员信息的列出
	 * @param currentPage
	 * @param lineSize
	 * @param column
	 * @param keyWord
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> list(int currentPage, int lineSize, String column, String keyWord) throws Exception;
	
	/**
	 * 根据雇员的在职与离职状态进行列出
	 * @param currentPage
	 * @param lineSize
	 * @param column
	 * @param keyWord
	 * @param status
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> listByStatus(int currentPage, int lineSize, String column, String keyWord, int status) throws Exception;

	/**
	 * 雇员更新工资前的数据查询操作，本操作要执行如下功能：<br>
	 * 	<li>调用IEmployeeDAO.findById()方法根据指定的雇员编号查询雇员信息</li>
	 * 	<li>调用ILevelDAO.findAll()方法查询出所有的工资级别</li>
	 * @param eid 要修改工资的雇员编号
	 * @return 通过Map集合返回数据，包含内容如下：<br>
	 * 	<li>key = employee, value = IEmployeeDAO.findById()，保存的是Employee对象</li>
	 * 	<li>key = allLevels, value = ILevelDAO.findAll()，保存的是List<Level>对象</li>
	 * @throws Exception
	 */
	public Map<String, Object> updateSalPre(int eid) throws Exception;
	
	/**
	 * 雇员信息的更新操作，在本操作之中需要如下内容：雇员编号、新工资、新级别、操作的管理员信息
	 * 本操作要执行如下的数据层功能：<br>
	 * 	<li>要根据雇员编号查询出已有的雇员原始数据，目的是进行相同的匹配</li>
	 * 	<li>更新雇员的工资信息，调用IEmployeeDAO.doUpdateSal()方法</li>
	 * 	<li>调用ILevelDAO.findById()方法查询出一个level的信息，这个信息通过employee对象保存</li>
	 * 	<li>如果工资修改成功，则要在Salary表中进行记录，调用ISalaryDAO.doCreate()方法</li>
	 * @param vo
	 * @return 更新成功返回true，否则返回false
	 * @throws Exception
	 */
	public boolean updateSal(Employee vo) throws Exception;
	
	/**
	 * 执行雇员职位变更前的查询操作，该操作包含有如下内容：<br>
	 * <li>调用IEmployeeDAO.findById()查询一个雇员的原始信息</li>
	 * <li>调用IDeptDAO.findAll()查询出所有的部门信息</li>
	 * <li>调用IJobsDAO.findAll()查询出所有的职位信息</li>
	 * @param eid 要查询的雇员编号
	 * @return 以Map集合的形式返回，包含有如下内容：<br>
	 * <li>key = employee; value = IEmployeeDAO.findById()，返回Employee</li>
	 * <li>key = allDepts; value = IDeptDAO.findAll()，返回List<Dept></li>
	 * <li>key = allJobs; value = IJobsDAO.findAll()，返回List<Jobs></li>
	 * 
	 * @throws Exception
	 */
	public Map<String, Object> updateDeptAndJobPre(int eid) throws Exception;
	
	/**
	 * 执行职位的变更操作，包含如下内容：<br>
	 * 	<li>首先要查询出的原始的雇员信息，以确定信息是否发生变化，由此判断业决定是否向work表中增加内容</li>
	 * 	<li>要向雇员表中执行更新操作，调用IEmpDAO.doUpdateDeptAndJob()方法</li>
	 * 	<li>如果发生内容变更，则调用IWorkDAO.doCreate()方法</li>
	 * @param vo
	 * @return 更新成功返回true，否则返回false
	 * @throws Exception
	 */
	public boolean updateDeptAndJob(Employee vo) throws Exception;
	
	
	
	
	
	
}
