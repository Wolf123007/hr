package cn.mldn.hr.vo;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class Work implements Serializable {
	private Integer wid;
	private Employee employee = new Employee();
	private Admin admin = new Admin();
	private Dept olddept = new Dept();
	private Dept newdept = new Dept();
	private Jobs oldjobs = new Jobs();
	private Jobs newJobs = new Jobs();
	private Date cdate;
	private String olddname;
	private String oldjob;
	private String newdname;
	private String newjob;
	private String reason;
	private String note;
	public Integer getWid() {
		return wid;
	}
	public void setWid(Integer wid) {
		this.wid = wid;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public Admin getAdmin() {
		return admin;
	}
	public void setAmdin(Admin amdin) {
		this.admin = amdin;
	}
	public Dept getOlddept() {
		return olddept;
	}
	public void setOlddept(Dept olddept) {
		this.olddept = olddept;
	}
	public Dept getNewdept() {
		return newdept;
	}
	public void setNewdept(Dept newdept) {
		this.newdept = newdept;
	}
	public Jobs getOldjobs() {
		return oldjobs;
	}
	public void setOldjobs(Jobs oldjobs) {
		this.oldjobs = oldjobs;
	}
	public Jobs getNewJobs() {
		return newJobs;
	}
	public void setNewJobs(Jobs newJobs) {
		this.newJobs = newJobs;
	}
	public Date getCdate() {
		return cdate;
	}
	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}
	public String getOlddname() {
		return olddname;
	}
	public void setOlddname(String olddname) {
		this.olddname = olddname;
	}
	public String getOldjob() {
		return oldjob;
	}
	public void setOldjob(String oldjob) {
		this.oldjob = oldjob;
	}
	public String getNewdname() {
		return newdname;
	}
	public void setNewdname(String newdname) {
		this.newdname = newdname;
	}
	public String getNewjob() {
		return newjob;
	}
	public void setNewjob(String newjob) {
		this.newjob = newjob;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
	
}
