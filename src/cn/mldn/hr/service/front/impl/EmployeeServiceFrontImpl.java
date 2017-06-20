package cn.mldn.hr.service.front.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import cn.mldn.hr.dbc.DatabaseConnection;
import cn.mldn.hr.factory.DAOFactory;
import cn.mldn.hr.service.front.IEmployeeServiceFront;
import cn.mldn.hr.vo.Dept;
import cn.mldn.hr.vo.Employee;
import cn.mldn.hr.vo.Jobs;
import cn.mldn.hr.vo.Level;
import cn.mldn.hr.vo.Salary;
import cn.mldn.hr.vo.Work;

public class EmployeeServiceFrontImpl implements IEmployeeServiceFront{
	private DatabaseConnection dbc = new DatabaseConnection();
	@Override
	public Map<String, Object> insertPre() throws Exception {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("allDepts", DAOFactory.getIDeptDAOInstance(this.dbc.getConnection()).findAll());
			map.put("allLevels", DAOFactory.getILevelDAOInstance(this.dbc.getConnection()).findAll());
			map.put("allJobs", DAOFactory.getIJobsDAOInstance(this.dbc.getConnection()).findAll());
			return map;
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}

	}

	@Override
	public boolean insert(Employee vo) throws Exception {
		try {
			// 1、判断要增加的雇员编号是否存在
			if (DAOFactory.getIEmployeeDAOInstance(this.dbc.getConnection())
					.findById(vo.getEid()) == null) { // 不存在
				// 2、查询工资范围，目的是为了判断用户输入的工资
				Level level = DAOFactory.getILevelDAOInstance(this.dbc.getConnection()).findById(vo.getLevel().getLevid());
				if (vo.getSal() >= level.getLosal() && vo.getSal() <= level.getHisal()) { // 表示工资合法
					// 3、查询部门信息，目的是取得部门名称
					vo.setDname(DAOFactory.getIDeptDAOInstance(this.dbc.getConnection()).findById(vo.getDept().getDid()).getDname());
					// 4、查询职位名称
					vo.setJob(DAOFactory.getIJobsDAOInstance(this.dbc.getConnection()).findById(vo.getJobs().getJid()).getTitle());
					// 5、判断当前的雇员状态
					if (vo.getStatus() == 0) { // 离职状态
						vo.setOutdate(new Date()); // 当前日期为离职信息
					}
					boolean flag = DAOFactory.getIEmployeeDAOInstance(this.dbc.getConnection()).doCreate(vo);
					if (flag) { // 雇员信息已经保存
						if (vo.getStatus() == 1) {
							DAOFactory.getIDeptDAOInstance(this.dbc.getConnection()).doUpdateAddCurrent(vo.getDept().getDid(), 1);
						}
					}
					return flag;
				}
			}
			return false;
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

	@Override
	public Map<String, Object> updatePre(int eid) throws Exception {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("allDepts", DAOFactory.getIDeptDAOInstance(this.dbc.getConnection()).findAll());
			map.put("allLevels", DAOFactory.getILevelDAOInstance(this.dbc.getConnection()).findAll());
			map.put("allJobs", DAOFactory.getIJobsDAOInstance(this.dbc.getConnection()).findAll());
			map.put("employee", DAOFactory.getIEmployeeDAOInstance(this.dbc.getConnection()).findById(eid));
			return map;
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

	@Override
	public boolean update(Employee vo) throws Exception {
		try {
			// 1、查询工资范围，目的是为了判断用户输入的工资
			Level level = DAOFactory.getILevelDAOInstance(this.dbc.getConnection()).findById(vo.getLevel().getLevid());
			if (vo.getSal() >= level.getLosal() && vo.getSal() <= level.getHisal()) { // 表示工资合法
				// 2、查询部门信息，目的是取得部门名称
				vo.setDname(DAOFactory.getIDeptDAOInstance(this.dbc.getConnection()).findById(vo.getDept().getDid()).getDname());
				// 3、查询职位名称
				vo.setJob(DAOFactory.getIJobsDAOInstance(this.dbc.getConnection()).findById(vo.getJobs().getJid()).getTitle());
				// 4、判断当前的雇员状态
				if (vo.getStatus() == 0) { // 离职状态
					vo.setOutdate(new Date()); // 当前日期为离职信息
				}
				// 5、在进行雇员数据的更新之前，必须要取得雇员数据的原始信息，否则无法进行对比的记录
				Employee oldEmp = DAOFactory.getIEmployeeDAOInstance(this.dbc.getConnection()).findById(vo.getEid());
				boolean flag = DAOFactory.getIEmployeeDAOInstance(this.dbc.getConnection()).doUpdate(vo);
				if (flag) {
					if (vo.getStatus() == 0) { // 离职
						DAOFactory.getIDeptDAOInstance(this.dbc.getConnection()).doUpdateSubCurrent(vo.getDept().getDid(), 1);
					}
					// 如果当前的工资发生修改，则必须进行相关数据的记录
					if (!(oldEmp.getSal().equals(vo.getSal()) && oldEmp.getLevel().getLevid().equals(vo.getLevel().getLevid()))) {
						// 8、需要准备出工资变更的情况
						Salary salary = new Salary();
						salary.getOldlevel().setLevid(oldEmp.getLevel().getLevid());
						salary.getNewlevel().setLevid(vo.getLevel().getLevid());
						salary.getEmployee().setEid(vo.getEid());
						salary.getAdmin().setAid(vo.getAdmin().getAid());
						salary.setCdate(new Date()); // 当前日期
						salary.setOldsal(oldEmp.getSal());
						salary.setNewsal(vo.getSal());
						salary.setReason("雇员信息更新的时候进行的调整！");
						salary.setNote("雇员信息修改操作");
						DAOFactory.getISalaryDAOInstance(this.dbc.getConnection()).doCreate(salary);
					}
					if (!(oldEmp.getDept().getDid().equals(vo.getDept().getDid()) && oldEmp.getJobs().getJid().equals(vo.getJobs().getJid()))) {
						// 9、职位变更情况
						Work work = new Work();
						work.getEmployee().setEid(vo.getEid());
						work.getAdmin().setAid(vo.getAdmin().getAid());
						work.getOlddept().setDid(vo.getDept().getDid());
						work.getNewdept().setDid(vo.getDept().getDid());
						work.getOldjobs().setJid(oldEmp.getJobs().getJid());
						work.getNewJobs().setJid(vo.getJobs().getJid());
						work.setCdate(new Date());
						work.setOlddname(oldEmp.getDname());
						work.setOldjob(oldEmp.getJob());
						work.setNewdname(vo.getDname());
						work.setNewjob(vo.getJob());
						work.setReason("雇员信息更新的时候进行的调整！");
						work.setNote("雇员信息修改操作");
						DAOFactory.getIWorkDAOInstance(this.dbc.getConnection()).doCreate(work);
					}
				}
				return flag;
				
			}
			return false;
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

	@Override
	public boolean updateOut(Set<Integer> ids) throws Exception {
		try {
			if (ids.size() == 0) {
				return false;
			}
			Map<Integer, Integer> map = DAOFactory.getIDeptDAOInstance(this.dbc.getConnection()).findAllByEmployee(ids);
			boolean flag = DAOFactory.getIEmployeeDAOInstance(this.dbc.getConnection()).doUpdateStatus(0, ids, new Date());
			if (flag) { // 所有的离职状态都修改完毕
				Set<Map.Entry<Integer, Integer>> entrySet = map.entrySet();
				Iterator<Map.Entry<Integer, Integer>> iter = entrySet.iterator();
				while (iter.hasNext()) {
					Map.Entry<Integer, Integer> entry = iter.next();
					DAOFactory.getIDeptDAOInstance(this.dbc.getConnection()).doUpdateSubCurrent(entry.getKey(), entry.getValue());
				}
			}
			return flag;
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
			map.put("allEmployees", DAOFactory.getIEmployeeDAOInstance(this.dbc.getConnection()).findAllSplit(currentPage, lineSize, column, keyWord));
			map.put("employeeCount", DAOFactory.getIEmployeeDAOInstance(this.dbc.getConnection()).getAllCount(column, keyWord));
			return map;
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

	@Override
	public Map<String, Object> listByStatus(int currentPage, int lineSize,
			String column, String keyWord, int status) throws Exception {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("allEmployees", DAOFactory.getIEmployeeDAOInstance(this.dbc.getConnection()).findAllSplitByStatus(status, currentPage, lineSize, column, keyWord));
			map.put("employeeCount", DAOFactory.getIEmployeeDAOInstance(this.dbc.getConnection()).getAllCountByStatus(status, column, keyWord));
			return map;
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

	@Override
	public Map<String, Object> updateSalPre(int eid) throws Exception {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			Employee vo = DAOFactory.getIEmployeeDAOInstance(this.dbc.getConnection()).findById(eid);
			if (vo != null) { // 可以查找到该雇员的信息
				map.put("allLevels", DAOFactory.getILevelDAOInstance(this.dbc.getConnection()).findAll());
				vo.setLevel(DAOFactory.getILevelDAOInstance(this.dbc.getConnection()).findById(vo.getLevel().getLevid()));
			}
			map.put("employee", vo);
			return map;
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

	@Override
	public boolean updateSal(Employee vo) throws Exception {
		try {
			// 查询出已有的雇员信息
			Employee oldEmp = DAOFactory.getIEmployeeDAOInstance(this.dbc.getConnection()).findById(vo.getEid());
			if (oldEmp != null) { // 数据可以查询到
				// 查询出当前设置的范围
				Level level = DAOFactory.getILevelDAOInstance(this.dbc.getConnection()).findById(vo.getLevel().getLevid());
				if (vo.getSal() >= level.getLosal() && vo.getSal() <= level.getHisal()) {
					if (DAOFactory.getIEmployeeDAOInstance(this.dbc.getConnection()).doUpdateSal(vo)) { // employee表更新完成
						// 如果当前的工资发生修改，则必须进行相关数据的记录
						if (!(oldEmp.getSal().equals(vo.getSal()) && oldEmp.getLevel().getLevid().equals(vo.getLevel().getLevid()))) {
							// 需要准备出工资变更的情况
							Salary salary = vo.getSalary();
							salary.setNewlevel(level); // 保存新的级别信息
							salary.setNewsal(vo.getSal());
							salary.getOldlevel().setLevid(oldEmp.getLevel().getLevid());
							salary.getEmployee().setEid(vo.getEid());
							salary.getAdmin().setAid(vo.getAdmin().getAid());
							salary.setCdate(new Date()); // 当前日期
							salary.setOldsal(oldEmp.getSal());
							DAOFactory.getISalaryDAOInstance(this.dbc.getConnection()).doCreate(salary);
						}
						return true;
					}
				}
			}
			return false;
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

	@Override
	public Map<String, Object> updateDeptAndJobPre(int eid) throws Exception {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			Employee vo = DAOFactory.getIEmployeeDAOInstance(this.dbc.getConnection()).findById(eid);
			if (vo != null) { // 可以查找到该雇员的信息
				map.put("allDepts", DAOFactory.getIDeptDAOInstance(this.dbc.getConnection()).findAll());
				map.put("allJobs", DAOFactory.getIJobsDAOInstance(this.dbc.getConnection()).findAll());
			}
			map.put("employee", vo);
			return map;
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
	}

	@Override
	public boolean updateDeptAndJob(Employee vo) throws Exception {
		try {
			// 1、查询出已有的雇员数据
			Employee oldEmp = DAOFactory.getIEmployeeDAOInstance(this.dbc.getConnection()).findById(vo.getEid());
			// 2、查询出部门信息
			Dept dept = DAOFactory.getIDeptDAOInstance(this.dbc.getConnection()).findById(vo.getDept().getDid());
			// 3、查询出职位信息
			Jobs jobs = DAOFactory.getIJobsDAOInstance(this.dbc.getConnection()).findById(vo.getJobs().getJid());
			vo.setDname(dept.getDname()); // 设置重复的部门名称
			vo.setJob(jobs.getTitle()); // 设置重复的职位名称
			// 4、判断此时的雇员信息是否存在
			if (oldEmp != null) { // 存在信息
				// 5、更新employee表中的内容
				boolean  flag = DAOFactory.getIEmployeeDAOInstance(this.dbc.getConnection()).doUpdateDeptAndJob(vo);
				if (flag) { // 6、要判断是否需要增加职位变动信息
					// 如果数据前后不一至，则进行数据更新
					if (!(oldEmp.getDept().getDid().equals(vo.getDept().getDid()) && oldEmp.getJobs().getJid().equals(vo.getJobs().getJid()))) {
						// 7、准备进行数据更新操作
						Work work = vo.getWork();
						work.getOlddept().setDid(oldEmp.getDept().getDid());
						work.getOldjobs().setJid(oldEmp.getJobs().getJid());
						work.setOldjob(oldEmp.getJob());
						work.setOlddname(oldEmp.getDname());
						work.getAdmin().setAid(vo.getAdmin().getAid());
						work.setCdate(new Date());
						work.setNewdept(dept);
						work.setNewJobs(jobs);
						work.setNewdname(dept.getDname());
						work.setNewjob(jobs.getTitle());
						work.getEmployee().setEid(vo.getEid());
						DAOFactory.getIWorkDAOInstance(this.dbc.getConnection()).doCreate(work);
					}
					return flag;
				}
			}
		} catch (Exception e) {
			throw e;
		} finally {
			this.dbc.close();
		}
		return false;
	}









}
