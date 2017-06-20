package cn.mldn.hr.servlet.front;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.annotation.WebServlet;

import cn.mldn.hr.factory.ServiceFrontFactory;
import cn.mldn.hr.vo.Admin;
import cn.mldn.hr.vo.Course;
import cn.mldn.util.servlet.DispatcherServlet;

@SuppressWarnings("serial")
@WebServlet(name="CourseServletFront", urlPatterns="/pages/front/course/CourseServletFront/*")
public class CourseServletFront extends DispatcherServlet {
	private final String insertValidate = "course.cname|course.total|course.begin|course.end|course.status|course.note";
	private final String updateValidate = "course.cid|course.cname|course.total|course.begin|course.end|course.status|course.note";
	private final String updatePreValidate = "course.cid";
	
	private Course course = new Course();
	public Course getCourse() {
		return course;
	}
	
	public String insertPre() {
		try {
			super.request.setAttribute("allDepts", ServiceFrontFactory.getICourseServiceFrontInstance().insertPre());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "front.course.insert.page";
	}
	
	public String insert() {
		Admin admin = (Admin) super.request.getSession().getAttribute("fadmin");
		this.course.getAdmin().setAid(admin.getAid());
		if (super.isUpload()) { // 现在有文件上传
			this.course.setPhoto(super.createSingleFileName());
		} else {
			this.course.setPhoto("nophoto.jpg");
		}
		// 处理所有的部门编号问题
		String did [] = super.getParameterValues("did");
		if (did != null) { // 部门必须存在
			Set<Integer> dids = new HashSet<Integer>();
			for (int i = 0; i < did.length; i++) {
				dids.add(Integer.parseInt(did[i]));
			}
			try {
				if (ServiceFrontFactory.getICourseServiceFrontInstance().insert(this.course, dids)) {
					if (super.isUpload()) {
						super.upload(this.course.getPhoto());
					}
					super.setMsgAndUrl("insert.success.msg", "front.course.insert.servlet");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			super.setMsgAndUrl("insert.failure.msg", "front.course.insert.servlet");
		}
		return "forward.page";
	}
	
	public String updatePre() {
		try {
			request.setAttribute("course", ServiceFrontFactory.getICourseServiceFrontInstance().updatePre(this.course.getCid()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "front.course.update.page";
	}
	
	public String update() {
		if (super.isUpload()) { // 有文件上传
			if ("nophoto".equals(this.course.getPhoto())) {
				this.course.setPhoto(super.createSingleFileName());
			}
		}
		Admin admin = (Admin) super.request.getSession().getAttribute("fadmin");
		this.course.getAdmin().setAid(admin.getAid());
		try {
			if (ServiceFrontFactory.getICourseServiceFrontInstance().update(this.course)) {
				if (super.isUpload()) {
					super.upload(this.course.getPhoto());
				}
				super.setMsgAndUrl("update.success.msg", "front.course.list.servlet");
			} else {
				super.setMsgAndUrl("update.failure.msg", "front.course.list.servlet");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "forward.page";
	}
	
	public String list() {
		super.handleSplit(); // 处理分页
		try {
			Map<String, Object> map = ServiceFrontFactory.getICourseServiceFrontInstance().list(super.getCurrentPage(), super.getLineSize(), super.getColumn(), super.getKeyWord());
			super.request.setAttribute("url", "/pages/front/course/CourseServletFront/list");
			super.request.setAttribute("allRecorders", map.get("courseCount"));
			super.request.setAttribute("allCourses", map.get("allCourses"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "front.course.list.page";
	}
	
	
	@Override
	public String getTitle() {
		return "课程";
	}

	@Override
	public String getUploadDirectory() {
		return "/upload/course/";
	}

	@Override
	public String getColumnData() {
		return "课程名称:cname";
	}

	@Override
	public String getDefaultColumn() {
		return "cname";
	}
	





}
