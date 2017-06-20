package cn.mldn.hr.servlet.front;

import javax.servlet.annotation.WebServlet;

import cn.mldn.hr.factory.ServiceFrontFactory;
import cn.mldn.hr.vo.Details;
import cn.mldn.hr.vo.Log;
import cn.mldn.util.servlet.DispatcherServlet;

@SuppressWarnings("serial")
@WebServlet(name="LogServletFront", urlPatterns="/pages/front/course/LogServletFront/*")
public class LogServletFront extends DispatcherServlet {
	private final String listValidate = "details.dtid";
	private final String updateStatusValidate = "details.dtid|log.lid|log.status";
	
	private Details details = new Details();
	private Log log = new Log();
	
	public Details getDetails() {
		return details;
	}
	public Log getLog() {
		return log;
	}
	
	public String updateStatus() {
		try {
			if (ServiceFrontFactory.getILogServiceFrontInstance().updateStatus(this.log)) {
				super.setMsgAndUrl("update.success.msg", "front.log.list.servlet");
			} else {
				super.setMsgAndUrl("update.failure.msg", "front.log.list.servlet");
			}
			super.request.setAttribute("paramName", "details.dtid");
			super.request.setAttribute("paramValue", this.details.getDtid());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "forward.page";
	}
	
	public String list() {
		super.handleSplit(); // 处理分页
		try {
			super.request.setAttribute("allLogs", ServiceFrontFactory.getILogServiceFrontInstance().list(this.details.getDtid()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "front.log.list.page";
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
