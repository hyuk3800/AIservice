package com.project.biz.mypage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.project.biz.JDBCUtill;
import com.project.biz.user.chatroom.chatVO;
import com.project.biz.user.chatroom.testJson;
import com.project.member.MemberVo;

@Repository("galleryDAO")
public class galleryDAO {

	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	
	private String SelectUser = "SELECT * FROM aiservice.userchatting "
	 	 					+ "WHERE chatroomnum=? AND chattype=1 "
	 	 					+ "ORDER BY chatting_date ASC;";
	
	private String SelectChatter = "SELECT * FROM aiservice.chatterchatting " 
		 					+ "WHERE chatroomnum=? AND chattype=1 "
							+ "ORDER BY chatting_date ASC;";

	
	public List<chatVO> searchChatterGallery(int chatroom) {
		System.out.println("search ChatterGallery");
		
		List<chatVO> chatList = null;

		conn = JDBCUtill.getConn();
		try {
			stmt = conn.prepareStatement(SelectChatter);
			stmt.setInt(1, chatroom);
			rs = stmt.executeQuery();
			chatList = new ArrayList<chatVO>();		
			if(!rs.next()) {
				
			}else {
				while (rs.next()) {
					String chatter = rs.getString("chatter");
					String chat= rs.getString("chattingrecord");
					Date cre_date= rs.getDate("chatting_date");
					int type = rs.getInt("chattype");
					
					chatVO chatRS = new chatVO()
							.setChatter(chatter)
							.setChatData(chat)
							.setCre_date(cre_date)
							.setType(type);
					
					chatList.add(chatRS);
					
			
				}
			}
			
		} 
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			// TODO: handle finally clause
			JDBCUtill.close(rs, stmt, conn);
		}
		
		return chatList;
	}

	public void searchUserGallery(testJson json) {
		System.out.println("search userGallery");
		int chatroom = json.getChatRoom();
		MemberVo user = json.getUser();
		conn = JDBCUtill.getConn();
		try {
			
		} 
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			// TODO: handle finally clause
			JDBCUtill.close(rs, stmt, conn);
		}
	}

}
