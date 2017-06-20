package cn.mldn.hr.servlet.front;

import java.util.Map;

import javax.servlet.annotation.WebServlet;

import cn.mldn.hr.factory.ServiceFrontFactory;
import cn.mldn.hr.vo.Course;
import cn.mldn.hr.vo.Details;
import cn.mldn.util.servlet.DispatcherServlet;

@SuppressWarnings("serial")
@WebServlet(name="DetailsServletFront", urlPatterns="/pages/front/course/DetailsServletFront/*")
public class DetailsServletFront extends DispatcherServlet {
	private final String listValidate = "course.cid";
	private final String updateScoreValidate = "course.cid|details.dtid|details.score";
	
	private Course course = new Course();
	private Details details = new Details();
	
	public Course getCourse() {
		return course;
	}
	public Details getDetails() {
		return details;
	}
	
	public String updateScore() {
		try {
			if (ServiceFrontFactory.getIDetailsServiceFrontInstance().updateScore(this.details)) {
				super.setMsgAndUrl("update.success.msg", "front.details.list.servlet");
			} else {
				super.setMsgAndUrl("update.failure.msg", "front.details.list.servlet");
			}
			super.request.setAttribute("paramName", "course.cid");
			super.request.setAttribute("paramValue", this.course.getCid());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "forward.page";
	}
	
	
	public String list() {
		super.handleSplit(); // 处理分页
		try {
			Map<String, Object> map = ServiceFrontFactory.getIDetailsServiceFrontInstance().list(this.course.getCid(), super.getCurrentPage(), super.getLineSize(), super.getColumn(), super.getKeyWord());
			super.request.setAttribute("url", "/pages/front/course/DetailsServletFront/list");
			super.request.setAttribute("allRecorders", map.get("detailsCount"));
			super.request.setAttribute("allDetails", map.get("allDetails"));
			super.request.setAttribute("course", map.get("course"));
			super.request.setAttribute("paramName", "course.cid");
			super.request.setAttribute("paramValue", String.valueOf(this.course.getCid()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "front.details.list.page";
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
