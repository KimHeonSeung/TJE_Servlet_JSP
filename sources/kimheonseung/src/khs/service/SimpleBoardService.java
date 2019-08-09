package khs.service;

import java.sql.*;
import java.util.*;
import khs.dao.*;
import khs.model.*;

public class SimpleBoardService implements Service {
	private SimpleBoardDAO simpleBoardDAO = new SimpleBoardDAO();
	
	public HashMap<String, Object> service(HashMap<String, Object> values) {
		HashMap<String, Object> result = new HashMap<>();
		Connection conn = (Connection)values.get("conn");
		int board_id = (int)values.get("board_id");
		
		result.put("simpleBoard", simpleBoardDAO.selectSimpleBoard(conn, board_id));
		result.put("result", result.get("simpleBoard") != null);
		
		return result;
	}
}







