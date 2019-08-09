package khs.service;

import java.sql.*;
import java.util.*;
import khs.dao.*;
import khs.model.*;

public class DetailBoardService implements Service {
	private DetailBoardDAO detailBoardDAO = new DetailBoardDAO();
	
	public HashMap<String, Object> service(HashMap<String, Object> values) {
		HashMap<String, Object> result = new HashMap<>();
		Connection conn = (Connection)values.get("conn");
		int article_num = (int)values.get("article_num");
		
		result.put("detailBoard", detailBoardDAO.select(conn, article_num));
		result.put("result", result.get("detailBoard") != null);
		
		return result;
	}
}







