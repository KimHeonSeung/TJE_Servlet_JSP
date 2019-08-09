package tje.servlet.session_scope;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SessionServlet
 */
@WebServlet("/session_04")
public class SessionServlet_04 extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		
		//세션의 유효시간 제어
		//web.xml
		//- session-config 태그 내부에
		//	session-timeout 태그 작성하여 시간 지정
		//- session-timeout 태그의 값은 정수를 입력해야만하며,
		//	단위는 분
		//- 모든 세션에 대한 지속 시간을 제어하는 경우에 사용
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy년 MM월 dd일 HH:mm:ss");
		Date ct=new Date(session.getCreationTime());
		Date lat=new Date(session.getLastAccessedTime());
		
		//세션 최대 유효 시간
		int maxInterval=session.getMaxInactiveInterval();
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		
		out.println("<h3>세션 유효 시간 : ");
		out.println(maxInterval+"</h3>");
	}

}
