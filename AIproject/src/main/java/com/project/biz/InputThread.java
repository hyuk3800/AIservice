package com.project.biz;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class InputThread {
	private Socket sock = null;
	private DataOutputStream fileterOut = null;
	private DataInputStream fileterIn = null;
	
	public InputThread(Socket sock, DataOutputStream fileterOut,DataInputStream fileterIn) {
		this.sock = sock;
		this.fileterOut = fileterOut;
		this.fileterIn = fileterIn;
	}
	
	public void run() {
		while(true) {
			try {
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
				
				FileOutputStream fos = new FileOutputStream(sImage_name);
				BufferedOutputStream bos = new BufferedOutputStream(fos);
				bos.write(image_file);
				bos.close();
				System.out.println("수신완료");
				System.out.println("-------------------------------------------");
				
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
