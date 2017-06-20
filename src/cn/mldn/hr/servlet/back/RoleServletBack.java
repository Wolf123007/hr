package cn.mldn.hr.servlet.back;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.annotation.WebServlet;

import cn.mldn.hr.factory.ServiceBackFactory;
import cn.mldn.hr.vo.Groups;
import cn.mldn.hr.vo.Role;
import cn.mldn.util.SplitIdUtil;
import cn.mldn.util.servlet.DispatcherServlet;
@WebServlet(urlPatterns="/pages/back/admin/role/RoleServletBack/*")
public class RoleServletBack extends DispatcherServlet {
	private final String insertValidate = "role.title|role.note";
	private final String updatePreValidate = "role.rid";
	private final String updateValidate = "role.rid|role.title|role.note";
	
	private Role role = new Role();
	public Role getRole() {
		return role;
	}
	
	public List<List<Groups>> handList(List<Groups> all) {
		int count = 0; // 设置保存的基数
		List<List<Groups>> allGroups = new ArrayList<List<Groups>>();
		Iterator<Groups> iter = all.iterator();
		List<Groups> temp = new ArrayList<Groups>();
		while (iter.hasNext()) {
			temp.add(iter.next());
			count ++;
			if (count == 3) { // 表示已经增加完整了
				allGroups.add(temp); // 向外部集合中增加
				temp = new ArrayList<Groups>();
				count = 0;
			}
		}
		if (count != 0 && count < 3) { // 还有数据没有保存
			allGroups.add(temp);
		}
		return allGroups;
	}
	
	public String insertPre() {
		try {
			Map<String, Object> map = ServiceBackFactory.getIRoleServiceBackInstance().insertPre();
			super.request.setAttribute("allGroups", this.handList((List<Groups>) map.get("allGroups")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "back.role.insert.page";
	}
	
	public String insert() {
		try {
			String gid [] = super.getParameterValues("gid");
			if (gid == null) { // 没有权限组被选择
				super.setMsgAndUrl("back.role.groups.failure", "back.role.insert.servlet");
			} else { // 现在有权限组，那么需要为为List<Groups>的形式保存
				List<Groups> allGroups = new ArrayList<Groups>();
				for (int i = 0; i < gid.length; i++) {
					Groups g = new Groups();
					g.setGid(Integer.parseInt(gid[i]));
					allGroups.add(g);
				}
				this.role.setGroups(allGroups); // 设置角色与权限组的关系
				if (ServiceBackFactory.getIRoleServiceBackInstance().insert(this.role)) {
					super.setMsgAndUrl("insert.success.msg", "back.role.insert.servlet");
				} else {
					super.setMsgAndUrl("insert.success.msg", "back.role.insert.servlet");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "forward.page";
	}
	
	public String updatePre() {
		try {
			Map<String, Object> map = ServiceBackFactory.getIRoleServiceBackInstance().updatePre(this.role.getRid());
			Role role = (Role) map.get("role");
			super.request.setAttribute("allGroups", this.handList((List<Groups>) map.get("allGroups")));
			super.request.setAttribute("role", role);
			// 处理角色中已有的权限组
			Map<Integer, String> result = new HashMap<Integer, String>();
			// 取出一个角色所具有的权限组
			Iterator<Groups> iter = role.getGroups().iterator();
			while (iter.hasNext()) {
				Groups gup = iter.next();
				result.put(gup.getGid(), "checked");
			}
			super.request.setAttribute("groupsResult", result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "back.role.update.page";
	}
	
	public String update() {
		try {
			String gid [] = super.getParameterValues("gid");
			if (gid == null) { // 没有权限组被选择
				super.setMsgAndUrl("back.role.groups.failure", "back.role.list.servlet");
			} else { // 现在有权限组，那么需要为为List<Groups>的形式保存
				List<Groups> allGroups = new ArrayList<Groups>();
				for (int i = 0; i < gid.length; i++) {
					Groups g = new Groups();
					g.setGid(Integer.parseInt(gid[i]));
					allGroups.add(g);
				}
				this.role.setGroups(allGroups); // 设置角色与权限组的关系
				if (ServiceBackFactory.getIRoleServiceBackInstance().update(this.role)) {
					super.setMsgAndUrl("update.success.msg", "back.role.list.servlet");
				} else {
					super.setMsgAndUrl("update.success.msg", "back.role.list.servlet");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "forward.page";
	}
	
	public String delete() {
		Set<Integer> ids = SplitIdUtil.splitInteger(super.request.getParameter("ids"));
		try {
			if (ServiceBackFactory.getIRoleServiceBackInstance().delete(ids)) {
				super.setMsgAndUrl("delete.success.msg", "back.role.list.servlet");
			} else {
				super.setMsgAndUrl("delete.failure.msg", "back.role.list.servlet");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "forward.page";
	}
	
	public String list() {
		try {
			request.setAttribute("allRoles", ServiceBackFactory.getIRoleServiceBackInstance().list());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "back.role.list.page";
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
