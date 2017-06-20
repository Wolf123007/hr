package cn.mldn.hr.service.front.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.mldn.hr.dbc.DatabaseConnection;
import cn.mldn.hr.factory.DAOFactory;
import cn.mldn.hr.service.front.ICourseServiceFront;
import cn.mldn.hr.vo.Course;
import cn.mldn.hr.vo.Dept;
import cn.mldn.hr.vo.Details;
import cn.mldn.hr.vo.Employee;
import cn.mldn.hr.vo.Log;

public class CourseServiceFrontImpl implements ICourseServiceFront {
	private DatabaseConnection dbc = new DatabaseConnection();
	@Override
	public boolean insert(Course vo, Set<Integer> dids) throws Exception {
		try {
			// 1、保存课程信息内容
			if (DAOFactory.getICourseDAOInstance(this.dbc.getConnection()).doCreate(vo)) { // 此时没有关闭
				// 2、取得当前增长后的课程编号
				Integer cid = DAOFactory.getICourseDAOInstance(this.dbc.getConnection()).getId();
				// 3、取出全部的部门雇员信息
				List<Employee> allEmps = DAOFactory.getIEmployeeDAOInstance(this.dbc.getConnection()).findAllByDept(dids);
				// 准备好一个对象用于保存所有的考勤数据
				Set<Log> allLogs = new HashSet<Log>();
				// 4、需要准备好Details对象，而这个对象需要课程编号、雇员编号、雇员姓名、成绩为0
				Iterator<Employee> iter = allEmps.iterator();
				while (iter.hasNext()) { // 根据雇员数据进行详情的创建
					Details details = new Details();
					Employee employee = iter.next();
					details.getCourse().setCid(cid);
					details.getEmployee().setEid(employee.getEid());
					details.setEname(employee.getEname());
					details.setScore(0.0);
					// 5、保存生成的详情，同时还需要取出详情当前的ID内容
					if (DAOFactory.getIDetailsDAOInstance(this.dbc.getConnection()).doCreate(details)) {
						// 6、取出当前增长的详情编号
						Integer dtid = DAOFactory.getIDetailsDAOInstance(this.dbc.getConnection()).getId();
						// 7、还需要创建考勤日志，考勤与培训天数有关系
						for (int i = 0; i < vo.getTotal(); i++) {
							Log log = new Log();
							log.getDetails().setDtid(dtid);
							//log.setRecdate(new Date());
							log.setNum(i);
							log.setStatus(0);
							allLogs.add(log); // 保存统一的信息
						}
					}
				}
				// 8、批量保存所有的日志信息
				return DAOFactory.getILogDAOInstance(this.dbc.getConnection()).doCreateBatch(allLogs);
			}
			return false;
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

	@Override
	public Map<String, Object> list(int currentPage, int lineSize,
			String column, String keyWord) throws Exception {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("allCourses", DAOFactory.getICourseDAOInstance(this.dbc.getConnection()).findAllSplit(currentPage, lineSize, column, keyWord));
			map.put("courseCount", DAOFactory.getICourseDAOInstance(this.dbc.getConnection()).getAllCount(column, keyWord));
			return map;
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

	@Override
	public Course updatePre(int cid) throws Exception {
		try {
			return DAOFactory.getICourseDAOInstance(this.dbc.getConnection()).findById(cid);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

	@Override
	public boolean update(Course vo) throws Exception {
		try {
			return DAOFactory.getICourseDAOInstance(this.dbc.getConnection()).doUpdate(vo);
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

	@Override
	public List<Dept> insertPre() throws Exception {
		try {
			return DAOFactory.getIDeptDAOInstance(this.dbc.getConnection()).findAll();
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
