package cn.mldn.hr.servlet.back;

import javax.servlet.annotation.WebServlet;

import cn.mldn.hr.factory.ServiceBackFactory;
import cn.mldn.hr.vo.Details;
import cn.mldn.util.servlet.DispatcherServlet;

@SuppressWarnings("serial")
@WebServlet(name="LogServletBack", urlPatterns="/pages/back/admin/course/LogServletBack/*")
public class LogServletBack extends DispatcherServlet {
	private final String listValidate = "details.dtid";
	
	private Details details = new Details();
	public Details getDetails() {
		return details;
	}
	
	
	public String list() {
		super.handleSplit(); // 处理分页
		try {
			super.request.setAttribute("allLogs", ServiceBackFactory.getILogServiceBackInstance().list(this.details.getDtid()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "back.log.list.page";
	}
	
	
	@Override
	public String getTitle() {
		return "课程考勤记录";
	}

	@Override
	public String getUploadDirectory() {
		return null;
	}

	@Override
	public String getColumnData() {
		return null;
	}

	@Override
	public String getDefaultColumn() {
		return null;
	}
	





}
