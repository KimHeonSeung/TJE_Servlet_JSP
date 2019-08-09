package khs.service;

import java.sql.*;
import java.util.*;
import khs.dao.*;
import khs.model.*;

public class DetailBoardReadCountService implements Service {
	private DetailBoardDAO detailBoardDAO = new DetailBoardDAO();
	
	public HashMap<String, Object> service(HashMap<String, Object> values) {
		HashMap<String, Object> result = new HashMap<>();
		Connection conn = (Connection)values.get("conn");
		DetailBoard detailBoard = (DetailBoard)values.get("detailBoard");
		
		result.put("result_updateReadCount", detailBoardDAO.updateReadCount(conn, detailBoard));
		
		return result;
	}
}







