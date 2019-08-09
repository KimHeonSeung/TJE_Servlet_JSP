
package pro.command;

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
import pro.service.LogoutService;

public class LogoutCommand extends Command {

	private LogoutService loService = new LogoutService();

	protected String processForm(HttpServletRequest request, HttpServletResponse response) {
		try (Connection conn = ConnectionProvider.getConnection();) {
			HashMap<String, Object> values = new HashMap<String, Object>();
			HttpSession session = request.getSession();
			Member member = (Member) session.getAttribute("login_member");

			member.setMember_id(member.getMember_id());
			member.setPassword(member.getPassword());

			values.put("conn", conn);
			values.put("member", member);
			values.put("type", "last_access_time");

			HashMap<String, Object> resultmap = loService.service(values);

			boolean result = (boolean) resultmap.get("result");
			// 로그아웃 성공시 로그인 세션 기록 삭제
			if (result) {
				session.invalidate();
			}
			response.sendRedirect(request.getParameter("myurl"));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected String processSubmit(HttpServletRequest request, HttpServletResponse response) {

		return null;
	}
}
