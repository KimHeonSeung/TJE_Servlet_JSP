package khs.service;

import java.sql.*;
import java.util.*;
import khs.dao.*;
import khs.model.*;

public class CommentDeleteService implements Service {
	private CommentsDAO commentDAO = new CommentsDAO();
	
	public HashMap<String, Object> service(HashMap<String, Object> values) {
		HashMap<String, Object> result = new HashMap<>();
		
		Connection conn = (Connection)values.get("conn");
		Comments model = (Comments)values.get("model");
		
		result.put("result", 
				commentDAO.delete(conn, model));
						
		return result;
	}
}







