package khs.service;

import java.sql.*;
import java.util.*;
import khs.dao.*;
import khs.model.*;

public class CommentInsertService implements Service {
	private CommentsDAO commentsDAO = new CommentsDAO();
	
	public HashMap<String, Object> service(HashMap<String, Object> values) {
		HashMap<String, Object> result = new HashMap<>();
		
		Connection conn = (Connection)values.get("conn");
		Comments model = (Comments)values.get("model");
		
		result.put("result", 
				commentsDAO.insert(conn, model));
		result.put("id", 
				commentsDAO.selectLastInsertID(conn));
						
		return result;
	}
}







