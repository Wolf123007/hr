package cn.mldn.hr.servlet.back;

import java.util.Map;
import java.util.Set;

import javax.servlet.annotation.WebServlet;

import cn.mldn.hr.factory.ServiceBackFactory;
import cn.mldn.hr.vo.Admin;
import cn.mldn.util.MD5Code;
import cn.mldn.util.SplitIdUtil;
import cn.mldn.util.servlet.DispatcherServlet;
@WebServlet(urlPatterns="/pages/back/admin/admin/AdminServletBack/*")
public class AdminServletBack extends DispatcherServlet {
	private final String insertValidate = "admin.aid|admin.password|admin.type";
	private final String updateValidate = "admin.aid|admin.type";
	
	private Admin admin = new Admin();
	public Admin getAdmin() {
		return admin;
	}
	
	public String insertPre() {
		try {
			Map<String, Object> map = ServiceBackFactory.getIAdminServiceBackInstance().insertPre();
			super.request.setAttribute("allRoles", map.get("allRoles"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "back.admin.insert.page";
	}
	
	public String insert() {
		try {
			// 需要对密码进行MD5加密处理
			this.admin.setPassword(new MD5Code().getMD5ofStr(this.admin.getPassword()));
			if (ServiceBackFactory.getIAdminServiceBackInstance().insert(this.admin)) {
				super.setMsgAndUrl("insert.success.msg", "back.admin.insert.servlet");
			} else {
				super.setMsgAndUrl("insert.failure.msg", "back.admin.insert.servlet");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "forward.page";
	}
	
	public String update() {
		try {
			String password = super.getParameter("admin.password");
			if (password != null && !"".equals(password) && password != this.admin.getPassword()) {
				// 需要对密码进行MD5加密处理
				this.admin.setPassword(new MD5Code().getMD5ofStr(this.admin.getPassword()));
			}
			if (ServiceBackFactory.getIAdminServiceBackInstance().update(this.admin)) {
				super.setMsgAndUrl("update.success.msg", "back.admin.list.servlet");
			} else {
				super.setMsgAndUrl("update.failure.msg", "back.admin.list.servlet");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "forward.page";
	}
	
	public String delete() {
		Set<String> ids = SplitIdUtil.splitString(request.getParameter("ids"));
		try {
			if (ServiceBackFactory.getIAdminServiceBackInstance().delete(ids)) {
				super.setMsgAndUrl("delete.success.msg", "back.admin.list.servlet");
			} else {
				super.setMsgAndUrl("delete.failure.msg", "back.admin.list.servlet");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "forward.page";
	}
	
	public String list() {
		try {
			Map<String, Object> map = ServiceBackFactory.getIAdminServiceBackInstance().list();
			super.request.setAttribute("allRoles", map.get("allRoles"));
			super.request.setAttribute("allAdmins", map.get("allAdmins"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "back.admin.list.page";
	}
	
	

	
	@Override
	public String getTitle() {
		return "管理员";
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
