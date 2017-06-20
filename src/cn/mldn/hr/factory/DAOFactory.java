package cn.mldn.hr.factory;

import java.sql.Connection;

import cn.mldn.hr.dao.IActionDAO;
import cn.mldn.hr.dao.IAdminDAO;
import cn.mldn.hr.dao.IAdminlogsDAO;
import cn.mldn.hr.dao.ICourseDAO;
import cn.mldn.hr.dao.IDeptDAO;
import cn.mldn.hr.dao.IDetailsDAO;
import cn.mldn.hr.dao.IEmployeeDAO;
import cn.mldn.hr.dao.IGroupsDAO;
import cn.mldn.hr.dao.IJobsDAO;
import cn.mldn.hr.dao.ILevelDAO;
import cn.mldn.hr.dao.ILogDAO;
import cn.mldn.hr.dao.IRoleDAO;
import cn.mldn.hr.dao.ISalaryDAO;
import cn.mldn.hr.dao.IWorkDAO;
import cn.mldn.hr.dao.impl.ActionDAOImpl;
import cn.mldn.hr.dao.impl.AdminDAOImpl;
import cn.mldn.hr.dao.impl.AdminlogsDAOImpl;
import cn.mldn.hr.dao.impl.CourseDAOImpl;
import cn.mldn.hr.dao.impl.DeptDAOImpl;
import cn.mldn.hr.dao.impl.DetailsDAOImpl;
import cn.mldn.hr.dao.impl.EmployeeDAOImpl;
import cn.mldn.hr.dao.impl.GroupsDAOImpl;
import cn.mldn.hr.dao.impl.JobsDAOImpl;
import cn.mldn.hr.dao.impl.LevelDAOImpl;
import cn.mldn.hr.dao.impl.LogDAOImpl;
import cn.mldn.hr.dao.impl.RoleDAOImpl;
import cn.mldn.hr.dao.impl.SalaryDAOImpl;
import cn.mldn.hr.dao.impl.WorkDAOImpl;

public class DAOFactory {
	public static IAdminDAO getIAdminDAOInstance(Connection conn) {
		return new AdminDAOImpl(conn);
	}
	public static IActionDAO getIActionDAOInstance(Connection conn) {
		return new ActionDAOImpl(conn);
	}
	public static IAdminlogsDAO getIAdminlogsDAOInstance(Connection conn) {
		return new AdminlogsDAOImpl(conn);
	}
	public static IGroupsDAO getIGroupsDAOInstance(Connection conn) {
		return new GroupsDAOImpl(conn);
	}
	public static IDeptDAO getIDeptDAOInstance(Connection conn) {
		return new DeptDAOImpl(conn);
	}
	public static IJobsDAO getIJobsDAOInstance(Connection conn) {
		return new JobsDAOImpl(conn);
	}
	public static ILevelDAO getILevelDAOInstance(Connection conn) {
		return new LevelDAOImpl(conn);
	}
	public static IEmployeeDAO getIEmployeeDAOInstance(Connection conn) {
		return new EmployeeDAOImpl(conn);
	}
	public static ISalaryDAO getISalaryDAOInstance(Connection conn) {
		return new SalaryDAOImpl(conn);
	}
	public static IWorkDAO getIWorkDAOInstance(Connection conn) {
		return new WorkDAOImpl(conn);
	}
	public static ICourseDAO getICourseDAOInstance(Connection conn) {
		return new CourseDAOImpl(conn);
	}
	public static IDetailsDAO getIDetailsDAOInstance(Connection conn) {
		return new DetailsDAOImpl(conn);
	}
	public static ILogDAO getILogDAOInstance(Connection conn) {
		return new LogDAOImpl(conn);
	}
	public static IRoleDAO getIRoleDAOInstance(Connection conn) {
		return new RoleDAOImpl(conn);
	}
	
}
