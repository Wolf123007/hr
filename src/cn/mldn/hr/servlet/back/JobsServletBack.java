package cn.mldn.hr.servlet.back;

import java.util.Set;

import javax.servlet.annotation.WebServlet;

import cn.mldn.hr.factory.ServiceBackFactory;
import cn.mldn.hr.vo.Dept;
import cn.mldn.hr.vo.Jobs;
import cn.mldn.util.SplitIdUtil;
import cn.mldn.util.servlet.DispatcherServlet;
@WebServlet(urlPatterns="/pages/back/admin/jobs/JobsServletBack/*")
public class JobsServletBack extends DispatcherServlet {
	private final String insertValidate = "jobs.title";
	private final String updateValidate = "jobs.jid|jobs.title";
	
	private Jobs jobs = new Jobs();
	public Jobs getJobs() {
		return jobs;
	}
	
	public String insertPre() {
		return "back.jobs.insert.page";
	}
	
	public String insert() {
		try {
			if (ServiceBackFactory.getIJobsServiceBackInstance().insert(this.jobs)) {
				super.setMsgAndUrl("insert.success.msg", "back.jobs.insert.servlet");
			} else {
				super.setMsgAndUrl("insert.failure.msg", "back.jobs.insert.servlet");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "forward.page";
	}
	
	public String update() {
		try {
			if (ServiceBackFactory.getIJobsServiceBackInstance().update(this.jobs)) {
				super.setMsgAndUrl("update.success.msg", "back.jobs.list.servlet");
			} else {
				super.setMsgAndUrl("update.failure.msg", "back.jobs.list.servlet");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "forward.page";
	}
	
	public String delete() {
		Set<Integer> ids = SplitIdUtil.splitInteger(request.getParameter("ids"));
		try {
			if (ServiceBackFactory.getIJobsServiceBackInstance().delete(ids)) {
				super.setMsgAndUrl("delete.success.msg", "back.jobs.list.servlet");
			} else {
				super.setMsgAndUrl("delete.failure.msg", "back.jobs.list.servlet");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "forward.page";
	}
	
	public String list() {
		try {
			request.setAttribute("allJobs", ServiceBackFactory.getIJobsServiceBackInstance().list());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "back.jobs.list.page";
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDefaultColumn() {
		// TODO Auto-generated method stub
		return null;
	}

}
