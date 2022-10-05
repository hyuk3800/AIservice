package com.project.biz;

import java.security.MessageDigest;
import java.security.SecureRandom;

public class Encrypt {
	
	public String getSalt() {
		
		SecureRandom r = new SecureRandom();
		
		byte[] salt = new byte[20];
		
		r.nextBytes(salt);
		
		StringBuffer sb = new StringBuffer();
		for (byte b : salt) {
			sb.append(String.format("%02x", b));
		};
		
		System.out.println(sb.toString());
		
		return sb.toString();
	}
	
	
	public String getEncrypt(String pwd, String salt) {
		
		String result = null;
		
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			
			md.update((pwd + salt).getBytes());
			byte[] pwdsalt = md.digest();
			
			StringBuffer sb = new StringBuffer();
			for (byte b : pwdsalt) {
				sb.append(String.format("%02x", b));
			}
			
			result = sb.toString();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
		
	}


	public static void main(String[] args) {
		Encrypt en = new Encrypt();
		
		String pwd = "나는이인혁";
		System.out.println("pwd : " + pwd);
	
		String salt = "jhsdjkfhsadfjksfahafksjsdafhkjsdyisdug";
		System.out.println("salt : " + salt);
		
		
		String res = en.getEncrypt(pwd, salt);
		
		System.out.println(res);
	}

}
