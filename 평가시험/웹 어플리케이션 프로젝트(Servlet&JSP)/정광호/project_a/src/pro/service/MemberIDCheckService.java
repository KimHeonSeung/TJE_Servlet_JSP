package pro.service;

import java.sql.Connection;
import java.util.HashMap;

import pro.dao.MemberDAO;
import pro.model.Member;

public class MemberIDCheckService implements Service {
	private MemberDAO memberDAO = new MemberDAO();
	
	public HashMap<String, Object> service(HashMap<String, Object> values) {
		HashMap<String, Object> result = new HashMap<>();
		Connection conn = (Connection)values.get("conn");
		Member member = (Member)values.get("member");
				
		result.put("searchedMember", 
				memberDAO.selectOne(conn, member));
		result.put("result", 
				result.get("searchedMember") != null);
		
		return result;
	}
}







