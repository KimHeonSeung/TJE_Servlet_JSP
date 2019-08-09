package pro.command;

import java.io.PrintWriter;
import java.sql.Connection;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import pro.jdbc.ConnectionProvider;
import pro.model.DetailArticle;
import pro.model.Member;
import pro.service.ArticleInsertService;

public class WriteArticleCommand extends Command {
	private String formPage = "/WEB-INF/iframe/write_article.jsp";
	private String submitPage = "/WEB-INF/submits/write_article.jsp";
	private String errorPage = "/WEB-INF/errors/write_article.jsp";

	private ArticleInsertService aiService = new ArticleInsertService();

	protected String processForm(HttpServletRequest request, HttpServletResponse response) {
		return formPage;
	}

	protected String processSubmit(HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		Member member = (Member)session.getAttribute("login_member");
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");		
		
		DetailArticle model = new DetailArticle(0, 
				member.getMember_id(), 
				null, 
				title, content, 
				null, null, 0);
		
		try (Connection conn = ConnectionProvider.getConnection()) {
			
			HashMap<String, Object> values = new HashMap<>();
			values.put("conn", conn);
			values.put("model", model);
			
			HashMap<String, Object> resultMap = aiService.service(values);
			request.setAttribute("result", resultMap.get("result"));
			
			response.setContentType("application/json; charset=utf-8");
			PrintWriter out = response.getWriter();
			
			out.println(resultMap.get("result"));
			out.flush();
			out.close();

			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
}
