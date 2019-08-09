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

public class ArticleDeleteCommand extends Command {
	private String formPage = "/WEB-INF/forms/article_delete.jsp";
	private String submitPage = "/WEB-INF/submits/article_delete.jsp";
	private String errorPage = "/WEB-INF/errors/article_delete.jsp";

	private ArticleDeleteService adService = new ArticleDeleteService();

	protected String processForm(HttpServletRequest request, HttpServletResponse response) {
		String article_id = request.getParameter("article_id");
		request.setAttribute("article_id", article_id);
		return formPage;
	}

	// POST 요청일 경우의 처리 로직을 구현하는 메소드
	protected String processSubmit(HttpServletRequest request, HttpServletResponse response) {
		String strArticle_id = request.getParameter("article_id");
		int article_id = 0;
		
		try {
			article_id = Integer.parseInt(strArticle_id);
		} catch (Exception e) {
			request.setAttribute("errorMsg", "게시글 삭제 과정에서 문제가 발생했습니다. (article_id의 형변환)");
			return errorPage;
		}
		
		SimpleArticle model = new SimpleArticle(article_id, null, null, null, null, 0);
		
		try (Connection conn = ConnectionProvider.getConnection()) {
			HashMap<String, Object> values = new HashMap<>();
			values.put("conn", conn);
			values.put("model", model);
			HashMap<String, Object> resultMap = adService.service(values);
			
			if(!(boolean)resultMap.get("result")) {
				request.setAttribute("errorMsg", "게시글 삭제 과정에서 문제가 발생했습니다. (관리자에게 문의하세요.)");
				return errorPage;
			};
		} catch (Exception e) {
			e.printStackTrace();
		}
		return submitPage;
	}
}
