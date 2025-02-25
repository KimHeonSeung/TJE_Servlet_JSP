package tje.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.*;
import java.sql.*;
import tje.jdbc.util.*;
import tje.model.Member;
import tje.service.*;

public class MemberCommand extends Command {
	private String formPage = "/WEB-INF/forms/member.jsp";
	private String submitPage = "/WEB-INF/submits/member.jsp";
	private String errorPage = "/WEB-INF/errors/member.jsp";

	protected String processForm(HttpServletRequest request, HttpServletResponse response) {
		return formPage;
	}

	protected String processSubmit(HttpServletRequest request, HttpServletResponse response) {
		System.out.println(1);
		String password = request.getParameter("password");

		HttpSession session = request.getSession();
		Member member = (Member)session.getAttribute("login_member");
		boolean isPasswordCheck = member.getPassword().equals(password);
		
		if( isPasswordCheck ) {
			session.setAttribute("member_id_detail", member.getMember_id());
		} else {
			request.setAttribute("errorMsg", "PASSWORD를 확인하세요.");
			return errorPage;
		}		

		return submitPage;
	}
}
