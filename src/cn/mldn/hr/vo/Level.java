package cn.mldn.hr.vo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Level implements Serializable {
	private Integer levid;
	private String title;
	private Double losal;
	private Double hisal;
	
	
	public Integer getLevid() {
		return levid;
	}
	public void setLevid(Integer levid) {
		this.levid = levid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Double getLosal() {
		return losal;
	}
	public void setLosal(Double losal) {
		this.losal = losal;
	}
	public Double getHisal() {
		return hisal;
	}
	public void setHisal(Double hisal) {
		this.hisal = hisal;
	}
	
	
}
