package tje.servlet.session_scope;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SessionServlet
 */
@WebServlet("/session_01")
public class SessionServlet_01 extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//session
		//웹 어플이케이션에서 각각의 클라이언트 마다 할당되는 메모리 영역
		//특정 클라이언트의 웹 서버 접속 시점부터 설정 시간동안 유짖되는 영역
		//- 로그인 정보등과 같은 클라이언트와 고유정보를 저장하는 영역
		
		HttpSession session = request.getSession();
		
		Integer session_cnt=(Integer) session.getAttribute("count");
		
		if(session_cnt==null) {
			session_cnt=1;
		}else {
			session_cnt++;
		}
		
		session.setAttribute("count", session_cnt);
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		
		out.println("<h3>Count : "+session_cnt+"</h3>");
	}

}
