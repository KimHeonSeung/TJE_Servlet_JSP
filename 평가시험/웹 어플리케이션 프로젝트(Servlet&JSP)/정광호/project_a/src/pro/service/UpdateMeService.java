package pro.service;

import java.sql.Connection;
import java.util.HashMap;

import pro.dao.MemberDAO;
import pro.model.Member;

public class UpdateMeService implements Service {
	MemberDAO memberDAO = new MemberDAO();

	public HashMap<String, Object> service(HashMap<String, Object> values) {
		HashMap<String, Object> result = new HashMap<String, Object>();
		Connection conn = (Connection) values.get("conn");
		Member member = (Member) values.get("member");
		Member result_member = memberDAO.selectOne(conn, member);
		boolean bl = result_member != null && member != null;
		
		if (bl &&member.getPassword().equals(result_member.getPassword())) {
			result.put("result", memberDAO.selectOne(conn, member) != null);
		}

		return result;
	}

}
