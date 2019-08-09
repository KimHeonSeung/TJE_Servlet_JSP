
package pro.command;

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

public class LoginCommand extends Command {
	private String formPage = "/WEB-INF/forms/login.jsp";
	private String submitPage = "/WEB-INF/submits/login.jsp";
	private String errorPage = "/WEB-INF/errors/login.jsp";
	private String checkPage = "/WEB-INF/forms/main.jsp";

	private LoginService lService = new LoginService();

	protected String processForm(HttpServletRequest request, HttpServletResponse response) {

		HttpSession session = request.getSession(false);
		
		if (session != null && session.getAttribute("login_member") != null) {
			// 접속 여부 검사
			request.setAttribute("errorMSG", "이미 접속중입니다.");
			return checkPage;
		}

		return formPage;
	}

	@Override
	protected String processSubmit(HttpServletRequest request, HttpServletResponse response) {

		String member_id = request.getParameter("member_id");
		String password = request.getParameter("password");
		String checkbox = request.getParameter("checkbox");

		try (Connection conn = ConnectionProvider.getConnection()) {
			HashMap<String, Object> values = new HashMap<String, Object>();

			Member member = new Member();
			member.setMember_id(member_id);
			member.setPassword(password);

			values.put("conn", conn);
			values.put("member", member);

			HashMap<String, Object> result = lService.service(values);
			Member memberSearch = new Member();
			boolean login = (boolean) result.get("result");

			String viewPage = null;

			if (login) {
				// 로그인 성공
				HttpSession session = request.getSession(false);
				memberSearch = (Member) result.get("memberSearch");
				session.setAttribute("login_member", memberSearch);
				
				MainCommand main = new MainCommand();
				
				// 아이디 저장 체크박스
				if (checkbox != null && checkbox.equals("on")) {
					Cookie cookie = new Cookie("save_login_id", member.getMember_id());
					cookie.setMaxAge(60 * 60 * 24);
					response.addCookie(cookie);

					response.sendRedirect(request.getContextPath() +"/main.do");
					
				} else {
					Cookie cookie = new Cookie("save_login_id", "");
					cookie.setMaxAge(0);
					response.addCookie(cookie);
					
					response.sendRedirect(request.getContextPath() +"/main.do");
				}

			} else {
				// 로그인 실패
				request.setAttribute("errorMSG", "로그인 실패");
				return processForm(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}
