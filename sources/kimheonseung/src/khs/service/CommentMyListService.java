package khs.service;

import java.sql.*;
import java.util.*;
import khs.dao.*;
import khs.model.*;

public class CommentMyListService implements Service {
	private CommentsDAO commentsDAO = new CommentsDAO();
	
	public HashMap<String, Object> service(HashMap<String, Object> values) {
		HashMap<String, Object> result = new HashMap<>();
		
		Connection conn = (Connection)values.get("conn");
		User user = (User)values.get("user");
		
		result.put("commentMyList", 
				commentsDAO.selectMy(conn, user));		
				
		return result;
	}
}







