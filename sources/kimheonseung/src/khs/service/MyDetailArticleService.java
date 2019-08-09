package khs.service;

import java.sql.*;
import java.util.*;
import khs.dao.*;
import khs.model.*;

public class MyDetailArticleService implements Service {
	private SimpleBoardsDAO simpleBoardsDAO = new SimpleBoardsDAO();
	
	public HashMap<String, Object> service(HashMap<String, Object> values) {
		HashMap<String, Object> result = new HashMap<>();
		Connection conn = (Connection)values.get("conn");
		User user = (User)values.get("user");
		
		result.put("result", simpleBoardsDAO.selectMySimpleBoardWithName(conn, user));
		
		return result;
	}
}







