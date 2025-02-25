package khs.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.*;
import java.sql.*;
import khs.jdbc.util.*;
import khs.model.*;
import khs.service.*;

public class MyDetailCommentCommand extends Command {
	private String formPage = "/WEB-INF/forms/my_detail_comment.jsp";
	private String submitPage = "/WEB-INF/submits/my_detail_comment.jsp";
	private String errorPage = "/WEB-INF/errors/my_detail_comment.jsp";

	private CommentMyPagingService cmpService = new CommentMyPagingService();
	private CommentMyListService cmlService = new CommentMyListService();
	
	protected String processForm(HttpServletRequest request, HttpServletResponse response) {
		String user_id = request.getParameter("user_id");
		User user = new User();
		user.setUser_id(user_id);
		
		// 페이징
		int totalCount = 0;
		int page = 1;
		if( request.getParameter("page") != null ) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		//
		
		try (Connection conn = ConnectionProvider.getConnection()) {

			HashMap<String, Object> values = new HashMap<>();
			values.put("conn", conn);
			values.put("user", user);
			values.put("page", page);
			
			// 페이징
			HashMap<String, Object> resultPagingMap = cmpService.service(values);
			totalCount = (int)resultPagingMap.get("totalCount");
			ArrayList<Comments> commentMyList = (ArrayList<Comments>) resultPagingMap.get("pagingList");
			//
			
			request.setAttribute("commentMyList", commentMyList);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 페이징
		Paging paging = new Paging();
		paging.setPage(page);
		paging.setTotalCount(totalCount);
		request.setAttribute("paging", paging);
		//
		
		return formPage;
	}

	// POST 요청일 경우의 처리 로직을 구현하는 메소드
	protected String processSubmit(HttpServletRequest request, HttpServletResponse response) {
		return null;
	}

}
