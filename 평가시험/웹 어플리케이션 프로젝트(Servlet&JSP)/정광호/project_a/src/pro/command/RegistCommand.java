
package pro.command;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pro.filter.CookieManager;
import pro.jdbc.ConnectionProvider;
import pro.model.Member;
import pro.service.LoginService;
import pro.service.RegistService;
import pro.service.MemberIDCheckService;

public class RegistCommand extends Command {
	private String formPage = "/WEB-INF/forms/regist.jsp";
	private String submitPage = "/WEB-INF/forms/submit_regist.jsp";
	private String errorPage = "/WEB-INF/errors/regist.jsp";

	private RegistService rService = new RegistService();
	private MemberIDCheckService micService = new MemberIDCheckService();

	protected String processForm(HttpServletRequest request, HttpServletResponse response) {
		return formPage;
	}

	@Override
	protected String processSubmit(HttpServletRequest request, HttpServletResponse response) {

		String member_id = request.getParameter("member_id");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		int gender = intInvertor(request.getParameter("gender"));
		int age = intInvertor(request.getParameter("age"));
		int year = intInvertor(request.getParameter("year"));
		int month = intInvertor(request.getParameter("month"));
		int day = intInvertor(request.getParameter("day"));
		String tel = request.getParameter("tel");
		String address = request.getParameter("address");

		Member member = new Member(member_id, password, name, gender, age, null, tel, address, null, null);
		member.setBirth(year, month, day);

		try (Connection conn = ConnectionProvider.getConnection()) {
			HashMap<String, Object> values = new HashMap<String, Object>();

			member.setMember_id(member_id);
			member.setPassword(password);

			values.put("conn", conn);
			values.put("member", member);

			HashMap<String, Object> result = rService.service(values);
			Member memberSearch = new Member();
			boolean login = (boolean) result.get("result");
			
			request.setAttribute("member", micService.service(values).get("searchedMember"));
			
			if (login) {
				request.setAttribute("errorMSG", "회원가입 성공");
				
			} else {
				request.setAttribute("errorMsg", "회원가입 실패");
				return formPage;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return submitPage;
	}

	private int intInvertor(String str) {
		int result = 0;
		try {
			result = Integer.parseInt(str);
		} catch (Exception e) {
		}

		return result;
	}
}
