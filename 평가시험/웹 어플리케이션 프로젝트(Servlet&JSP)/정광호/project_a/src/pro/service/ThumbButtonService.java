package pro.service;

import java.sql.Connection;
import java.util.HashMap;

import pro.dao.DetailArticleDAO;
import pro.dao.MemberDAO;
import pro.dao.ThumbDAO;
import pro.model.DetailArticle;
import pro.model.Member;
import pro.model.Thumb;

public class ThumbButtonService implements Service {
	ThumbDAO thumbeDAO = new ThumbDAO();

	public HashMap<String, Object> service(HashMap<String, Object> values) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		int count = 0;
		Connection conn = (Connection) values.get("conn");
		Thumb model = (Thumb) values.get("model");

		
		// 아이디랑 기사 비교해서 좋아요 누른거 없으면 생성
		if(thumbeDAO.selectOneArticleThumb(conn, model) ==null) {
			model.setArticle_thumb(0);
			thumbeDAO.insert(conn, model);
			// 아이디랑 기사 비교해서 있으면 article 추가
		}else if(thumbeDAO.selectOneArticleThumb(conn, model).getArticle_thumb() == 0){
			model.setArticle_thumb(1);
			result.put("result",thumbeDAO.updateArticleThumb(conn, model));
			// 아이디랑 기사 비교해서 있으면 article 감소
		}else if(thumbeDAO.selectOneArticleThumb(conn, model).getArticle_thumb() == 1) {
			model.setArticle_thumb(0);
			result.put("result",thumbeDAO.updateArticleThumb(conn, model));
		}
		
		result.put("thumb_search",thumbeDAO.selectOneArticleThumb(conn, model).getArticle_thumb());
		result.put("thumb_count",thumbeDAO.selectArticleCount(conn) != 0? thumbeDAO.selectArticleCount(conn): count );
		return result;
	}

}
