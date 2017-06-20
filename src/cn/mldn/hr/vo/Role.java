package cn.mldn.hr.vo;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class Role implements Serializable {
	private Integer rid;
	private String title;
	private String note;
	private List<Admin> admin;
	private List<Groups> groups;
	public Integer getRid() {
		return rid;
	}
	public void setRid(Integer rid) {
		this.rid = rid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public List<Admin> getAdmin() {
		return admin;
	}
	public void setAdmin(List<Admin> admin) {
		this.admin = admin;
	}
	public List<Groups> getGroups() {
		return groups;
	}
	public void setGroups(List<Groups> groups) {
		this.groups = groups;
	}
	
	
}
