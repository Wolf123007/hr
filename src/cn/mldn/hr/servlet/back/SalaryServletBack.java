package cn.mldn.hr.servlet.back;

import java.util.Map;

import javax.servlet.annotation.WebServlet;

import cn.mldn.hr.factory.ServiceBackFactory;
import cn.mldn.util.servlet.DispatcherServlet;
@WebServlet(urlPatterns="/pages/back/admin/employee/SalaryServletBack/*")
public class SalaryServletBack extends DispatcherServlet {
	
	public String list() {
		super.handleSplit(); // 处理分页
		try {
			Map<String, Object> map = ServiceBackFactory.getISalaryServiceBackInstance().list(super.getCurrentPage(), super.getLineSize(), super.getColumn(), super.getKeyWord());
			super.request.setAttribute("url", "/pages/back/admin/employee/SalaryServletBack/list");
			super.request.setAttribute("allRecorders", map.get("salaryCount"));
			super.request.setAttribute("allSalarys", map.get("allSalarys"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "back.salary.list.page";
	}
	

	@Override
	public String getTitle() {
		return "工资";
	}

	@Override
	public String getUploadDirectory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getColumnData() {
		return "雇员编号:eid|管理员:aid";
	}

	@Override
	public String getDefaultColumn() {
		return "eid";
	}

}
