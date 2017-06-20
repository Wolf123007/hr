package cn.mldn.hr.servlet.front;

import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.annotation.WebServlet;

import cn.mldn.hr.factory.ServiceFrontFactory;
import cn.mldn.hr.vo.Admin;
import cn.mldn.hr.vo.Employee;
import cn.mldn.hr.vo.Salary;
import cn.mldn.hr.vo.Work;
import cn.mldn.util.servlet.DispatcherServlet;

@SuppressWarnings("serial")
@WebServlet(name="EmployeeServletFront", urlPatterns="/pages/front/employee/EmployeeServletFront/*")
public class EmployeeServletFront extends DispatcherServlet {
	private final String insertValidate = "employee.eid|employee.dept.did|employee.level.levid|employee.jobs.jid|employee.ename|employee.birthday|employee.sex|employee.idcard|employee.school|employee.profession|employee.grad|employee.status|employee.sal|employee.note|employee.edu";
	private final String updateValidate = "employee.eid|employee.dept.did|employee.level.levid|employee.jobs.jid|employee.ename|employee.birthday|employee.sex|employee.idcard|employee.school|employee.profession|employee.grad|employee.status|employee.sal|employee.note|employee.edu|employee.photo";
	private final String updatePreValidate = "employee.eid";
	private final String updateSalPreValidate = "employee.eid";
	private final String updateSalValidate = "employee.eid|employee.level.levid|employee.sal|salary.reason|salary.note";
	private final String updateDeptPreValidate = "employee.eid";
	private final String updateDeptValidate = "employee.eid|employee.dept.did|employee.jobs.jid|work.reason|work.note";
	
	
	private Employee employee = new Employee();
	private Salary salary = new Salary();
	private Work work = new Work();
	public Work getWork() {
		return work;
	}
	public Salary getSalary() {
		return salary;
	}
	public Employee getEmployee() {
		return employee;
	}
	
	public String updateSalPre() {
		try {
			Map<String, Object> map = ServiceFrontFactory.getIEmployeeServiceFrontInstance().updateSalPre(this.employee.getEid());
			super.request.setAttribute("employee", map.get("employee"));
			super.request.setAttribute("allLevels", map.get("allLevels"));
			if (map.get("employee") != null) { // 查找到内容了
				return "front.employee.updatesal.page";
			} else {
				super.setMsgAndUrl("front.employee.notfound.msg", "front.employee.updatesal.input.page");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "forward.page";
	}
	
	public String updateSal() {
		Admin admin = (Admin) super.request.getSession().getAttribute("fadmin");
		this.employee.getAdmin().setAid(admin.getAid());
		try {
			this.employee.setSalary(this.salary); // 配置关系
			if (ServiceFrontFactory.getIEmployeeServiceFrontInstance().updateSal(this.employee)) {
				super.setMsgAndUrl("front.employee.updatesal.success", "front.employee.updatesal.input.page");
			} else {
				super.setMsgAndUrl("front.employee.updatesal.failure", "front.employee.updatesal.input.page");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "forward.page";
	}
	
	
	public String updateDeptPre() {
		try {
			Map<String, Object> map = ServiceFrontFactory.getIEmployeeServiceFrontInstance().updateDeptAndJobPre(this.employee.getEid());
			super.request.setAttribute("employee", map.get("employee"));
			super.request.setAttribute("allDepts", map.get("allDepts"));
			super.request.setAttribute("allJobs", map.get("allJobs"));
			if (map.get("employee") != null) { // 查找到内容了
				return "front.employee.updatedept.page";
			} else {
				super.setMsgAndUrl("front.employee.notfound.msg", "front.employee.updatedept.input.page");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "forward.page";
	}
	
	public String updateDept() {
		Admin admin = (Admin) super.request.getSession().getAttribute("fadmin");
		this.employee.getAdmin().setAid(admin.getAid());
		try {
			this.employee.setWork(this.work); // 配置关系
			if (ServiceFrontFactory.getIEmployeeServiceFrontInstance().updateDeptAndJob(employee)) {
				super.setMsgAndUrl("front.employee.updatedept.success", "front.employee.updatedept.input.page");
			} else {
				super.setMsgAndUrl("front.employee.updatedept.failure", "front.employee.updatedept.input.page");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "forward.page";
	}
	
	
	
	public String insertPre() {
		try {
			Map<String, Object> map = ServiceFrontFactory.getIEmployeeServiceFrontInstance().insertPre();
			request.setAttribute("allDepts", map.get("allDepts"));
			request.setAttribute("allJobs", map.get("allJobs"));
			request.setAttribute("allLevels", map.get("allLevels"));
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "front.employee.insert.page";
	}
	
	public String insert() {
		if (super.isUpload()) { // 现在有文件上传
			this.employee.setPhoto(super.createSingleFileName());
		} else {
			this.employee.setPhoto("nophoto.jpg");
		}
		this.employee.setIndate(new Date());
		Admin admin = (Admin) super.request.getSession().getAttribute("fadmin");
		this.employee.getAdmin().setAid(admin.getAid()); // 设置操作的管理员
		try {
			if (ServiceFrontFactory.getIEmployeeServiceFrontInstance().insert(this.employee)) {
				if (super.isUpload()) {
					super.upload(this.employee.getPhoto());
				}
				super.setMsgAndUrl("insert.success.msg", "front.employee.insert.servlet");
			} else {
				super.setMsgAndUrl("insert.failure.msg", "front.employee.insert.servlet");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "forward.page";
	}
	
	public String updatePre() {
		try {
			String refer = super.request.getHeader("referer");
			String backUrl = "/pages/front/employee/EmployeeServletFront/" + refer.substring(refer.lastIndexOf("/") + 1);
			Map<String, Object> map = ServiceFrontFactory.getIEmployeeServiceFrontInstance().updatePre(this.employee.getEid());
			request.setAttribute("allDepts", map.get("allDepts"));
			request.setAttribute("allJobs", map.get("allJobs"));
			request.setAttribute("allLevels", map.get("allLevels"));
			request.setAttribute("employee", map.get("employee"));
			request.setAttribute("backUrl", backUrl);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "front.employee.update.page";
	}
	
	public String update() {
		if (super.isUpload()) { // 现在有文件上传
			if ("nophoto.jpg".equals(this.employee.getPhoto())) {
				this.employee.setPhoto(super.createSingleFileName());
			}
		}
		// 需要取出管理员信息，但是这个时候的管理员信息是为Salary与Work服务的
		Admin admin = (Admin) super.request.getSession().getAttribute("fadmin");
		this.employee.getAdmin().setAid(admin.getAid()); // 设置操作的管理员
		try {
			if (ServiceFrontFactory.getIEmployeeServiceFrontInstance().update(this.employee)) {
				if (super.isUpload()) {
					super.upload(this.employee.getPhoto());
				}
				super.setMsgAndUrl("update.success.msg", "front.employee.list.servlet");
			} else {
				super.setMsgAndUrl("update.failure.msg", "front.employee.list.servlet");
			}
			super.request.setAttribute("url", super.getSmart().getRequest().getParameter("backUrl"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "forward.page";
	}
	
	public String updateStatus() {
		String refer = super.request.getHeader("referer");
		String backUrl = "/pages/front/employee/EmployeeServletFront/" + refer.substring(refer.lastIndexOf("/") + 1);
		String ids = super.request.getParameter("ids");
		String result [] = ids.split("\\|");
		Set<Integer> all = new HashSet<Integer>();
		for (int i = 0; i < result.length; i++) {
			all.add(Integer.parseInt(result[i]));
		}
		try {
			if (ServiceFrontFactory.getIEmployeeServiceFrontInstance().updateOut(all)) {
				super.setMsgAndUrl("front.emp.out.success", "front.employee.list.servlet");
			} else {
				super.setMsgAndUrl("front.emp.out.failure", "front.employee.list.servlet");
			}
			super.request.setAttribute("url", backUrl);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "forward.page";
	}
	
	public String list() {
		super.handleSplit(); // 处理分页
		try {
			Map<String, Object> map = ServiceFrontFactory.getIEmployeeServiceFrontInstance().list(super.getCurrentPage(), super.getLineSize(), super.getColumn(), super.getKeyWord());
			super.request.setAttribute("url", "/pages/front/employee/EmployeeServletFront/list");
			super.request.setAttribute("allRecorders", map.get("employeeCount"));
			super.request.setAttribute("allEmployees", map.get("allEmployees"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "front.employee.list.page";
	}
	
	public String listStatus() {
		super.handleSplit(); // 处理分页
		try {
			int status = Integer.parseInt(request.getParameter("status"));
			Map<String, Object> map = ServiceFrontFactory.getIEmployeeServiceFrontInstance().listByStatus(super.getCurrentPage(), super.getLineSize(), super.getColumn(), super.getKeyWord(), status);
			super.request.setAttribute("url", "/pages/front/employee/EmployeeServletFront/listStatus?status=" + status);
			super.request.setAttribute("allRecorders", map.get("employeeCount"));
			super.request.setAttribute("allEmployees", map.get("allEmployees"));
			super.request.setAttribute("paramName", "status");
			super.request.setAttribute("paramValue", String.valueOf(status));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "front.employee.list.page";
	}
	
	@Override
	public String getTitle() {
		return "雇员";
	}

	@Override
	public String getUploadDirectory() {
		return "/upload/employee/";
	}

	@Override
	public String getColumnData() {
		return "姓名:ename|部门名称:dname|职位名称:job|毕业院校:school|雇员学历:edu|雇员专业:profession";
	}

	@Override
	public String getDefaultColumn() {
		return "ename";
	}
	





}
