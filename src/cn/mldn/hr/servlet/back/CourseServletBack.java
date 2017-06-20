package cn.mldn.hr.servlet.back;

import java.util.Map;

import javax.servlet.annotation.WebServlet;

import cn.mldn.hr.factory.ServiceBackFactory;
import cn.mldn.hr.vo.Employee;
import cn.mldn.util.servlet.DispatcherServlet;

@SuppressWarnings("serial")
@WebServlet(name="CourseServletBack", urlPatterns="/pages/back/admin/course/CourseServletBack/*")
public class CourseServletBack extends DispatcherServlet {
	private final String listEmpValidate = "employee.eid";
	
	private Employee employee = new Employee();
	public Employee getEmployee() {
		return employee;
	}
	
	public String list() {
		super.handleSplit(); // 处理分页
		try {
			Map<String, Object> map = ServiceBackFactory.getICourseServiceBackInstance().list(super.getCurrentPage(), super.getLineSize(), super.getColumn(), super.getKeyWord());
			super.request.setAttribute("url", "/pages/back/admin/course/CourseServletBack/list");
			super.request.setAttribute("allRecorders", map.get("courseCount"));
			super.request.setAttribute("allCourses", map.get("allCourses"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "back.course.list.page";
	}
	
	public String listEmp() {
		super.handleSplit(); // 处理分页
		try {
			Map<String, Object> map = ServiceBackFactory.getICourseServiceBackInstance().listByEmployee(this.employee.getEid(), super.getCurrentPage(), super.getLineSize(), super.getColumn(), super.getKeyWord());
			super.request.setAttribute("url", "/pages/back/admin/course/CourseServletBack/list");
			super.request.setAttribute("allRecorders", map.get("courseCount"));
			super.request.setAttribute("allCourses", map.get("allCourses"));
			super.request.setAttribute("paramName", "employee.eid");
			super.request.setAttribute("paramValue", String.valueOf(this.employee.getEid()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "back.employee.course.list.page";
	}
	
	
	@Override
	public String getTitle() {
		return "课程";
	}

	@Override
	public String getUploadDirectory() {
		return "/upload/course/";
	}

	@Override
	public String getColumnData() {
		return "课程名称:cname";
	}

	@Override
	public String getDefaultColumn() {
		return "cname";
	}
	





}
