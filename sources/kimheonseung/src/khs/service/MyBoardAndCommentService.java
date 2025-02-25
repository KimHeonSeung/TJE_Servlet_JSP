package khs.service;

import java.sql.*;
import java.util.*;
import khs.dao.*;
import khs.model.*;

public class MyBoardAndCommentService implements Service {
	private CommentsDAO commentDAO = new CommentsDAO();
	
	public HashMap<String, Object> service(HashMap<String, Object> values) {
		HashMap<String, Object> result = new HashMap<>();
		
		Connection conn = (Connection)values.get("conn");
		ArrayList<SimpleBoards> simpleBoardList = (ArrayList<SimpleBoards>)values.get("simpleBoardList");
		HashMap<Integer, Object> articleNumAndCommentUserMap = new HashMap<Integer, Object>();
		
		for(SimpleBoards sbs : simpleBoardList) {
			int article_num = sbs.getArticle_num();
			Comments comment = new Comments(0, article_num, null, null, null, null);
			
			Integer comment_count = commentDAO.selectCount(conn, comment);
			articleNumAndCommentUserMap.put(article_num, comment_count);
			
		}
		result.put("articleNumAndCommentUserMap", articleNumAndCommentUserMap);
		return result;
		
	}
}







