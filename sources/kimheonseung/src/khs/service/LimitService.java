package khs.service;

import java.sql.*;
import java.util.*;
import khs.dao.*;
import khs.model.*;

public class LimitService implements Service {
	private SimpleBoardWithUserDAO simpleBoardWithUserDAO = new SimpleBoardWithUserDAO();
	//private CommentDAO commentDAO = new CommentDAO();
	
	public HashMap<String, Object> service(HashMap<String, Object> values) {
		HashMap<String, Object> result = new HashMap<>();
		Connection conn = (Connection)values.get("conn");
		User user = (User)values.get("user");
		
		//result.put("commentList", commentDAO.selectCommentByMemberId(conn, member));
		result.put("articleList", simpleBoardWithUserDAO.selectLimit(conn, user));
		//result.put("articleCount", simpleArticleDAO.selectCount(conn));
		
		return result;
	}
}







