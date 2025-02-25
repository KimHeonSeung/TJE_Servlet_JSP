package khs.service;

import java.sql.*;
import java.util.*;
import khs.dao.*;
import khs.model.*;

public class SimpleBoardsPagingService implements Service {
	private SimpleBoardsDAO simpleBoardsDAO = new SimpleBoardsDAO();
	
	public HashMap<String, Object> service(HashMap<String, Object> values) {
		HashMap<String, Object> result = new HashMap<>();
		Connection conn = (Connection)values.get("conn");
		User user = (User)values.get("user");
		String user_id = user.getUser_id();
		int page = (int)values.get("page");
		
		result.put("totalCount", simpleBoardsDAO.selectSimpleBoardCount(conn, user_id));
		result.put("pagingList", simpleBoardsDAO.selectSimpleBoardPaging(conn, user_id, page));
		
		return result;
	}
}







