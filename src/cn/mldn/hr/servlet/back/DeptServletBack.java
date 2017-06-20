package cn.mldn.hr.servlet.back;

import java.util.Set;

import javax.servlet.annotation.WebServlet;

import cn.mldn.hr.factory.ServiceBackFactory;
import cn.mldn.hr.vo.Dept;
import cn.mldn.util.SplitIdUtil;
import cn.mldn.util.servlet.DispatcherServlet;
@WebServlet(urlPatterns="/pages/back/admin/dept/DeptServletBack/*")
public class DeptServletBack extends DispatcherServlet {
	private final String insertValidate = "dept.dname";
	private final String updateValidate = "dept.did|dept.dname";
	
	private Dept dept = new Dept();
	public Dept getDept() {
		return this.dept;
	}
	
	public String insertPre() {
		return "back.dept.insert.page";
	}
	
	public String insert() {
		try {
			if (ServiceBackFactory.getIDeptServiceBackInstance().insert(this.dept)) {
				super.setMsgAndUrl("insert.success.msg", "back.dept.insert.servlet");
			} else {
				super.setMsgAndUrl("insert.failure.msg", "back.dept.insert.servlet");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "forward.page";
	}
	
	public String update() {
		try {
			if (ServiceBackFactory.getIDeptServiceBackInstance().update(this.dept)) {
				super.setMsgAndUrl("update.success.msg", "back.dept.list.servlet");
			} else {
				super.setMsgAndUrl("update.failure.msg", "back.dept.list.servlet");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "forward.page";
	}
	
	public String delete() {
		Set<Integer> ids = SplitIdUtil.splitInteger(request.getParameter("ids"));
		try {
			if (ServiceBackFactory.getIDeptServiceBackInstance().delete(ids)) {
				super.setMsgAndUrl("delete.success.msg", "back.dept.list.servlet");
			} else {
				super.setMsgAndUrl("delete.failure.msg", "back.dept.list.servlet");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "forward.page";
	}
	
	public String list() {
		try {
			request.setAttribute("allDepts", ServiceBackFactory.getIDeptServiceBackInstance().list());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "back.dept.list.page";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public String getTitle() {
		return "部门";
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
