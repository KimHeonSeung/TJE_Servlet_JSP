package khs.command;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.*;
import java.sql.*;
import khs.jdbc.util.*;
import khs.model.*;
import khs.service.*;

public class LogoutCommand extends Command {
	private String formPage = "/WEB-INF/forms/logout.jsp";
	private String submitPage = "/WEB-INF/submits/logout.jsp";
	private String errorPage = "/WEB-INF/errors/logout.jsp";


	protected String processForm(HttpServletRequest request, HttpServletResponse response) {
		return null;
	}

	// POST 요청일 경우의 처리 로직을 구현하는 메소드
	protected String processSubmit(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		ServletContext application = request.getServletContext();
		
		User user = (User)session.getAttribute("login_user");
		
		// 로그아웃
		session.invalidate();
		synchronized (application) {
			if (application.getAttribute("login_user_count") == null)
				application.setAttribute("login_user_count", 0);
			else {
				Integer count = (Integer) application.getAttribute("login_user_count");
				application.setAttribute("login_user_count", count - 1);
			}
		}
		request.setAttribute("logout_user", user);
		return submitPage;
	}

}
