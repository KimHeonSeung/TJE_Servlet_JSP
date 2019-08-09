package tje.command;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.*;
import java.sql.*;
import tje.jdbc.util.*;
import tje.model.*;
import tje.service.*;

public class ArticleUpdateCommand extends Command {
	private String formPage = "/WEB-INF/forms/article_update.jsp";
	private String submitPage = "/WEB-INF/submits/article_update.jsp";
	private String errorPage = "/WEB-INF/errors/article_update.jsp";

	private ArticleUpdateService auService = new ArticleUpdateService();

	protected String processForm(HttpServletRequest request, HttpServletResponse response) {
		String article_id = request.getParameter("article_id");
		String member_id = request.getParameter("member_id");
		String name = request.getParameter("name");
		String title = request.getParameter("title");
		String write_time = request.getParameter("write_timeString");
		String read_count = request.getParameter("read_count");
		String content = request.getParameter("content");
		request.setAttribute("article_id", article_id);
		request.setAttribute("member_id", member_id);
		request.setAttribute("name", name);
		request.setAttribute("title", title);
		request.setAttribute("write_time", write_time);
		request.setAttribute("read_count", read_count);
		request.setAttribute("content", content);
		return formPage;
	}

	// POST 요청일 경우의 처리 로직을 구현하는 메소드
	protected String processSubmit(HttpServletRequest request, HttpServletResponse response) {
		String strArticle_id = request.getParameter("article_id");
		String member_id = request.getParameter("member_id");
		String name = request.getParameter("name");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		Timestamp write_time = strConvertor(request.getParameter("write_time"));
		String strRead_count = request.getParameter("read_count");
		int article_id = 0;
		int read_count = 0;
		try {
			article_id = Integer.parseInt(strArticle_id);
			read_count = Integer.parseInt(strRead_count);
		} catch (Exception e) {
			request.setAttribute("errorMsg", "게시글 수정 과정에서 문제가 발생했습니다. (article_id의 형변환)");
			return errorPage;
		}
		
		
		DetailArticle model = new DetailArticle(article_id, member_id, name, title, content, write_time, null, read_count);
		System.out.println(model.getContent());
		System.out.println(model.getTitle());
		try (Connection conn = ConnectionProvider.getConnection()) {
			HashMap<String, Object> values = new HashMap<>();
			values.put("conn", conn);
			values.put("model", model);
			HashMap<String, Object> resultMap = auService.service(values);
			
			if(!(boolean)resultMap.get("result")) {
				request.setAttribute("errorMsg", "게시글 수정 과정에서 문제가 발생했습니다. (관리자에게 문의하세요.)");
				return errorPage;
			};
		} catch (Exception e) {
			e.printStackTrace();
		}
		return submitPage;
	}
	
	
	
	private Timestamp strConvertor(String source) {
		Timestamp date = null;
		date = Timestamp.valueOf(source);
		return date;
	}
}
