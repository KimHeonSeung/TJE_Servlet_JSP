
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
import pro.model.Likepage;
import pro.model.Member;
import pro.service.LikePageService;
import pro.service.LoginService;

public class LikePageCommand extends Command {
	private String formPage = "/WEB-INF/iframe/likeList.jsp";

	private LikePageService lpService = new LikePageService();

	protected String processForm(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		try (Connection conn = ConnectionProvider.getConnection();) {
			HashMap<String, Object> values = new HashMap<String, Object>();
			Member member = (Member) session.getAttribute("login_member");
			Likepage likepage = new Likepage();

			likepage.setMember_id(member.getMember_id());

			values.put("conn", conn);
			values.put("likepage", likepage);
			values.put("type", "search");

			HashMap<String, Object> likepagesearch = lpService.service(values);
			ArrayList<Likepage> Likepage_list = (ArrayList<Likepage>) likepagesearch.get("likepagesearch");
			request.setAttribute("likepagesearch", Likepage_list);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return formPage;
	}

	protected String processSubmit(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		String title = request.getParameter("title");
		String url = request.getParameter("url");

		Likepage likepage = new Likepage();
		Member member = (Member) session.getAttribute("login_member");

		try (Connection conn = ConnectionProvider.getConnection();) {

			HashMap<String, Object> values = new HashMap<String, Object>();

			likepage.setMember_id(member.getMember_id());
			likepage.setTitle(title);
			likepage.setLikepage(url);

			values.put("conn", conn);
			values.put("likepage", likepage);
			values.put("type", "insert");

			HashMap<String, Object> result = lpService.service(values);

			boolean login = (boolean) result.get("result");

			response.setContentType("application/json; charset=utf-8");
			PrintWriter out = response.getWriter();

			out.println(login);
			out.flush();
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}
