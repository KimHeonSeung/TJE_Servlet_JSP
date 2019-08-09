package pro.service;

import java.sql.Connection;
import java.util.HashMap;

import pro.dao.DetailArticleDAO;
import pro.model.DetailArticle;

public class DetailArticleUpdateReadCountService implements Service {
	private DetailArticleDAO detailArticleDAO = new DetailArticleDAO();
	
	public HashMap<String, Object> service(HashMap<String, Object> values) {
		HashMap<String, Object> result = new HashMap<>();
		
		Connection conn = (Connection)values.get("conn");
		DetailArticle model = (DetailArticle)values.get("model");
				
		result.put("result",
				detailArticleDAO.updateReadCount(conn, model));
				
		return result;
	}
}







