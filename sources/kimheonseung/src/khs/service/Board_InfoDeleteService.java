package khs.service;

import java.sql.*;
import java.util.*;
import khs.dao.*;
import khs.model.*;

public class Board_InfoDeleteService implements Service {
	private Board_InfoDAO board_InfoDAO = new Board_InfoDAO();
	
	public HashMap<String, Object> service(HashMap<String, Object> values) {
		HashMap<String, Object> result = new HashMap<>();
		Connection conn = (Connection)values.get("conn");
		Board_Info model = (Board_Info)values.get("model");
		
		result.put("result", board_InfoDAO.delete(conn, model));
		
		return result;
	}
}







