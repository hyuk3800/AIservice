package multythread;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



public class MultiThreadEchoServer {
	final static int PORT = 9400;
	
	// 있는 소켓의 종류
	static HashMap hm = new HashMap();
	

	public static byte[] getCommData(DataInputStream filerIn) throws IOException {
		
		byte[] sizeBuf = new byte[4];
		
		int totalLen = 0;
		int len = filerIn.read(sizeBuf);
		totalLen += len;
		while(totalLen < sizeBuf.length) {
			int noReceivedLen = sizeBuf.length - totalLen;
			len = filerIn.read(sizeBuf, totalLen, noReceivedLen);
			totalLen += len;
		}
		int size = ByteBuffer.wrap(sizeBuf).getInt();
		
		byte[] buf = new byte[size];
		totalLen = 0;
		len = filerIn.read(buf);
		totalLen += len;
		while (totalLen < size) {
			int noReceivedLen = size - totalLen;
			len = filerIn.read(buf, totalLen, noReceivedLen);
			totalLen += len;
		}
		
		return buf;
	}
	
	
	public static String byteToString(byte[] buf) {
		String converted = new String(buf, StandardCharsets.UTF_8);
		return converted;
	}
	
	public static String getClientName(Socket client) throws IOException {
		InputStream is = client.getInputStream();
		DataInputStream filerIn = new DataInputStream(is);
		byte[] buf = getCommData(filerIn);
		String name = byteToString(buf);
		return name;
	}
	
	
	public static void main(String[] args) {
		try {
			ServerSocket serverSocket = new ServerSocket(PORT);
		
			
			while(true) {
				System.out.println("클라이언트 접속 대기.....");
			
				Socket clientSocket = serverSocket.accept();
				
				String name = getClientName(clientSocket);
				
				hm.put(name, clientSocket);
				System.out.println(name + "과" + clientSocket.toString() + "를 등록");
				EchoThread echoThread = new EchoThread(hm, clientSocket, name);
				echoThread.start();
				System.out.println();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

class EchoThread extends Thread {
	// 있는 소켓
	private HashMap hm;

	private Socket socket;
	
	private String name;
	
	public EchoThread(HashMap hm, Socket socket, String name) {
		this.hm = hm;
		this.name = name;
		this.socket = socket;	
		

	}
//	
//	public HashMap getHm() {
//		return hm;
//	}
//	
//	public HashMap getUsing() {
//		return using;
//	}
//
//	public void setUsing(HashMap using) {
//		this.using = using;
//	}

	public void run() {
		try {

			InetAddress inetAddr = socket.getInetAddress();  
			System.out.println(inetAddr.getHostAddress() + "로부터 접속했습니다~");
			
			InputStream in = socket.getInputStream();
			BufferedInputStream bis = new BufferedInputStream(in);
			DataInputStream fileterIn = new DataInputStream(bis);
			
			byte[] buf = new byte[4096];
			while(true) {
				int len = bis.read(buf);
				
				if (len <= 0) {
					
					System.out.println(this.name + " : 클라이언트 접속 단절!");
					break;
					
				}else {
					
					System.out.println(inetAddr.getHostAddress() + " 클라이언트로부터 수신");
					
//					/////////////////////////////////////////////////////////////////////
//					유저
					if(name.equals("service")) {
						Socket serviceSocket = (Socket) this.hm.get("hairstyle");
						OutputStream out = serviceSocket.getOutputStream();
						BufferedOutputStream bos = new BufferedOutputStream(out);
						bos.write(buf, 0, len);
						bos.flush();
					}
//					파이썬
					else if(name.equals("hairstyle")) {
						Socket serviceSocket = (Socket) this.hm.get("service");
						OutputStream out = serviceSocket.getOutputStream();
						BufferedOutputStream bos = new BufferedOutputStream(out);
						bos.write(buf, 0, len);
						bos.flush();
					}
//					///////////////////////////////////////////////////////////////
//					유저
					else if(name.equals("dummy")) {
						Socket serviceSocket = (Socket) this.hm.get("dualstyle");
						OutputStream out = serviceSocket.getOutputStream();
						BufferedOutputStream bos = new BufferedOutputStream(out);
						bos.write(buf, 0, len);
						bos.flush();
					}
//					파이썬
					else if(name.equals("dualstyle")) {
						Socket serviceSocket = (Socket) this.hm.get("dummy");
						OutputStream out = serviceSocket.getOutputStream();
						BufferedOutputStream bos = new BufferedOutputStream(out);
						bos.write(buf, 0, len);
						bos.flush();
					}
//					/////////////////////////////////////////////////////////////////
//					유저
					else if(name.equals("user_chat")) {
						Socket serviceSocket = (Socket) this.hm.get("chat_bot");
//						Socket serviceSocket = (Socket) this.chatbotL.get(0);
//						this.chatbotL.remove(0);
						
						OutputStream out = serviceSocket.getOutputStream();
						BufferedOutputStream bos = new BufferedOutputStream(out);
						bos.write(buf, 0, len);
						bos.flush();
									
						
					}
//					파이썬
					else if(name.equals("chat_bot")) {
						System.out.println(name);
						Socket serviceSocket = (Socket) this.hm.get("user_chat");
						OutputStream out = serviceSocket.getOutputStream();
						BufferedOutputStream bos = new BufferedOutputStream(out);
						System.out.println(bos);
						bos.write(buf, 0, len);
						bos.flush();
						

					}


				}
			}
			System.out.println(inetAddr.getHostAddress() + " 클라이언트 접속 종료");
			System.out.println(this.hm);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
}