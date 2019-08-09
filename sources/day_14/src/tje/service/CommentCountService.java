package tje.service;

import java.sql.*;
import java.util.*;
import tje.dao.*;
import tje.model.*;

public class CommentCountService implements Service {
	private CommentDAO commentDAO = new CommentDAO();
	
	public HashMap<String, Object> service(HashMap<String, Object> values) {
		HashMap<String, Object> result = new HashMap<>();
		Connection conn = (Connection)values.get("conn");
		ArrayList<SimpleArticle> articleModel = (ArrayList<SimpleArticle>)values.get("model");
		// article_id를 키값으로 갖고 그에 대한 comment 갯수를 밸류로 갖는 해쉬맵
		HashMap<Integer, Object> articleIdAndCommentMap = new HashMap<>();
		
		for(SimpleArticle sa : articleModel) {
			int article_id = sa.getArticle_id();
			Comment comment = new Comment(0, article_id, null, null, null);
			// 모든 정보가 담긴 comment 갯수 반환하여 articleIdAndCommentMap에 넣어준다.
			Integer comment_count = commentDAO.selectCount(conn, comment);
			articleIdAndCommentMap.put(article_id, comment_count);
		}
		
		result.put("articleIdAndCommentMap", articleIdAndCommentMap);
		return result;
	}
}







