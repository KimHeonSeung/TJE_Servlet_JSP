package pro.service;

import java.sql.Connection;
import java.util.HashMap;

import pro.dao.SimpleArticleDAO;

public class ArticleListService implements Service {
	private SimpleArticleDAO simpleArticleDAO = new SimpleArticleDAO();
	
	public HashMap<String, Object> service(HashMap<String, Object> values) {
		HashMap<String, Object> result = new HashMap<>();
		Connection conn = (Connection)values.get("conn");
		
		result.put("articleList", 
				simpleArticleDAO.selectAll(conn));
		
		return result;
	}
}







