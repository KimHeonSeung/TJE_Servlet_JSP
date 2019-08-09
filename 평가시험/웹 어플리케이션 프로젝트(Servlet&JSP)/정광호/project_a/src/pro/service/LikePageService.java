package pro.service;

import java.sql.Connection;
import java.util.HashMap;

import pro.dao.LikepageDAO;
import pro.dao.MemberDAO;
import pro.model.Likepage;
import pro.model.Member;

public class LikePageService implements Service {

	LikepageDAO likepageDAO = new LikepageDAO();

	public HashMap<String, Object> service(HashMap<String, Object> values) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		Connection conn = (Connection) values.get("conn");
		Likepage likepage = (Likepage) values.get("likepage");
		String type = (String) values.get("type");

	

		if (type != null & type.equals("insert")) {
			result.put("result", likepageDAO.insert(conn, likepage));
		}
		if (type != null & type.equals("search")) {
			result.put("likepagesearch", likepageDAO.selectpage(conn, likepage));
		}

		return result;
	}

}
