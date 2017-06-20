package cn.mldn.hr.vo;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class Log implements Serializable {
	private Integer lid;
	private Details details = new Details();
	private Date recdate;
	private Integer num;
	private Integer status;
	public Integer getLid() {
		return lid;
	}
	public void setLid(Integer lid) {
		this.lid = lid;
	}
	public Details getDetails() {
		return details;
	}
	public void setDetails(Details details) {
		this.details = details;
	}
	public Date getRecdate() {
		return recdate;
	}
	public void setRecdate(Date recdate) {
		this.recdate = recdate;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
