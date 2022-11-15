package com.project.biz;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;


public class InputThread3 extends Thread {
	
	private Socket sock = null;
	private DataOutputStream fileterOut = null;
	private DataInputStream fileterIn = null;
	private String result = null;
	
	public InputThread3(
			Socket sock, 
			DataOutputStream fileterOut,
			DataInputStream fileterIn
			) {

		this.sock = sock;
		this.fileterOut = fileterOut;
		this.fileterIn = fileterIn;

	}
	
	public String getResult() {
		return result;
	}


	public void setResult(String result) {
		this.result = result;
	}


	public void run() {
			try {
				byte[] chat_size = new byte[4];
				int len = this.fileterIn.read(chat_size);				
				ByteBuffer buffer = ByteBuffer.allocate(Integer.BYTES);
				buffer.put(chat_size);
				buffer.flip();
				int nchat_size = buffer.getInt();
//				System.out.println("수신  - 챗봇 채팅 크기 :  " + nchat_size);
				System.out.println(nchat_size);
//				
				byte[] chat = new byte[nchat_size];
				len = this.fileterIn.read(chat);
				System.out.println("len:" + len);
				String sChat = new String(chat, StandardCharsets.UTF_8);
				System.out.println("수신 - 챗봇 채팅 : " + sChat);
				
				
				System.out.println("수신완료");
				System.out.println("-------------------------------------------");	
				setResult(sChat);
				
			}
			catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				try {
					if (sock != null) {
						sock.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
	}
}
