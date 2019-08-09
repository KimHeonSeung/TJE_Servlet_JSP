
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
import pro.model.DetailArticle;
import pro.model.Member;
import pro.model.Thumb;
import pro.service.LoginService;
import pro.service.ThumbButtonService;

public class ThumbButtonCommand extends Command {

	private ThumbButtonService tbService = new ThumbButtonService();

	protected String processForm(HttpServletRequest request, HttpServletResponse response) {

		return null;
	}

	protected String processSubmit(HttpServletRequest request, HttpServletResponse response) {
		String strarticle_id = request.getParameter("article_id");
		HttpSession session = request.getSession(false);
		try (Connection conn = ConnectionProvider.getConnection();) {
			HashMap<String, Object> values = new HashMap<String, Object>();
			Member member = (Member) session.getAttribute("login_member");

			int article_id = 0;

			try {
				article_id = Integer.parseInt(strarticle_id);
			} catch (Exception e) {
				e.printStackTrace();
			}

			Thumb model = new Thumb();
			model.setArticle_id(article_id);
			model.setMember_id(member.getMember_id());

			values.put("conn", conn);
			values.put("model", model);

			HashMap<String, Object> result = tbService.service(values);
			// 증감 결과
			result.get("result");
			result.get("thumb_search");
			
			response.setContentType("application/json; charset=utf-8");
			PrintWriter out = response.getWriter();
			String strResult = String.format("{ \"result\" : \"%s\", \"thumb_search\" : \"%d\" , \"thumb_count\" : \"%d\"}", result.get("result"),
					result.get("thumb_search"),result.get("thumb_count"));
			out.println(strResult);
			out.flush();
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}
