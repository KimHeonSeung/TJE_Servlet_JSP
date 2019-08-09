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
@WebServlet("/session_03")
public class SessionServlet_03 extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//session
		//웹 어플이케이션에서 각각의 클라이언트 마다 할당되는 메모리 영역
		//특정 클라이언트의 웹 서버 접속 시점부터 설정 시간동안 유짖되는 영역
		//- 로그인 정보등과 같은 클라이언트와 고유정보를 저장하는 영역
		
		HttpSession session = request.getSession();
		
		//세션의 유효시간 제어
		//setMaxInactiveInterval() 매개변수로
		//세션의 유효 시간의 값을 정의할 수 있습니다.
		//단위 : 초
		session.setMaxInactiveInterval(60);
		
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
