package com.project.biz.user.chatroom;

import java.util.Date;

public class chatVO {
	private String chatter;
	private String chatData;
	private int chatroomnum;
	private Date cre_date;
	private int type;
	
	
	public String getChatter() {
		return chatter;
	}
	public chatVO setChatter(String chatter) {
		this.chatter = chatter;
		return this;
	}
	public String getChatData() {
		return chatData;
	}
	public chatVO setChatData(String chatData) {
		this.chatData = chatData;
		return this;
	}
	public int getChatroomnum() {
		return chatroomnum;
	}
	public chatVO setChatroomnum(int chatroomnum) {
		this.chatroomnum = chatroomnum;
		return this;
	}
	public int getType() {
		return type;
	}
	public chatVO setType(int type) {
		this.type = type;
		return this;
	}
	public Date getCre_date() {
		return cre_date;
	}
	public chatVO setCre_date(Date cre_date) {
		this.cre_date = cre_date;
		return this;
	}
	
	@Override
	public String toString() {
		return "chatVO [chatter=" + chatter + ", chatData=" + chatData + ", chatroomnum=" + chatroomnum + ", cre_date="
				+ cre_date + ", type=" + type + "]";
	}
}
