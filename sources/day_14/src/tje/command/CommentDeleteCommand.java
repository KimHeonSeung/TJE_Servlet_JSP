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

public class CommentDeleteCommand extends Command {
	private String formPage = "/WEB-INF/forms/comment_delete.jsp";
	private String submitPage = "/WEB-INF/submits/comment_delete.jsp";
	private String errorPage = "/WEB-INF/errors/comment_delete.jsp";

	private CommentDeleteService cdService = new CommentDeleteService();

	protected String processForm(HttpServletRequest request, HttpServletResponse response) {
		String comment_id = request.getParameter("comment_id");
		request.setAttribute("comment_id", comment_id);
		return formPage;
	}

	// POST 요청일 경우의 처리 로직을 구현하는 메소드
	protected String processSubmit(HttpServletRequest request, HttpServletResponse response) {
		String strComment_id = request.getParameter("comment_id");
		int comment_id = 0;
		
		try {
			comment_id = Integer.parseInt(strComment_id);
		} catch (Exception e) {
			request.setAttribute("errorMsg", "댓글 삭제 과정에서 문제가 발생했습니다. (comment_id의 형변환)");
			return errorPage;
		}
		
		Comment model = new Comment(comment_id, 0, null, null, null);
		
		try (Connection conn = ConnectionProvider.getConnection()) {
			HashMap<String, Object> values = new HashMap<>();
			values.put("conn", conn);
			values.put("model", model);
			HashMap<String, Object> resultMap = cdService.service(values);
			
			if(!(boolean)resultMap.get("result")) {
				request.setAttribute("errorMsg", "댓글 삭제 과정에서 문제가 발생했습니다. (관리자에게 문의하세요.)");
				return errorPage;
			};
		} catch (Exception e) {
			e.printStackTrace();
		}
		return submitPage;
	}
}
