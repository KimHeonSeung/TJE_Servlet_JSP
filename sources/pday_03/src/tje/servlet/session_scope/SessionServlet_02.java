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
@WebServlet("/session_02")
public class SessionServlet_02 extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//session
		//웹 어플이케이션에서 각각의 클라이언트 마다 할당되는 메모리 영역
		//특정 클라이언트의 웹 서버 접속 시점부터 설정 시간동안 유짖되는 영역
		//- 로그인 정보등과 같은 클라이언트와 고유정보를 저장하는 영역
		
		HttpSession session = request.getSession();
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy년 MM월 dd일 HH:mm:ss");
		Date ct=new Date(session.getCreationTime());
		Date lat=new Date(session.getLastAccessedTime());
		
		//세션 최대 유효 시간
		int maxInterval=session.getMaxInactiveInterval();
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		
		out.println("<h3>세션 생성시간 : ");
		out.println(sdf.format(ct)+"</h3>");
		
		out.println("<h3>세션 마지막 접속 시간 : ");
		out.println(sdf.format(lat)+"</h3>");
		
		out.println("<h3>세션 유효 시간 : ");
		out.println(maxInterval+"</h3>");
	}

}
