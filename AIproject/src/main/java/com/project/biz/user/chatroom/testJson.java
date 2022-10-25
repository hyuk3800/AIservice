package com.project.biz.user.chatroom;

import java.util.List;

import com.project.member.MemberVo;

public class testJson {
	private MemberVo user;
	private String web;
	private int chatRoom;
	private List<chatVO> chatData;
	
	
	public MemberVo getUser() {
		return user;
	}
	public void setUser(MemberVo user) {
		this.user = user;
	}
	public String getWeb() {
		return web;
	}
	public void setWeb(String web) {
		this.web = web;
	}
	public int getChatRoom() {
		return chatRoom;
	}
	public void setChatRoom(int chatRoom) {
		this.chatRoom = chatRoom;
	}
	public List<chatVO> getChatData() {
		return chatData;
	}
	public void setChatData(List<chatVO> chatData) {
		this.chatData = chatData;
	}
	
	
	
	
}
