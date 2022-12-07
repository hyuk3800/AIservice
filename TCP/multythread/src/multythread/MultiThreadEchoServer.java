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


class ClientInfo{
	String name;
	Socket socket;
	
	ClientInfo(String name, Socket socket){
		this.name = name;
		this.socket = socket;
	}
}

class MatchHairInfo{
	ClientInfo service;
	ClientInfo hairstyle;
	
	MatchHairInfo(ClientInfo service, ClientInfo hairstyle){
		this.service = service;
		this.hairstyle = hairstyle;
	}
}

class MatchDumyInfo{
	ClientInfo dummydata;
	ClientInfo dualstyle;
	
	MatchDumyInfo(ClientInfo dummydata, ClientInfo dualstyle){
		this.dummydata = dummydata;
		this.dualstyle = dualstyle;
	}
}

public class MultiThreadEchoServer {
	static int serviceCnt = 0;
	static int hairstyleCnt = 0;
	static int dummyCnt = 0;
	static int dualstyleCnt = 0;
	static List<ClientInfo> freeEngineList = new ArrayList<ClientInfo>();
	static List<ClientInfo> freeEngineList2 = new ArrayList<ClientInfo>();
	static List<MatchHairInfo> matchHairList = new ArrayList<MatchHairInfo>();
	static List<MatchDumyInfo> matchDumyList = new ArrayList<MatchDumyInfo>();
	
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
				
				clientSocket.setSoTimeout(1000*60*60*3);
				System.out.println(clientSocket.getSoTimeout());
				
				String name = getClientName(clientSocket);
				
				if(name.equals("service")) {
					if(freeEngineList.size() == 0) {
						System.out.println("남은 엔진이 없어서 할당할 수 없음");
						clientSocket.close();
					}
					name += serviceCnt++;
					
					ClientInfo hairstyle = freeEngineList.remove(0);
					MatchHairInfo matchInfo = new MatchHairInfo(
							new ClientInfo(name, clientSocket), 
							hairstyle
							);
					matchHairList.add(matchInfo);
					

				}else if(name.equals("hairstyle")) {
					name += hairstyleCnt++;
					freeEngineList.add(new ClientInfo(name, clientSocket));
				}
				else if(name.equals("dummydata")) {
					if(freeEngineList2.size() == 0) {
						System.out.println("남은 엔진이 없어서 할당할 수 없음");
						clientSocket.close();
					}
					name += dummyCnt++;
					
					ClientInfo dualstyle = freeEngineList2.remove(0);
					MatchDumyInfo matchDummy = new MatchDumyInfo(
							new ClientInfo(name, clientSocket), 
							dualstyle
							);
					matchDumyList.add(matchDummy);
					
				}
				else if(name.equals("dualstyle")){
					name += dualstyleCnt++;
					freeEngineList2.add(new ClientInfo(name, clientSocket));
				}
				else {
					hm.put(name, clientSocket);
				}
				
				
				System.out.println(name + "과" + clientSocket.toString() + "를 등록");
				EchoThread echoThread = new EchoThread(matchHairList, matchDumyList, freeEngineList, freeEngineList2,  hm, clientSocket, name);
				echoThread.start();
				System.out.println();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

class EchoThread extends Thread {
	
	List<MatchHairInfo> matchHairList;
	List<MatchDumyInfo> matchDumyList;
	
	List<ClientInfo> freeEngineList;
	List<ClientInfo> freeEngineList2;
	
	// 있는 소켓
	private HashMap hm;

	private Socket socket;
	
	private String name;
	
	public EchoThread(
			List<MatchHairInfo> matchHairList, 
			List<MatchDumyInfo> matchDumyList,
			
			List<ClientInfo> freeEngineList, 
			List<ClientInfo> freeEngineList2,
			
			HashMap hm, Socket socket, String name) {
		
		this.matchHairList = matchHairList;
		this.matchDumyList = matchDumyList;
		this.freeEngineList = freeEngineList;
		this.freeEngineList2 = freeEngineList2;
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
	
	Socket getMatchSocket(String name, boolean isService) {
		Socket socket = null;
		for(int i=0;i<matchHairList.size();i++) {
			MatchHairInfo mhi = matchHairList.get(i);
			
			if(isService) {
				if(mhi.service.name.equals(name)) {
					socket = mhi.hairstyle.socket;
					break;
				}			
			}else {
				if(mhi.hairstyle.name.equals(name)) {
					socket = mhi.service.socket;
					break;
				}	
			}
		}
		
		return socket;
	}
	
	Socket getMatchSocket2(String name, boolean isService) {
		Socket socket = null;
		for(int i=0;i<matchDumyList.size();i++) {
			MatchDumyInfo mhi = matchDumyList.get(i);
			
			if(isService) {
				if(mhi.dummydata.name.equals(name)) {
					socket = mhi.dualstyle.socket;
					break;
				}			
			}else {
				if(mhi.dualstyle.name.equals(name)) {
					socket = mhi.dummydata.socket;
					break;
				}	
			}
		}
		
		return socket;
	}
	
	
	boolean removeMatchHairList(String name) {
		boolean isRemove = false;
		for(int i=0;i<matchHairList.size();i++) {
			MatchHairInfo mhi = matchHairList.get(i);
			if(mhi.service.name.equals(name)) {
				
				ClientInfo hairstyle = mhi.hairstyle;
				freeEngineList.add(hairstyle);
				
				matchHairList.remove(i);
				isRemove = true;
				break;
			}
		}
		
		return isRemove;		
	}
	
	boolean removeMatchDumyList(String name) {
		boolean isRemove = false;
		for(int i=0;i<matchDumyList.size();i++) {
			MatchDumyInfo mhi = matchDumyList.get(i);
			if(mhi.dummydata.name.equals(name)) {
				
				ClientInfo dualstyle = mhi.dualstyle;
				freeEngineList2.add(dualstyle);
				
				matchDumyList.remove(i);
				isRemove = true;
				break;
			}
		}
		
		return isRemove;		
	}
	
	
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
					
					if(name.substring(0, 7).equals("service")) {
						removeMatchHairList(this.name);
					}
					else if(name.substring(0, 7).equals("dummyda")) {
						removeMatchDumyList(this.name);
					}
					break;
					
				}else {
					
					System.out.println(inetAddr.getHostAddress() + " 클라이언트로부터 수신");
					
//					/////////////////////////////////////////////////////////////////////
//					유저
//					if(name.equals("service")) {
					if(name.substring(0, 7).equals("service")) {
						//Socket serviceSocket = (Socket) this.hm.get("hairstyle");
						Socket serviceSocket = getMatchSocket(name, true);
						OutputStream out = serviceSocket.getOutputStream();
						BufferedOutputStream bos = new BufferedOutputStream(out);
						bos.write(buf, 0, len);
						bos.flush();
					}
//					파이썬
//					else if(name.equals("hairstyle")) {
					else if(name.substring(0, 7).equals("hairsty")) {
						//Socket serviceSocket = (Socket) this.hm.get("service");
						Socket serviceSocket = getMatchSocket(name, false);
						
						// service와 hairstyle이 매칭되어 service소켓을 찾았을 때
						if(serviceSocket != null) {
							OutputStream out = serviceSocket.getOutputStream();
							BufferedOutputStream bos = new BufferedOutputStream(out);
							bos.write(buf, 0, len);
							bos.flush();							
						}else {
							// ping = -1을 받고 다시 돌려준다
							OutputStream out = socket.getOutputStream();
							BufferedOutputStream bos = new BufferedOutputStream(out);
							bos.write(buf, 0, len);
							bos.flush();
						}
					}
//					///////////////////////////////////////////////////////////////
//					유저
//					else if(name.equals("dummy")) {
					else if(name.substring(0, 7).equals("dummyda")) {
//						Socket serviceSocket = (Socket) this.hm.get("dualstyle");
//						System.out.println(dummy);
						Socket serviceSocket = getMatchSocket2(name, true);
						OutputStream out = serviceSocket.getOutputStream();
						BufferedOutputStream bos = new BufferedOutputStream(out);
						bos.write(buf, 0, len);
						bos.flush();
					}
//					파이썬
//					else if(name.equals("dualstyle")) {
					else if(name.substring(0, 7).equals("dualsty")) {
//						Socket serviceSocket = (Socket) this.hm.get("dummy");
						Socket serviceSocket = getMatchSocket2(name, false);
						
						if(serviceSocket != null) {
							OutputStream out = serviceSocket.getOutputStream();
							BufferedOutputStream bos = new BufferedOutputStream(out);
							bos.write(buf, 0, len);
							bos.flush();
						}
						else {
							// ping = -1을 받고 다시 돌려준다
							OutputStream out = socket.getOutputStream();
							BufferedOutputStream bos = new BufferedOutputStream(out);
							bos.write(buf, 0, len);
							bos.flush();
						}
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