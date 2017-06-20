package cn.mldn.hr.vo;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class Salary implements Serializable {
	private Integer sid;
	private Level oldlevel = new Level();
	private Level newlevel = new Level();
	private Employee employee = new Employee();
	private Admin admin = new Admin();
	private Date cdate;
	private Double oldsal;
	private Double newsal;
	private String reason;
	private String note;
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public Level getOldlevel() {
		return oldlevel;
	}
	public void setOldlevel(Level oldLevel) {
		this.oldlevel = oldLevel;
	}
	public Level getNewlevel() {
		return newlevel;
	}
	public void setNewlevel(Level newLevel) {
		this.newlevel = newLevel;
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
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	public Date getCdate() {
		return cdate;
	}
	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}
	public Double getOldsal() {
		return oldsal;
	}
	public void setOldsal(Double oldsal) {
		this.oldsal = oldsal;
	}
	public Double getNewsal() {
		return newsal;
	}
	public void setNewsal(Double newsal) {
		this.newsal = newsal;
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
