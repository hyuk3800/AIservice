package com.project.biz.user;

public class userVO {
	private String ID;
	private String Password;
	private String UserName;
	
	
	public String getID() {
		return ID;
	}
	public userVO setID(String iD) {
		ID = iD;
		return this;
	}
	public String getPassword() {
		return Password;
	}
	public userVO setPassword(String password) {
		Password = password;
		return this;
	}
	public String getUserName() {
		return UserName;
	}
	public userVO setUserName(String userName) {
		UserName = userName;
		return this;
	}
	
	@Override
	public String toString() {
		return "userVO [ID=" + ID + ", Password=" + Password + ", UserName=" + UserName + "]";
	}
}
