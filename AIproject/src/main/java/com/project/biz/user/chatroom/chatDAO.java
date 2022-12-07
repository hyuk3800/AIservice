package com.project.biz.user.chatroom;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.project.biz.JDBCUtill;
import com.project.member.MemberVo;

@Repository("chatDAO")
public class chatDAO {
	
	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	
	private String SelectChatRoom = "SELECT * FROM aiservice.chatroom "
								+ "WHERE user_id=?;";
	
	private String insertChatRoom = "INSERT INTO aiservice.chatroom (user_id, chatroomcre_date)" 
								+ "VALUES (?, NOW())";
	
	private String allchat = "SELECT chatter, chattingrecord, chatting_date, chattype FROM aiservice.chatterchatting " 
								+ "WHERE chatroomnum=? "
								+ "UNION "  
								+ "SELECT chatter, chattingrecord, chatting_date, chattype FROM aiservice.userchatting " 
								+ "WHERE chatroomnum=? "
								+ "ORDER BY chatting_date ASC;";
	
	
	// private String SelectUser = "SELECT * FROM aiservice.userchatting "
	//	 	 					+ "WHERE chatroomnum=?;";
	
	private String insertUserChat = "INSERT aiservice.userchatting (chatter, chattingrecord, chatroomnum, chatting_date, chattype) " 
								+ "VALUES (?, ?, ?, NOW(), ?);";
	
	// private String SelectChatter = "SELECT * FROM aiservice.chatterchatting " 
	// 		 					+ "WHERE chatroomnum=?;";
	
	private String insertChatterChat = "INSERT aiservice.chatterchatting (chatter, chattingrecord, chatroomnum, chatting_date, chattype) " 
								+ "VALUES ('AI', ?, ?, NOW(), ?);";
	
	public int searchChatRoom(MemberVo vo) {
		System.out.println("select ChatRoom");
		conn = JDBCUtill.getConn();
		int chatroom = 0;
		try {
			stmt = conn.prepareStatement(SelectChatRoom);
			stmt.setString(1, vo.getID());
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				chatroom = rs.getInt("chatroomnum");
				
				return chatroom;
			}else {
				int addroom = insertChatRoom(vo);
				
				if (addroom != 0) {
					return searchChatRoom(vo);
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
		return searchChatRoom(vo);
	}
	
	public int insertChatRoom(MemberVo vo) {
		System.out.println("insert ChatRoom");
		conn = JDBCUtill.getConn();
		int row = 0;
		
		try {
			stmt = conn.prepareStatement(insertChatRoom);
			stmt.setString(1, vo.getID());
			row = stmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return row;
	}
	
	public List<chatVO> searchAllChat(testJson json) {
		System.out.println("search UserChat");
		int chatroom = json.getChatRoom();
		conn = JDBCUtill.getConn();
		List<chatVO> chatList = new ArrayList<chatVO>();
		// chatList = []
		try {
			stmt = conn.prepareStatement(allchat);
			stmt.setInt(1, chatroom);
			stmt.setInt(2, chatroom);
			rs = stmt.executeQuery();

			while(rs.next()){
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
				System.out.println(chatRS);
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
	
	public int insertUserChat(chatVO vo) {
		System.out.println("insert UserChat");
		String user = vo.getChatter();
		String chat = vo.getChatData();
		int roomNum = vo.getChatroomnum();
		int chattype = vo.getType();
		
		int row = 0;
		conn = JDBCUtill.getConn();
		try {
			stmt = conn.prepareStatement(insertUserChat);
			stmt.setString(1, user);
			stmt.setString(2, chat);
			stmt.setInt(3, roomNum);
			stmt.setInt(4, chattype);
			row = stmt.executeUpdate();
		} 
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			// TODO: handle finally clause
			JDBCUtill.close(stmt, conn);
		}
		return row;
	}
	
	
	public void searchChatterChat(testJson json) {
		System.out.println("search ChatterChat");
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
	
	public int insertChatterChat(chatVO vo) {
		System.out.println("insert ChatterChat");
		String chat = vo.getChatData();
		int chatroom = vo.getChatroomnum();
		int type = vo.getType();
		int row = 0;
		conn = JDBCUtill.getConn();
		try {
			stmt = conn.prepareStatement(insertChatterChat);
			stmt.setString(1, chat);
			stmt.setInt(2, chatroom);
			stmt.setInt(3, type);
			row = stmt.executeUpdate();
		} 
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			// TODO: handle finally clause
			JDBCUtill.close(stmt, conn);
		}
		return row;
	}
	
	
}
