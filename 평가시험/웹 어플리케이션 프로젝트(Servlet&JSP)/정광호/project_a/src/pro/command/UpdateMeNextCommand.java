
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
import pro.service.UpdateMeService;

public class UpdateMeNextCommand extends Command {
	private String formPage = "/WEB-INF/iframe/updateme_next.jsp";

	private UpdateMeService umService = new UpdateMeService();

	protected String processForm(HttpServletRequest request, HttpServletResponse response) {

		return formPage;
	}

	protected String processSubmit(HttpServletRequest request, HttpServletResponse response) {

		String password = request.getParameter("password");

		try (Connection conn = ConnectionProvider.getConnection();) {
			HashMap<String, Object> values = new HashMap<String, Object>();

			Member member = new Member();
			member.setPassword(password);

			values.put("conn", conn);
			values.put("member", member);

			HashMap<String, Object> result = umService.service(values);
			Member memberSearch = new Member();
			boolean check = (boolean) result.get("result");
			
			response.setContentType("application/json; charset=utf-8");
			PrintWriter out = response.getWriter();
			
			out.println(check);
			out.flush();
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
return null;
	}
}
