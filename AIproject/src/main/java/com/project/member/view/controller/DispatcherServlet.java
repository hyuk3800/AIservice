package com.project.member.view.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.project.member.MemberVo;
import com.project.member.impl.MemberDao;

public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		
		if (uri.equals("/signup.do")) {
			String nickname = req.getParameter("nickname");
			String password = req.getParameter("password");
			
			MemberVo vo = new MemberVo();
			vo.setNickname(nickname);
			vo.setPwd(password);
			
			MemberDao memberDao = new MemberDao();
			memberDao.insertMember(vo);
			
			req.setAttribute("memberDao", memberDao);
			resp.sendRedirect("home.html");
		} else {
			resp.sendError(400, "ป็น้");
		}
	}
}
