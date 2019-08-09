package pro.command;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pro.jdbc.ConnectionProvider;
import pro.model.Member;
import pro.service.MemberIDCheckService;

public class RegistCheckCommand extends Command {	

	private MemberIDCheckService micService = new MemberIDCheckService();	

	protected String processForm(HttpServletRequest request, HttpServletResponse response) {
		return null;
	}

	protected String processSubmit(HttpServletRequest request, HttpServletResponse response) {
		String member_id = request.getParameter("member_id");
		
		Member member = new Member();
		member.setMember_id(member_id);

		boolean result = false;
		try (Connection conn = ConnectionProvider.getConnection()) {

			HashMap<String, Object> values = new HashMap<>();
			values.put("conn", conn);
			values.put("member", member);
			result = (Boolean) micService.service(values).get("result");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		response.setContentType("text/plane;charset=utf-8");
		try {
			PrintWriter out = response.getWriter();
			out.println(result);
			out.flush();
			out.close();
		} catch (IOException e) {			
			e.printStackTrace();
		}

		return null;
	}

	
}
