package cn.mldn.hr.servlet.back;

import java.util.Map;
import java.util.Set;

import javax.servlet.annotation.WebServlet;

import cn.mldn.hr.factory.ServiceBackFactory;
import cn.mldn.hr.vo.Action;
import cn.mldn.util.SplitIdUtil;
import cn.mldn.util.servlet.DispatcherServlet;
@WebServlet(urlPatterns="/pages/back/admin/action/ActionServletBack/*")
public class ActionServletBack extends DispatcherServlet {
	private final String insertValidate = "action.groups.gid|action.title|action.url";
	private final String updateValidate = "action.actid|action.groups.gid|action.title|action.url";
	
	private Action action = new Action();
	public Action getAction() {
		return action;
	}
	
	public String insertPre() {
		try {
			Map<String, Object> map = ServiceBackFactory.getIActionServiceBackInstance().inserPre();
			super.request.setAttribute("allGroups", map.get("allGroups"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "back.action.insert.page";
	}
	
	public String insert() {
		try {
			if (ServiceBackFactory.getIActionServiceBackInstance().insert(this.action)) {
				super.setMsgAndUrl("insert.success.msg", "back.action.insert.servlet");
			} else {
				super.setMsgAndUrl("insert.failure.msg", "back.action.insert.servlet");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "forward.page";
	}
	
	public String update() {
		try {
			if (ServiceBackFactory.getIActionServiceBackInstance().update(this.action)) {
				super.setMsgAndUrl("update.success.msg", "back.action.list.servlet");
			} else {
				super.setMsgAndUrl("update.failure.msg", "back.action.list.servlet");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "forward.page";
	}
	
	public String delete() {
		Set<Integer> ids = SplitIdUtil.splitInteger(request.getParameter("ids"));
		try {
			if (ServiceBackFactory.getIActionServiceBackInstance().delete(ids)) {
				super.setMsgAndUrl("delete.success.msg", "back.action.list.servlet");
			} else {
				super.setMsgAndUrl("delete.failure.msg", "back.action.list.servlet");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "forward.page";
	}
	
	public String list() {
		try {
			Map<String, Object> map = ServiceBackFactory.getIActionServiceBackInstance().list();
			super.request.setAttribute("allGroups", map.get("allGroups"));
			super.request.setAttribute("allActions", map.get("allActions"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "back.action.list.page";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public String getTitle() {
		return "权限";
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
