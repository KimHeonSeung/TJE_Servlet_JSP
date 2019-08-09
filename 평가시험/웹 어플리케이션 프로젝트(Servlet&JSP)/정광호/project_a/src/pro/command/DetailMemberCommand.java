
package pro.command;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pro.filter.CookieManager;
import pro.jdbc.ConnectionProvider;
import pro.model.Member;
import pro.service.LoginService;

public class DetailMemberCommand extends Command {
	private String formPage = "/WEB-INF/iframe/detail_member.jsp";
	private String submitPage = "/WEB-INF/submits/login.jsp";
	private String errorPage = "/WEB-INF/errors/login.jsp";


	protected String processForm(HttpServletRequest request, HttpServletResponse response) {
		
		return formPage;
	}

	protected String processSubmit(HttpServletRequest request, HttpServletResponse response) {


		return null;
	}
}
