package tje.service;

import java.sql.*;
import java.util.*;
import tje.dao.*;
import tje.model.*;

public class LimitService implements Service {
	private SimpleArticleDAO simpleArticleDAO = new SimpleArticleDAO();
	private CommentDAO commentDAO = new CommentDAO();
	
	public HashMap<String, Object> service(HashMap<String, Object> values) {
		HashMap<String, Object> result = new HashMap<>();
		Connection conn = (Connection)values.get("conn");
		Member member = (Member)values.get("member");
		
		result.put("commentList", commentDAO.selectCommentByMemberId(conn, member));
		result.put("articleList", simpleArticleDAO.selectLimit(conn, member));
		result.put("articleCount", simpleArticleDAO.selectCount(conn));
		
		return result;
	}
}







