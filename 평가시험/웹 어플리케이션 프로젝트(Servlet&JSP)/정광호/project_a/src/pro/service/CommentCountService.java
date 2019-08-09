package pro.service;

import java.sql.Connection;
import java.util.HashMap;

import pro.dao.CommentDAO;
import pro.model.Comment;

public class CommentCountService implements Service {
	private CommentDAO commentDAO = new CommentDAO();
	
	public HashMap<String, Object> service(HashMap<String, Object> values) {
		HashMap<String, Object> result = new HashMap<>();
		
		Connection conn = (Connection)values.get("conn");
		Comment model = (Comment)values.get("model");
		
		result.put("commentSize", 
				commentDAO.selectCount(conn, model));
						
		return result;
	}
}







