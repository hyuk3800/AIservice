package com.project.biz.user.chatroom;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.project.biz.EchoClient;
import com.project.biz.InputThread;
import com.project.biz.InputThread2;
import com.project.member.MemberVo;

@Controller
public class ChatRoom {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private FileService fileservice;
	
	@RequestMapping(value = "/home.do", method = RequestMethod.GET)
	public String getChathome(HttpSession session, chatDAO chatDAO) {
		logger.info("GET_ChatRoomPage");
		MemberVo user = (MemberVo) session.getAttribute("user");
		if(user != null) {
			int chatRoom = chatDAO.searchChatRoom((MemberVo) session.getAttribute("user"));
			session.setAttribute("chatroom", chatRoom);
			System.out.println(user.toString());
		}

		return "home";			

	}
	
	@ResponseBody
	@RequestMapping(value = "/chat/json.do", method = RequestMethod.GET)
	public testJson testGetJson(HttpSession session, chatDAO chatDAO) {
		testJson json = new testJson();
		json.setWeb("home");
		if(session.getAttribute("user") != null) {
			int chatRoom = (int) session.getAttribute("chatroom");
			System.out.println(chatRoom);
			json.setChatRoom(chatRoom);
			MemberVo user = (MemberVo) session.getAttribute("user");
			System.out.println(user.toString());
			json.setUser(user);
			
			List<chatVO> chatList = chatDAO.searchAllChat(json);
			json.setChatData(chatList);
		}
		
		
		return json;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/chat/chatting.do", method = RequestMethod.POST)
	public String chattingPost(@RequestBody HashMap<String, Object> map, HttpSession session, chatDAO chatDAO) {
		logger.info("POST_Chatting");
		System.out.println(map);
		String userChat = (String) map.get("chat");
		System.out.println(userChat);
		MemberVo user = (MemberVo) session.getAttribute("user");
		if(user != null) {
			int chatRoom = (int) session.getAttribute("chatroom");
			chatVO vo = new chatVO()
							.setChatter(user.getID())
							.setChatroomnum(chatRoom)
							.setChatData(userChat)
							.setType(0);
			int row = chatDAO.insertUserChat(vo);
			System.out.println(row + " 행에 추가");
			
			String ai = "hi im Ai " + user.getID();
			
			chatVO aiVo = new chatVO()
							.setChatData(ai)
							.setChatroomnum(chatRoom)
							.setType(0);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			
			int airow = chatDAO.insertChatterChat(aiVo);
			System.out.println(airow + " 행에 AI 추가");
			
			return ai;		
		}
		else {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			return "hi im Ai null";
		}
		
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/chat/uploadFile.do", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public String publ(@RequestParam("files") MultipartFile uploadFile, HttpSession session, HttpServletRequest request, chatDAO chatDAO) throws Exception {
		logger.info("POST_File");
		String fileName = fileservice.fileUpload(uploadFile, session);
		
		System.out.println(fileName);
		
		MemberVo user = (MemberVo) session.getAttribute("user");
		if(user != null) {
			System.out.println("userTrue");
			int chatroom = (int) session.getAttribute("chatroom");
			String userID = user.getID();
			
			chatVO vo = new chatVO()
							.setChatter(userID)
							.setChatData(fileName)
							.setChatroomnum(chatroom)
							.setType(1);
			int row = chatDAO.insertUserChat(vo);
			System.out.println(row+" 행에 추가");
		}
		
		return fileName;
	}
	
	@ResponseBody
	@RequestMapping(value = "/chat/testAi.do", method = RequestMethod.POST)
	public String testAi(@RequestBody HashMap<String, Object> map, HttpSession session, chatDAO chatDao) throws InterruptedException {
		
		String result = null;
		
		String uploadDir = session.getServletContext().getRealPath("resources/AiUploadImg");
		String UserDir = session.getServletContext().getRealPath("resources/uploadImg");
		
		MemberVo user = (MemberVo) session.getAttribute("user");
		int chatroom = 0;
		if(user != null) {
			chatroom = (int) session.getAttribute("chatroom");
		
			Thread.sleep(1000);
			
			chatVO aiVo = new chatVO()
					.setChatData("잠시만 기다려주세요")
					.setChatroomnum(chatroom)
					.setType(0);
			int row1 = chatDao.insertChatterChat(aiVo);
			System.out.println(row1 + " 잠시만 기다려주세요");
		}
		
		File Folder = new File(uploadDir);
		
		if(!Folder.exists()) {
			try {
				Folder.mkdir();
				System.out.println("False");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		}else {
			System.out.println("True");
		}
		String hairStyle = (String) map.get("haristyle");
		String haircolor = (String) map.get("hairColor");
		String imgName = (String) map.get("img");
//		UserDir+"/"+imgName
//		System.out.println(hairStyle + ", " + haircolor + ", " + imgName);
	
		String ip = "192.168.0.34";
		int PORT = 9400;
		Socket clientSocket = null;
		
		EchoClient EC = new EchoClient();
		
		try {
			clientSocket = new Socket(ip, PORT);
			
			OutputStream out = clientSocket.getOutputStream();
			BufferedOutputStream bos = new BufferedOutputStream(out);
			DataOutputStream filterOut = new DataOutputStream(bos);
			
			InputStream in = clientSocket.getInputStream();
			BufferedInputStream bis = new BufferedInputStream(in);
			DataInputStream filterIn = new DataInputStream(bis);
			
			String name = "service";
			byte[] data = EC.makeStringBuf(name);
			filterOut.write(data);
			filterOut.flush();
			
			InputThread it = new InputThread(
					clientSocket, 
					filterOut, 
					filterIn, 
					uploadDir,
					imgName
					);
			it.start();
			
			EC.sendStyleColor(filterOut, hairStyle, haircolor, imgName, UserDir);
			
			
			int count = 0;
			while(count <= 30) {
				File aiImg = new File(uploadDir + "/" +imgName);
				if(aiImg.exists()) {
					break;
				}
				count ++;
				Thread.sleep(1000);
			}
			
			if(count > 30) {
				
				chatVO aiVo = new chatVO()
						.setChatData("이미지 가 적절하지 않아요~")
						.setChatroomnum(chatroom)
						.setType(0);
				chatDao.insertChatterChat(aiVo);

				
				result = "image is not appropriate";
				
			}else {
				System.out.println("이미지 파일 저장 완료!");				
			
				if(user != null) {
					
					
					chatVO vo = new chatVO()
							.setType(2)
							.setChatData(imgName)
							.setChatroomnum(chatroom);
					int row2 = chatDao.insertChatterChat(vo);
					System.out.println(row2 + " 행에 AI 추가");
				}
				result = imgName;			
			}
			
		}
		catch (UnknownHostException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		catch (IOException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

		System.out.println(result + " 이다");
		return result;
	}
	
	
	
	
	@ResponseBody
	@RequestMapping(value = "/chat/dummy.do", method = RequestMethod.POST)
	public String dummy(@RequestBody HashMap<String, Object> map, HttpSession session, chatDAO chatDao) throws InterruptedException {
		System.out.println("더미데이터");
		String result = null;
		
		String uploadDir = session.getServletContext().getRealPath("resources/AiUploadImg");
		String UserDir = session.getServletContext().getRealPath("resources/uploadImg");
		
		MemberVo user = (MemberVo) session.getAttribute("user");
		int chatroom = 0;
		if(user != null) {
			chatroom = (int) session.getAttribute("chatroom");
		
			Thread.sleep(1000);
			
			chatVO aiVo = new chatVO()
					.setChatData("잠시만 기다려주세요")
					.setChatroomnum(chatroom)
					.setType(0);
			int row1 = chatDao.insertChatterChat(aiVo);
			System.out.println(row1 + " 잠시만 기다려주세요");
		}
		
		File Folder = new File(uploadDir);
		
		if(!Folder.exists()) {
			try {
				Folder.mkdir();
				System.out.println("False");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		}else {
			System.out.println("True");
		}
		
		String imgName = (String) map.get("img");
		
		String ip = "192.168.0.34";
		int PORT = 9400;
		Socket clientSocket = null;
		
		EchoClient EC = new EchoClient();
		
		try {
			clientSocket = new Socket(ip, PORT);
			
			OutputStream out = clientSocket.getOutputStream();
			BufferedOutputStream bos = new BufferedOutputStream(out);
			DataOutputStream filterOut = new DataOutputStream(bos);
			
			InputStream in = clientSocket.getInputStream();
			BufferedInputStream bis = new BufferedInputStream(in);
			DataInputStream filterIn = new DataInputStream(bis);
			
			String name = "dummy";
			byte[] data = EC.makeStringBuf(name);
			filterOut.write(data);
			filterOut.flush();
			
			InputThread2 it = new InputThread2(
					clientSocket, 
					filterOut, 
					filterIn, 
					uploadDir,
					imgName
					);
			it.start();
			
			
			EC.sendDummy(filterOut, imgName, UserDir);
			
			
			int count = 0;
			while(count <= 30) {
				File aiImg = new File(uploadDir + "/" +"4" + imgName);
				if(aiImg.exists()) {
					break;
				}
				count ++;
				Thread.sleep(1000);
			}
			
			if(count > 30) {
				
				chatVO aiVo = new chatVO()
						.setChatData("이미지 가 적절하지 않아요~")
						.setChatroomnum(chatroom)
						.setType(0);
				chatDao.insertChatterChat(aiVo);

				
				result = "image is not appropriate";
				
			}else {
				System.out.println("이미지 파일 저장 완료!");				
			
				if(user != null) {
					chatVO vo = new chatVO();
					for (int i = 0; i < 5; i++) {	
						if(i == 0) {
							vo.setType(3)
							  .setChatData(i + imgName)
						      .setChatroomnum(chatroom);	
						}else {
							vo.setType(1)
							  .setChatData(i + imgName)
						      .setChatroomnum(chatroom);							
						}
						int row2 = chatDao.insertChatterChat(vo);
						System.out.println(row2 + " 행에 AI 추가");
					}
				}
				result = "0" + imgName;			
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
}
