package cn.mldn.hr.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@SuppressWarnings("serial")
public class Admin implements Serializable {
	private String aid;
	private String password;
	private Integer type;
	private Date lastdate;
	private Integer flag;
	private Role role = new Role();
	private List<Adminlogs> logs;
	private String ip; // 这个不是给Admin使用的，而是给日志记录使用的
	
	
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public List<Adminlogs> getLogs() {
		return logs;
	}
	public void setLogs(List<Adminlogs> logs) {
		this.logs = logs;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getAid() {
		return aid;
	}
	public void setAid(String aid) {
		this.aid = aid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Date getLastdate() {
		return lastdate;
	}
	public void setLastdate(Date lastdate) {
		this.lastdate = lastdate;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	
	
}
