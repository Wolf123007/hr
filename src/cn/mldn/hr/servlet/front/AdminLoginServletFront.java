package cn.mldn.hr.servlet.front;

import java.util.Map;

import javax.servlet.annotation.WebServlet;

import cn.mldn.hr.factory.ServiceFrontFactory;
import cn.mldn.hr.vo.Admin;
import cn.mldn.util.MD5Code;
import cn.mldn.util.servlet.DispatcherServlet;

@SuppressWarnings("serial")
@WebServlet(name="AdminLoginServletFront", urlPatterns="/AdminLogin/*")
public class AdminLoginServletFront extends DispatcherServlet {
	private final String loginValidate = "admin.aid|admin.password";

	private Admin admin = new Admin();
	public Admin getAdmin() {
		return admin;
	}

	public String login() {
		if (super.checkCode()) { // 先进行验证码的检查
			// 对登录密码进行MD5的加密处理
			this.admin.setPassword(new MD5Code().getMD5ofStr(this.admin.getPassword()));
			this.admin.setIp(super.request.getRemoteAddr());
			// 此时用户名和密码已经设置在了Admin对象里面，所以可以直接进行登录验证
			Map<String, Object> map;
			try {
				map = ServiceFrontFactory.getIAdminServiceFrontInstance().login(this.admin);
				boolean flag = (Boolean) map.get("flag");
				if (flag) { // 用户名密码正确，表示登录成功
					Admin vo = (Admin) map.get("admin");
					vo.setIp(super.request.getRemoteAddr());
					if (vo.getType() == 1) { // 后台管理员登录成功
						super.setMsgAndUrl("admin.login.success", "back.admin.index");
						request.getSession().setAttribute("badmin", vo);
					} else { // 前台管理员登录成功
						super.setMsgAndUrl("admin.login.success", "front.admin.index");
						request.getSession().setAttribute("fadmin", vo);
					}
				} else { // 登录失败
					super.setMsgAndUrl("admin.login.failure", "login.page");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			super.setMsgAndUrl("admin.code.error", "login.page");
		}
		return "forward.page";
	}


	public String updatePassword() {
		Admin admin = null;
		admin = (Admin) super.request.getSession().getAttribute("fadmin");
		if (admin == null) {
			admin = (Admin) super.request.getSession().getAttribute("badmin");
		}
		String oldpass = super.request.getParameter("oldpass");
		String newpass = super.request.getParameter("newpass");
		if (oldpass != null && newpass != null) {
			oldpass = new MD5Code().getMD5ofStr(oldpass);
			newpass = new MD5Code().getMD5ofStr(newpass);
			try {
				// ???
				if (ServiceFrontFactory.getIAdminServiceFrontInstance().updatePassword(admin.getAid(), oldpass, newpass)) {
					super.setMsgAndUrl("admin.update.password.success", "admin.logout.page");
				} else {
					super.setMsgAndUrl("admin.update.password.failure", "admin.logout.page");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "forward.page";
	}

	
	public String logout() {
		super.request.getSession().invalidate();
		super.setMsgAndUrl("admin.logout.msg", "login.page");
		return "forward.page";
	}





	@Override
	public String getTitle() {
		return null;
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
