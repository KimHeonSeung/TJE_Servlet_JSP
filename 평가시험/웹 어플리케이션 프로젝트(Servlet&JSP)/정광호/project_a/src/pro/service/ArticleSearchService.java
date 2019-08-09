package pro.service;

import java.sql.Connection;
import java.util.HashMap;

import pro.dao.SimpleArticleDAO;

public class ArticleSearchService implements Service {
	private SimpleArticleDAO simpleArticleDAO = new SimpleArticleDAO();
	
	public HashMap<String, Object> service(HashMap<String, Object> values) {
		HashMap<String, Object> result = new HashMap<>();
		
		Connection conn = (Connection)values.get("conn");
		String searchItem = (String)values.get("searchItem");
		String searchValue = (String)values.get("searchValue");
		
		result.put("articleSearch", 
			simpleArticleDAO.select(conn, searchItem, searchValue));
		
		return result;
	}
}







