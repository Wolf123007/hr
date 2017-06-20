package cn.mldn.hr.factory;

import cn.mldn.hr.service.front.IAdminServiceFront;
import cn.mldn.hr.service.front.ICourseServiceFront;
import cn.mldn.hr.service.front.IDetailsServiceFront;
import cn.mldn.hr.service.front.IEmployeeServiceFront;
import cn.mldn.hr.service.front.ILogServiceFront;
import cn.mldn.hr.service.front.impl.AdminServiceFrontImpl;
import cn.mldn.hr.service.front.impl.CourseServiceFrontImpl;
import cn.mldn.hr.service.front.impl.DetailsServiceFrontImpl;
import cn.mldn.hr.service.front.impl.EmployeeServiceFrontImpl;
import cn.mldn.hr.service.front.impl.LogServiceFrontImpl;

public class ServiceFrontFactory {
	public static IAdminServiceFront getIAdminServiceFrontInstance() {
		return new AdminServiceFrontImpl();
	}
	public static IEmployeeServiceFront getIEmployeeServiceFrontInstance() {
		return new EmployeeServiceFrontImpl();
	}
	public static ICourseServiceFront getICourseServiceFrontInstance() {
		return new CourseServiceFrontImpl();
	}
	public static IDetailsServiceFront getIDetailsServiceFrontInstance() {
		return new DetailsServiceFrontImpl();
	}
	public static ILogServiceFront getILogServiceFrontInstance() {
		return new LogServiceFrontImpl();
	}
}
