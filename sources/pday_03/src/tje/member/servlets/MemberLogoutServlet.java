package tje.member.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/member_Logout")
public class MemberLogoutServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		
		String id=(String) session.getAttribute("login_id");
		String name=(String) session.getAttribute("login_name");
		
		session.removeAttribute("login_id");
		session.removeAttribute("login_name");
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.println("<h2>로그아웃</h2>");
		out.println("<h4>" + name + " 님의 로그아웃 요청이 처리되었습니다.</h4>");
		out.println("<a href='./member_main'>메인 페이지로</a>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
