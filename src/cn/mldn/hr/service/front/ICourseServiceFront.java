package cn.mldn.hr.service.front;

import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.mldn.hr.vo.Course;
import cn.mldn.hr.vo.Dept;

public interface ICourseServiceFront {
	/**
	 * 进行课程信息的发布，发布的同时需要注意课程的内容以及相关操作，具体流程如下：<br>
	 * 	<li>首先要创建课程信息，调用ICourseDAO.doCreate()</li>
	 * 	<li>为了进行课程详情的创建，那么必须取得当前的课程编号，使用ICourseDAO.getId()</li>
	 * 	<li>利用给出的全部部门编号，查询出对应的所有雇员信息，IEmployeeDAO.findByDept()</li>
	 * 	<li>随后设置Details类的VO对象，将课程编号以及雇员编号、姓名进行保存，成绩都设置为0.0</li>
	 * 	<li>分别调用IDetailsDAO中的doCreate()方法增加每一个详情，但是要同时取回增加的ID给日志用</li>
	 * 	<li>所有的日志对象统一保存在一个集合之中，但是num内容由循环来决定</li>
	 * @param vo
	 * @param dids
	 * @return
	 * @throws Exception
	 */
	public boolean insert(Course vo, Set<Integer> dids) throws Exception;
	
	public Map<String, Object> list(int currentPage, int lineSize, String column, String keyWord) throws Exception;

	public Course updatePre(int cid) throws Exception;
	
	public boolean update(Course vo) throws Exception;

	public List<Dept> insertPre() throws Exception;
}
