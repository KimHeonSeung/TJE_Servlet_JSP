package tje.servlet_jdbc;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Logout")
public class LogoutServlet extends HttpServlet {
	
	private static final String path="WEB-INF/forms/logout.html";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesstion=request.getSession();
		
		String id=(String) sesstion.getAttribute("login_id");
		String name=(String) sesstion.getAttribute("login_name");
		
		if(id==null) {
			response.setContentType("text/html;charset=utf-8");
			java.io.PrintWriter out = response.getWriter();
			out.println("<h3>로그인을 수행해야만 로그아웃을 처리할 수 있습니다.</h3>");
			out.flush();
			return;
		}
		
		RequestDispatcher rd=request.getRequestDispatcher(path);
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		
		String id=(String) session.getAttribute("login_id");
		String name=(String) session.getAttribute("login_name");
		
		session.removeAttribute("login_id");
		session.removeAttribute("login_name");
		
		response.setContentType("text/html;charset=utf-8");
		java.io.PrintWriter out = response.getWriter();
		
		out.println("<h2>로그아웃</h2>");
		out.println("<h4>" + name + " 님의 로그아웃 요청이 처리되었습니다.</h4>");
		
	}

}
