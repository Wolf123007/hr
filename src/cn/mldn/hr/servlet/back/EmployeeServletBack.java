package cn.mldn.hr.servlet.back;

import java.util.Map;

import javax.servlet.annotation.WebServlet;

import cn.mldn.hr.factory.ServiceFrontFactory;
import cn.mldn.util.servlet.DispatcherServlet;

@SuppressWarnings("serial")
@WebServlet(name="EmployeeServletBack", urlPatterns="/pages/back/admin/employee/EmployeeServletBack/*")
public class EmployeeServletBack extends DispatcherServlet {
	
	public String listIn() {
		super.handleSplit(); // 处理分页
		try {
			int status = 1;
			Map<String, Object> map = ServiceFrontFactory.getIEmployeeServiceFrontInstance().listByStatus(super.getCurrentPage(), super.getLineSize(), super.getColumn(), super.getKeyWord(), status);
			super.request.setAttribute("url", "/pages/front/employee/EmployeeServletFront/listIn");
			super.request.setAttribute("allRecorders", map.get("employeeCount"));
			super.request.setAttribute("allEmployees", map.get("allEmployees"));
			super.request.setAttribute("paramName", "status");
			super.request.setAttribute("paramValue", String.valueOf(status));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "back.employee.list.page";
	}
	
	public String listOut() {
		super.handleSplit(); // 处理分页
		try {
			int status = 0;
			Map<String, Object> map = ServiceFrontFactory.getIEmployeeServiceFrontInstance().listByStatus(super.getCurrentPage(), super.getLineSize(), super.getColumn(), super.getKeyWord(), status);
			super.request.setAttribute("url", "/pages/front/employee/EmployeeServletFront/listOut");
			super.request.setAttribute("allRecorders", map.get("employeeCount"));
			super.request.setAttribute("allEmployees", map.get("allEmployees"));
			super.request.setAttribute("paramName", "status");
			super.request.setAttribute("paramValue", String.valueOf(status));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "back.employee.list.page";
	}
	
	
	
	@Override
	public String getTitle() {
		return "雇员";
	}

	@Override
	public String getUploadDirectory() {
		return "/upload/employee/";
	}

	@Override
	public String getColumnData() {
		return "姓名:ename|部门名称:dname|职位名称:job|毕业院校:school|雇员学历:edu|雇员专业:profession";
	}

	@Override
	public String getDefaultColumn() {
		return "ename";
	}

}
