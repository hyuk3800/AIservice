package com.project.member.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.project.member.MemberVo;

public class MemberDao {
	private Connection conn = null;
	private PreparedStatement stmt = null;
	private DataSource dataSource;
	private final String memberInsertSql =
			"INSERT INTO member(nickname, password, cre_date, mod_date) VALUES(?, ?, NOW(), NOW())";
			
	public void insertMember(MemberVo vo) {
		try {
			conn = dataSource.getConnection();
			stmt = conn.prepareStatement(memberInsertSql);
			stmt.setString(1, vo.getNickname());
			stmt.setString(2, vo.getPwd());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
