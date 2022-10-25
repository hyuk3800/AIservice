package com.project.biz.user.chatroom;

import java.util.Date;

public class chatroomVO {
	private int chatroomnum;
	private String user_id;
	private Date chatroomcre_data;
	
	public int getChatroomnum() {
		return chatroomnum;
	}
	public chatroomVO setChatroomnum(int chatroomnum) {
		this.chatroomnum = chatroomnum;
		return this;
	}
	public String getUser_id() {
		return user_id;
	}
	public chatroomVO setUser_id(String user_id) {
		this.user_id = user_id;
		return this;
	}
	public Date getChatroomcre_data() {
		return chatroomcre_data;
	}
	public chatroomVO setChatroomcre_data(Date chatroomcre_data) {
		this.chatroomcre_data = chatroomcre_data;
		return this;
	}
}
