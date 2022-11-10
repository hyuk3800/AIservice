package com.project.biz.user.chatroom;

import java.util.List;

import com.project.member.MemberVo;

public class testJson {
	private MemberVo user;
	private String web;
	private int chatRoom;
	private List<chatVO> chatData;
	private List<chatVO> dummyList;
	private List<chatVO> HairCatList;
	
	
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
	public List<chatVO> getDummyList() {
		return dummyList;
	}
	public void setDummyList(List<chatVO> dummyList) {
		this.dummyList = dummyList;
	}
	public List<chatVO> getHairCatList() {
		return HairCatList;
	}
	public void setHairCatList(List<chatVO> hairCatList) {
		HairCatList = hairCatList;
	}

	
	
	
}
