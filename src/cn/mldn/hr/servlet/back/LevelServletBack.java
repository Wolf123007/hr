package cn.mldn.hr.servlet.back;

import java.util.Set;

import javax.servlet.annotation.WebServlet;

import cn.mldn.hr.factory.ServiceBackFactory;
import cn.mldn.hr.vo.Level;
import cn.mldn.util.SplitIdUtil;
import cn.mldn.util.servlet.DispatcherServlet;
@WebServlet(urlPatterns="/pages/back/admin/level/LevelServletBack/*")
public class LevelServletBack extends DispatcherServlet {
	private final String insertValidate = "level.title|level.losal|level.hisal";
	private final String updateValidate = "level.levid|level.title|level.losal|level.hisal";
	
	private Level level = new Level();
	public Level getLevel() {
		return level;
	}

	public String insertPre() {
		return "back.level.insert.page";
	}
	
	public String insert() {
		try {
			if (ServiceBackFactory.getILevelServiceBackInstance().insert(this.level)) {
				super.setMsgAndUrl("insert.success.msg", "back.level.insert.servlet");
			} else {
				super.setMsgAndUrl("insert.failure.msg", "back.level.insert.servlet");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "forward.page";
	}
	
	public String update() {
		try {
			if (ServiceBackFactory.getILevelServiceBackInstance().update(this.level)) {
				super.setMsgAndUrl("update.success.msg", "back.level.list.servlet");
			} else {
				super.setMsgAndUrl("update.failure.msg", "back.level.list.servlet");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "forward.page";
	}
	
	public String delete() {
		Set<Integer> ids = SplitIdUtil.splitInteger(request.getParameter("ids"));
		try {
			if (ServiceBackFactory.getILevelServiceBackInstance().delete(ids)) {
				super.setMsgAndUrl("delete.success.msg", "back.level.list.servlet");
			} else {
				super.setMsgAndUrl("delete.failure.msg", "back.level.list.servlet");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "forward.page";
	}
	
	public String list() {
		try {
			request.setAttribute("allLevels", ServiceBackFactory.getILevelServiceBackInstance().list());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "back.level.list.page";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public String getTitle() {
		return "职位等级";
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
