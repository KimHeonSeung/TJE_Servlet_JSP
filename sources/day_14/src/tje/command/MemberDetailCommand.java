package tje.command;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.*;
import java.sql.*;
import tje.jdbc.util.*;
import tje.model.Comment;
import tje.model.Member;
import tje.model.SimpleArticle;
import tje.service.*;

public class MemberDetailCommand extends Command {
	private String formPage = "/WEB-INF/forms/member_detail.jsp";
	private String errorPage = "/WEB-INF/errors/member_detail.jsp";
	
	private LimitService lService = new LimitService();
	private ArticleSearchService asService = new ArticleSearchService();
	private CommentCountService ccService = new CommentCountService();
	
	protected String processForm(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();

		Member member = (Member)session.getAttribute("login_member");
		String member_id = (String)session.getAttribute("member_id_detail");
		
		session.removeAttribute("member_id_detail");
		
		
		
		if( member_id == null || !member.getMember_id().equals(member_id) ) {
			request.setAttribute("errorMsg", "잘못된 요청이 실행되었습니다.");
			return errorPage;
		}
		
		try (Connection conn = ConnectionProvider.getConnection()) {
			HashMap<String, Object> values = new HashMap<>();
			values.put("conn", conn);
			values.put("member", member);
			HashMap<String, Object> resultMap = lService.service(values);
			ArrayList<SimpleArticle> articleListModel = (ArrayList<SimpleArticle>)resultMap.get("articleList");
			values.put("model", articleListModel);
			HashMap<String, Object> articleIdAndCommentResultMap = ccService.service(values);
			
			
			request.setAttribute("articleIdAndCommentMap", articleIdAndCommentResultMap.get("articleIdAndCommentMap"));
			request.setAttribute("commentList", resultMap.get("commentList"));
			request.setAttribute("articleCount", resultMap.get("articleCount"));
			request.setAttribute("articleList", resultMap.get("articleList"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return formPage;
	}

	// POST 요청일 경우의 처리 로직을 구현하는 메소드
	protected String processSubmit(HttpServletRequest request, HttpServletResponse response) {
		response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
		return null;
	}

}
