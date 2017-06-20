package cn.mldn.hr.factory;

import cn.mldn.hr.service.back.IActionServiceBack;
import cn.mldn.hr.service.back.IAdminServiceBack;
import cn.mldn.hr.service.back.ICourseServiceBack;
import cn.mldn.hr.service.back.IDeptServiceBack;
import cn.mldn.hr.service.back.IDetailsServiceBack;
import cn.mldn.hr.service.back.IGroupsServiceBack;
import cn.mldn.hr.service.back.IJobsServiceBack;
import cn.mldn.hr.service.back.ILevelServiceBack;
import cn.mldn.hr.service.back.ILogServiceBack;
import cn.mldn.hr.service.back.IRoleServiceBack;
import cn.mldn.hr.service.back.ISalaryServiceBack;
import cn.mldn.hr.service.back.IWorkServiceBack;
import cn.mldn.hr.service.back.impl.ActionServiceBackImpl;
import cn.mldn.hr.service.back.impl.AdminServiceBackImpl;
import cn.mldn.hr.service.back.impl.CourseServiceBackImpl;
import cn.mldn.hr.service.back.impl.DeptServiceBackImpl;
import cn.mldn.hr.service.back.impl.DetailsServiceBackImpl;
import cn.mldn.hr.service.back.impl.GroupsServiceBackImpl;
import cn.mldn.hr.service.back.impl.JobsServiceBackImpl;
import cn.mldn.hr.service.back.impl.LevelServiceBackImpl;
import cn.mldn.hr.service.back.impl.LogServiceBackImpl;
import cn.mldn.hr.service.back.impl.RoleServiceBackImpl;
import cn.mldn.hr.service.back.impl.SalaryServiceBackImpl;
import cn.mldn.hr.service.back.impl.WorkServiceBackImpl;

public class ServiceBackFactory {
	public static IDeptServiceBack getIDeptServiceBackInstance() {
		return new DeptServiceBackImpl();
	}
	public static IJobsServiceBack getIJobsServiceBackInstance() {
		return new JobsServiceBackImpl();
	}
	public static ILevelServiceBack getILevelServiceBackInstance() {
		return new LevelServiceBackImpl();
	}
	public static ISalaryServiceBack getISalaryServiceBackInstance() {
		return new SalaryServiceBackImpl();
	}
	public static IWorkServiceBack getIWorkServiceBackInstance() {
		return new WorkServiceBackImpl();
	}
	public static ICourseServiceBack getICourseServiceBackInstance() {
		return new CourseServiceBackImpl();
	}
	public static IDetailsServiceBack getIDetailsServiceBackInstance() {
		return new DetailsServiceBackImpl();
	}
	public static ILogServiceBack getILogServiceBackInstance() {
		return new LogServiceBackImpl();
	}
	public static IGroupsServiceBack getIGroupsServiceBackInstance() {
		return new GroupsServiceBackImpl();
	}
	public static IActionServiceBack getIActionServiceBackInstance() {
		return new ActionServiceBackImpl();
	}
	public static IRoleServiceBack getIRoleServiceBackInstance() {
		return new RoleServiceBackImpl();
	}
	public static IAdminServiceBack getIAdminServiceBackInstance() {
		return new AdminServiceBackImpl();
	}
}
