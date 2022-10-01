package com.project.biz.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import org.springframework.stereotype.Repository;

import com.project.biz.JDBCUtill;

@Repository("userDAO")
public class userDAO {
	
	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	
	private String SelectSql = "SELECT * FROM aiservice.member "
							 + "WHERE id=? AND pwd=?;";
	
	private String InsertSql = "INSERT INTO aiservice.member (id, nickname, pwd, cre_date, mod_date) " 
							 + "VALUES (?, ?, ?, NOW(), NOW());";
	
	
	public userVO selectMember(userVO vo) {
		System.out.println("select User");
		conn = JDBCUtill.getConn();
		userVO user = null;
		try {
			stmt = conn.prepareStatement(SelectSql);	
			stmt.setString(1, vo.getID());
			stmt.setString(2, vo.getPassword());
			rs = stmt.executeQuery();
			if(rs.next()) {
				user = new userVO()
						.setID(rs.getString("id"))
						.setUserName(rs.getString("nickname"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtill.close(rs, stmt, conn);
		}
		System.out.println(vo);
		return user;
	}
	
	public int InsertMember(userVO vo) {
		System.out.println("insert User");
		conn = JDBCUtill.getConn();
		int row = 0;
		
		
		return row;
	}
}
