package khs.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.*;
import java.sql.*;
import khs.jdbc.util.*;
import khs.model.*;
import khs.service.*;

public class UpdateResultCommand extends Command {
	
	private String formPage = "/WEB-INF/forms/update_result.jsp";
	private String submitPage = "/WEB-INF/submits/update_result.jsp";
	private String errorPage = "/WEB-INF/errors/update_result.jsp";

	private UserUpdateService uuService = new UserUpdateService();
	// private UserRegistService urService = new UserRegistService();

	protected String processForm(HttpServletRequest request, HttpServletResponse response) {
		return formPage;
	}

	// POST 요청일 경우의 처리 로직을 구현하는 메소드
	protected String processSubmit(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		User login_user = (User)session.getAttribute("login_user");
		String user_id = request.getParameter("user_id");
		String user_pw = request.getParameter("user_pw");
		String user_pw_confirm = request.getParameter("user_pw_confirm");
		String user_nick = request.getParameter("user_nick");
		String strUser_tel = request.getParameter("user_tel");
		int user_tel = -1;
		try {
			user_tel = Integer.parseInt(strUser_tel);
		} catch (Exception e) {
			user_tel = -1;
		}
		String user_mail = request.getParameter("user_mail");
		
		
		String strIsCheckPW = request.getParameter("isCheckPW");
		boolean isCheckPW = Boolean.valueOf(strIsCheckPW).booleanValue();
		
		String strIsCheckPWConfirm = request.getParameter("isCheckPWConfirm");
		boolean isCheckPWConfirm = Boolean.valueOf(strIsCheckPWConfirm).booleanValue();
		
		String strIsCheckNick = request.getParameter("isCheckNick");
		boolean isCheckNick = Boolean.valueOf(strIsCheckNick).booleanValue();
		if(user_nick.equals(login_user.getUser_nick())) {
			isCheckNick = true;
		}
		
		String strIsCheckTelorMail = request.getParameter("isCheckTelOrMail");
		boolean isCheckTelOrMail = Boolean.valueOf(strIsCheckTelorMail).booleanValue();
		

		boolean isPass = isCheckPW && isCheckPWConfirm && isCheckNick && isCheckTelOrMail;
		boolean result = false;
		
		
		User user = new User(user_id, user_pw, user_nick, user_tel, user_mail, null, 0);
		if(isPass) {
			
			
			try (Connection conn = ConnectionProvider.getConnection()) {
				
				HashMap<String, Object> values = new HashMap<>();
				values.put("conn", conn);
				values.put("user", user);
				values.put("type", "user_update");
				
				HashMap<String, Object> resultMap = uuService.service(values);
				result = (boolean)resultMap.get("updateResult");
				session.setAttribute("login_user", (User)resultMap.get("updatedUser"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} 
		
		ArrayList<Boolean> resultList = new ArrayList<Boolean>();
		resultList.add(isCheckPW);
		resultList.add(isCheckPWConfirm);
		resultList.add(isCheckNick);
		resultList.add(isCheckTelOrMail);
		resultList.add(isPass);
		
		
		if (result == true) {
			
			return submitPage;
		} else {
			request.setAttribute("resultList", resultList);
			request.setAttribute("user_password_confirm", user_pw_confirm);
			request.setAttribute("user", user);
			return errorPage;
		}
		
		
	}

}
