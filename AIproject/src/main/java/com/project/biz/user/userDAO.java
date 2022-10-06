package com.project.biz.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import org.springframework.stereotype.Repository;

import com.project.biz.Encrypt;
import com.project.biz.JDBCUtill;
import com.project.member.MemberVo;

@Repository("userDAO")
public class userDAO {
	
	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	
	private String SelectSql = "SELECT * FROM aiservice.member "
							 + "WHERE id=? AND pwd=?;";
	
	private String InsertSql = "INSERT INTO aiservice.member (id, username, pwd, cre_date, mod_date) " 
							 + "VALUES (?, ?, ?, NOW(), NOW());";
	
	
	public MemberVo selectMember(MemberVo vo) {
		System.out.println("select User");
		conn = JDBCUtill.getConn();
		MemberVo user = null;
		Encrypt enc = new Encrypt();
		try {
			stmt = conn.prepareStatement(SelectSql);	
			stmt.setString(1, vo.getID());
			stmt.setString(2, enc.getEncrypt(vo.getPwd(), enc.getEsalt()));
			rs = stmt.executeQuery();
			if(rs.next()) {
				user = new MemberVo();
				user.setID(rs.getString("id"));
				user.setNickname(rs.getString("username"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtill.close(rs, stmt, conn);
		}
		System.out.println(vo);
		return user;
	}
	
	public int InsertMember(MemberVo vo) {
		System.out.println("insert User");
		conn = JDBCUtill.getConn();
		Encrypt enc = new Encrypt();
		int row = 0;
		try {
			stmt = conn.prepareStatement(InsertSql);
			stmt.setString(1, vo.getID());
			stmt.setString(2, vo.getNickname());
			stmt.setString(3, enc.getEncrypt(vo.getPwd(), enc.getEsalt()));
			row = stmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtill.close(stmt, conn);
		}
		
		return row;
	}
}
