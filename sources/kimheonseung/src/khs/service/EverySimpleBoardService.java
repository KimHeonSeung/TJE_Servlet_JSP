package khs.service;

import java.sql.*;
import java.util.*;
import khs.dao.*;
import khs.model.*;

public class EverySimpleBoardService implements Service {
	private SimpleBoardsDAO simpleBoardsDAO = new SimpleBoardsDAO();
	
	public HashMap<String, Object> service(HashMap<String, Object> values) {
		HashMap<String, Object> result = new HashMap<>();
		Connection conn = (Connection)values.get("conn");
		int board_id = (int)values.get("board_id");
		int page = (int)values.get("page");
		
		result.put("thisBoardList", simpleBoardsDAO.selectThisBoardList(conn, board_id, page));
		
		return result;
	}
}






