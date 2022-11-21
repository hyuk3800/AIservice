package com.project.biz;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;


public class InputThread2 extends Thread {
	
	private Socket sock = null;
	private DataOutputStream fileterOut = null;
	private DataInputStream fileterIn = null;
	private String uploadDir = null;
	private String imgName = null;
	private String result = null;
	
	public InputThread2(
			Socket sock, 
			DataOutputStream fileterOut,
			DataInputStream fileterIn,
			String uploadDir,
			String imgName
			) {

		this.sock = sock;
		this.fileterOut = fileterOut;
		this.fileterIn = fileterIn;
		this.uploadDir = uploadDir;
		this.imgName = imgName;
	}
	
	public String getResult() {
		return result;
	}


	public void setResult(String result) {
		this.result = result;
	}


	public void run() {
			try {
//				int count = 0;
				for(int i=0; i<5; i++) {
					byte[] imagename_size = new byte[4];
					int len = this.fileterIn.read(imagename_size);				
					ByteBuffer buffer = ByteBuffer.allocate(Integer.BYTES);
					buffer.put(imagename_size);
					buffer.flip();
					int nImagename_size = buffer.getInt();
					System.out.println("수신  - 이미지 이름 크기 :  " + nImagename_size);
					
					byte[] image_name = new byte[nImagename_size];
					len = this.fileterIn.read(image_name);
					String sImage_name = new String(image_name, StandardCharsets.UTF_8);
					System.out.println("수신 - 이미지 이름 : " + sImage_name);
					
					if(sImage_name.equals("image is not appropriate")) {
						
						System.out.println("이미지 가 적절하지 않아요~");
						i = 10;
						setResult("image is not appropriate");
		
					}
					else {
						byte[] imagefile_size = new byte[8];
						len = this.fileterIn.read(imagefile_size);
						ByteBuffer bufferImageNameSize = ByteBuffer.allocate(Long.BYTES);
						bufferImageNameSize.put(imagefile_size);
						bufferImageNameSize.flip();
						long nImagefile_size = bufferImageNameSize.getLong();
						System.out.println("수신 이미지 파일 크기 : " + nImagefile_size);
						
						int total_size = 0;
						byte[] image_file = new byte[(int) nImagefile_size];
						while(total_size < nImagefile_size) {
							int recvSize = (int)nImagefile_size - total_size;
							int readLen = this.fileterIn.read(image_file, total_size, recvSize);
							total_size += readLen;
							System.out.println("수신 - 이미지 파일 : " + readLen);
						}
						
						FileOutputStream fos = new FileOutputStream(uploadDir + "/" + i + imgName);
						BufferedOutputStream bos = new BufferedOutputStream(fos);
						bos.write(image_file);
						
						
						
						 
						bos.close();
						System.out.println("수신완료");
						System.out.println("-------------------------------------------");	
						if (i == 0) {
							setResult(i +imgName);							
						}
					}
				}
				
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
