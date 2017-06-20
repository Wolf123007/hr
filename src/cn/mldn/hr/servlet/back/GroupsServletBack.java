package cn.mldn.hr.servlet.back;

import java.util.Set;

import javax.servlet.annotation.WebServlet;

import cn.mldn.hr.factory.ServiceBackFactory;
import cn.mldn.hr.vo.Groups;
import cn.mldn.util.SplitIdUtil;
import cn.mldn.util.servlet.DispatcherServlet;
@WebServlet(urlPatterns="/pages/back/admin/groups/GroupsServletBack/*")
public class GroupsServletBack extends DispatcherServlet {
	private final String insertValidate = "groups.title";
	private final String updateValidate = "groups.gid|groups.title";
	
	private Groups groups = new Groups();
	public Groups getGroups() {
		return groups;
	}
	
	public String insertPre() {
		return "back.groups.insert.page";
	}
	
	public String insert() {
		try {
			if (ServiceBackFactory.getIGroupsServiceBackInstance().insert(this.groups)) {
				super.setMsgAndUrl("insert.success.msg", "back.groups.insert.servlet");
			} else {
				super.setMsgAndUrl("insert.failure.msg", "back.groups.insert.servlet");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "forward.page";
	}
	
	public String update() {
		try {
			if (ServiceBackFactory.getIGroupsServiceBackInstance().update(this.groups)) {
				super.setMsgAndUrl("update.success.msg", "back.groups.list.servlet");
			} else {
				super.setMsgAndUrl("update.failure.msg", "back.groups.list.servlet");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "forward.page";
	}
	
	public String delete() {
		Set<Integer> ids = SplitIdUtil.splitInteger(request.getParameter("ids"));
		try {
			if (ServiceBackFactory.getIGroupsServiceBackInstance().delete(ids)) {
				super.setMsgAndUrl("delete.success.msg", "back.groups.list.servlet");
			} else {
				super.setMsgAndUrl("delete.failure.msg", "back.groups.list.servlet");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "forward.page";
	}
	
	public String list() {
		try {
			request.setAttribute("allGroups", ServiceBackFactory.getIGroupsServiceBackInstance().list());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "back.groups.list.page";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public String getTitle() {
		return "权限组";
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
