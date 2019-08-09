package tje.member.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/member_main")
public class MemberMainServlet extends HttpServlet {
	private static final String path="WEB-INF/pages/main.html";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		
		// 아래의 기능이 동작할 수 있도록 서블릿과 HTML 파일을 완성하세요.
		// (회원목록보기/로그아웃은 로그인된 클라이언트만 가능)
		// (회원가입/로그인은 로그인이 되지 않은 클라이언트만 가능)
		// (로그인된 클라이언트는 메인화면에서 현재 접속된 클라이언트의 
		//  인원수를 확인할 수 있음)
		
		HttpSession session=request.getSession();
		
		String id=(String) session.getAttribute("login_id");
		String name=(String) session.getAttribute("login_name");
		
		ServletContext ctx=request.getServletContext();
		
		int currentUsers= (int) ctx.getAttribute("currentUsers");
		
		String cntVisible="";
		String listVisible="";
		String registVisible="";
		String loginVisible="";
		String logoutVisible="";
		
		if(id==null) {
			cntVisible="style='display:none'";
			listVisible="style='display:none'";
			logoutVisible="style='display:none'";
		}else {
			registVisible="style='display:none'";
			loginVisible="style='display:none'";
		}
		
		out.println("<h2>메인 화면</h2>");
		out.println("<h3 "+cntVisible+">현재 접속된 클라이언트의 수 : "+currentUsers+"</h3>");
		out.println("<ul>");
		out.println("<li "+listVisible+"><a href=\"./member_select\">회원목록 보기</a></li>");
		out.println("<li "+registVisible+"><a href=\"./member_regist\">회원가입</a></li>");
		out.println("<li "+loginVisible+"><a href=\"./member_Login\">로그인</a></li>");
		out.println("<li "+logoutVisible+"><a href=\"./member_Logout\">로그아웃</a></li>");
		out.println("</ul>");
	}

}
