package com.project.biz;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class EchoClient {
	
	public static byte[] makeStringBuf(String str) {
		
		int len = str.length();
		byte[] lenBuf = new byte[4];
		lenBuf[0] = (byte)((len&0xFF000000)>>24);
		lenBuf[1] = (byte)((len&0x00FF0000)>>16);
		lenBuf[2] = (byte)((len&0x0000FF00)>>8);
		lenBuf[3] = (byte)((len&0x000000FF));
		
		byte[] strBuf = str.getBytes(StandardCharsets.UTF_8);
		
		byte[] data = new byte[lenBuf.length + strBuf.length];
		System.arraycopy(lenBuf, 0, data, 0, lenBuf.length);
		System.arraycopy(strBuf, 0, data, lenBuf.length, strBuf.length);
		
		return data;
	}
	
	public static byte[] makeLongBuf(long len) {
		byte[] byterAttay = ByteBuffer.allocate(8).putLong(len).array();
		return byterAttay;
	}
	
	
	public static void sendStyleColor(DataOutputStream fileterOut, String style, String color, String image, String UserDir) throws IOException
	{
		byte[] styleBuf = makeStringBuf(style);
		fileterOut.write(styleBuf);
		fileterOut.flush();
		
		byte[] colorBuf = makeStringBuf(color);
		fileterOut.write(colorBuf);
		fileterOut.flush();
		
		byte[] imageBuf = makeStringBuf(image);
		fileterOut.write(imageBuf);
		fileterOut.flush();
		
		File file = new File(UserDir + "/" + image);
		long bytes = file.length();
		byte[] fLenBuf = makeLongBuf(bytes);
		fileterOut.write(fLenBuf);
		
		InputStream inFile = new FileInputStream(UserDir + "/" + image);
		BufferedInputStream bis = new BufferedInputStream(inFile);
		int copyByte = 0;
		int readLen = 0;
		byte[] buf = new byte[1024];
		while(true) {
			readLen = bis.read(buf);
			if(readLen == -1) {
				break;
			}
			fileterOut.write(buf, 0, readLen);
			fileterOut.flush();
			copyByte += readLen;
		}
		System.out.println(copyByte + " 바이트를 전송했습니다");
		inFile.close();
	}

	public void sendDummy(DataOutputStream fileterOut, String image, String UserDir) throws IOException{
	
		byte[] imageBuf = makeStringBuf(image);
		fileterOut.write(imageBuf);
		fileterOut.flush();
		
		File file = new File(UserDir + "/" + image);
		long bytes = file.length();
		byte[] fLenBuf = makeLongBuf(bytes);
		fileterOut.write(fLenBuf);
		
		InputStream inFile = new FileInputStream(UserDir + "/" + image);
		BufferedInputStream bis = new BufferedInputStream(inFile);
		int copyByte = 0;
		int readLen = 0;
		byte[] buf = new byte[1024];
		while(true) {
			readLen = bis.read(buf);
			if(readLen == -1) {
				break;
			}
			fileterOut.write(buf, 0, readLen);
			fileterOut.flush();
			copyByte += readLen;
		}
		System.out.println(copyByte + " 바이트를 전송했습니다");
		inFile.close();
	}
}
