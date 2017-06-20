package cn.mldn.hr.servlet.back;

import java.util.Map;

import javax.servlet.annotation.WebServlet;

import cn.mldn.hr.factory.ServiceBackFactory;
import cn.mldn.util.servlet.DispatcherServlet;
@SuppressWarnings("serial")
@WebServlet(urlPatterns="/pages/back/admin/employee/WorkServletBack/*")
public class WorkServletBack extends DispatcherServlet {
	
	public String list() {
		super.handleSplit(); // 处理分页
		try {
			Map<String, Object> map = ServiceBackFactory.getIWorkServiceBackInstance().list(super.getCurrentPage(), super.getLineSize(), super.getColumn(), super.getKeyWord());
			super.request.setAttribute("url", "/pages/back/admin/employee/WorkServletBack/list");
			super.request.setAttribute("allRecorders", map.get("workCount"));
			super.request.setAttribute("allWorks", map.get("allWorks"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "back.work.list.page";
	}
	

	@Override
	public String getTitle() {
		return "职位";
	}

	@Override
	public String getUploadDirectory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getColumnData() {
		return "新部门:newdname|旧部门:olddname|新职位:newjob|旧职位:oldjob|管理员:aid|雇员编号:eid";
	}

	@Override
	public String getDefaultColumn() {
		return "eid";
	}

}
