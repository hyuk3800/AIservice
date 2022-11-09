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
	public static Scanner sc = new Scanner(System.in);
	
	public static void showMenu() {
		System.out.println("=============================================");
		System.out.println("1. 이미지 파일 이름 입력 전송");
		System.out.println("2. exit");
	}
	public static int getSelectMenu() {
		System.out.print(">> ");
		return sc.nextInt();
	}
	public static void showStyles() {
		String[] styles = {"afro hairstyle", "bob cut hairstyle", "bowl cut hairstyle", "braid hairstyle", "caesar cut hairstyle", "chignon hairstyle", "cornrows hairstyle", "crew cut hairstyle", "crown braid hairstyle", "curtained hair hairstyle", "dido flip hairstyle", "dreadlocks hairstyle", "extensions hairstyle", "fade hairstyle", "fauxhawk hairstyle", "finger waves hairstyle", "french braid hairstyle", "frosted tips hairstyle", "full crown hairstyle", "harvard clip hairstyle", "high and tight hairstyle", "hime cut hairstyle", "hi-top fade hairstyle","jewfro hairstyle", "jheri curl hairstyle", "liberty spikes hairstyle", "marcel waves hairstyle", "mohawk hairstyle", "pageboy hairstyle", "perm hairstyle", "pixie cut hairstyle", "psychobilly wedge hairstyle", "quiff hairstyle", "regular taper cut hairstyle", "ringlets hairstyle", "shingle bob hairstyle", "short hair hairstyle", "slicked-back hairstyle", "spiky hair hairstyle","surfer hair hairstyle", "taper cut hairstyle", "the rachel hairstyle", "undercut hairstyle", "updo hairstyle"};
		System.out.println("-----------------------------------");
		for(int i=0;i<styles.length;i++) {
			System.out.println(styles[i]);
		}
	}
	public static void showColors() {
		String[] colors = {"purple", "red", "orange", "yellow", "green", "blue", "gray", "brown", "black", "white", "blond", "pink"};
		System.out.println("-----------------------------------");
		for(int i=0;i<colors.length;i++) {
			System.out.println(colors[i]);
		}
	}
	
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
	
	
	public static void sendStyleColor(DataOutputStream fileterOut, String style, String color, String image) throws IOException
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
		
		File file = new File(image);
		long bytes = file.length();
		byte[] fLenBuf = makeLongBuf(bytes);
		fileterOut.write(fLenBuf);
		
		InputStream inFile = new FileInputStream(image);
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
