package pro.service;

import java.sql.Connection;
import java.util.HashMap;

import pro.dao.MemberDAO;
import pro.model.Member;

public class LogoutService implements Service {
	MemberDAO memberDAO = new MemberDAO();

	public HashMap<String, Object> service(HashMap<String, Object> values) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		Connection conn = (Connection) values.get("conn");
		Member member = (Member) values.get("member");
		String type = (String) values.get("type");

		if(type.equals("last_access_time")) {
		result.put("result", memberDAO.updateLastAT(conn,member));
		}
		return result;
	}

}
