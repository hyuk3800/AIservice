package com.project.member;

import java.util.Date;

public class MemberVo {
	private String id;
	private String nickname; 
	private String pwd;
	private Date cre_date;
	private Date mod_date;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}	
	public Date getCre_date() {
		return cre_date;
	}
	public void setCre_date(Date cre_date) {
		this.cre_date = cre_date;
	}
	public Date getMod_date() {
		return mod_date;
	}
	public void setMod_date(Date mod_date) {
		this.mod_date = mod_date;
	}
	@Override
	public String toString() {
		return "MemberVo [id=" + id + ", nickname=" + nickname + ", pwd=" + pwd + ", cre_date=" + cre_date + ", mod_date=" + mod_date + "]";
	}
}
