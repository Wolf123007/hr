package cn.mldn.hr.servlet.back;

import java.util.Map;

import javax.servlet.annotation.WebServlet;

import cn.mldn.hr.factory.ServiceBackFactory;
import cn.mldn.hr.vo.Course;
import cn.mldn.util.servlet.DispatcherServlet;

@SuppressWarnings("serial")
@WebServlet(name="DetailsServletBack", urlPatterns="/pages/back/admin/course/DetailsServletBack/*")
public class DetailsServletBack extends DispatcherServlet {
	private final String listValidate = "course.cid";
	
	private Course course = new Course();
	public Course getCourse() {
		return course;
	}
	
	public String list() {
		super.handleSplit(); // 处理分页
		try {
			Map<String, Object> map = ServiceBackFactory.getIDetailsServiceBackInstance().list(this.course.getCid(), super.getCurrentPage(), super.getLineSize(), super.getColumn(), super.getKeyWord());
			super.request.setAttribute("url", "/pages/back/admin/course/DetailsServletBack/list");
			super.request.setAttribute("allRecorders", map.get("detailsCount"));
			super.request.setAttribute("allDetails", map.get("allDetails"));
			super.request.setAttribute("course", map.get("course"));
			super.request.setAttribute("paramName", "course.cid");
			super.request.setAttribute("paramValue", String.valueOf(this.course.getCid()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "back.details.list.page";
	}
	
	
	@Override
	public String getTitle() {
		return "课程详情";
	}

	@Override
	public String getUploadDirectory() {
		return "/upload/course/";
	}

	@Override
	public String getColumnData() {
		return "雇员姓名:ename|雇员编号:eid";
	}

	@Override
	public String getDefaultColumn() {
		return "ename";
	}
	





}
