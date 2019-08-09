package khs.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.*;
import java.sql.*;
import khs.jdbc.util.*;
import khs.model.*;
import khs.service.*;

public class UserUpdateCommand extends Command {
	private String formPage = "/WEB-INF/forms/user_update.jsp";
	private String submitPage = "/WEB-INF/submits/user_update.jsp";
	private String errorPage = "/WEB-INF/errors/user_update.jsp";

	private UserIDCheckService uicService = new UserIDCheckService();

	protected String processForm(HttpServletRequest request, HttpServletResponse response) {
		return null;
	}

	// POST 요청일 경우의 처리 로직을 구현하는 메소드
	protected String processSubmit(HttpServletRequest request, HttpServletResponse response) {
		
		return formPage;
	}

}
