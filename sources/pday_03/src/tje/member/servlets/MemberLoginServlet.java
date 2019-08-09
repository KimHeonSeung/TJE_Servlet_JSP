package tje.member.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/member_Login")
public class MemberLoginServlet extends HttpServlet {
	
	private static final String path="WEB-INF/pages/login.html";
	
	private static final String jdbc_driver="com.mysql.cj.jdbc.Driver";
	private static final String jdbc_url="jdbc:mysql://localhost:3306/test?serverTimezone=UTC";
	private static final String jdbc_id="root";
	private static final String jdbc_password="SystemManager304";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		
		Cookie[] cookies=request.getCookies();
		String save_id=null;
		
		for (int i=0;cookies!=null&&i<cookies.length;i++) {
			if(cookies[i].getName().equals("save_id"))
				save_id=cookies[i].getValue();
		}
		
		PrintWriter out=response.getWriter();
		StringBuilder sb=new StringBuilder();
		
		sb.append("<form action=\"./member_Login\" method=\"post\">");
		sb.append("<table>");
		sb.append("<tr>");
		sb.append("<td>id</td>");
		if(save_id==null) {
			sb.append("<td><input type=\"text\" name=\"id\" required=\"required\"></td>");
		}else {
			sb.append("<td><input type=\"text\" name=\"id\" value='"+save_id+"' required=\"required\"></td>");
		}
		sb.append("</tr>");
		sb.append("<tr>");
		sb.append("<td>password</td>");
		sb.append("<td><input type=\"password\" name=\"pw\" required=\"required\"></td>");
		sb.append("</tr>");
		sb.append("<tr>");
		sb.append("<td colspan=\"2\"><input type=\"submit\" value=\"로그인\"></td>");
		sb.append("</tr>");
		sb.append("</table>");
		sb.append("</form>");
		
		out.println(sb);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		try {
			Class.forName(jdbc_driver);
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 클래스를 찾을 수 없습니다.");
		}
		
		String name=null;
		
		String id=request.getParameter("id").trim();
		String password=request.getParameter("pw").trim();
		String save_id=request.getParameter("save_id");
		
		try {
			Connection conn=DriverManager.getConnection(jdbc_url,jdbc_id,jdbc_password);
			String sql="select name from member where id=? and password=?";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			
			ResultSet rs=pstmt.executeQuery();
			
			if(rs.next()) {
				name=rs.getString("name");
			}
			
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out=response.getWriter();
			
			Cookie cookie=null;
			
			if(name==null) {
				out.println("<h2>로그인 실패</h2>");
				out.println("<a href='./member_main'>메인 페이지로</a>");
				
				cookie=new Cookie("save_id", "");
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}else {
				out.println("<h2>로그인 성공</h2>");
				out.println("<h4>"+name+"님 환영합니다.</h4>");
				out.println("<a href='./member_main'>메인 페이지로</a>");
				
				cookie=new Cookie("save_id", id);
				response.addCookie(cookie);
				
				HttpSession sesstion=request.getSession();
				sesstion.setAttribute("login_id", id);
				sesstion.setAttribute("login_name", name);
			}
			
			rs.close();
			pstmt.close();
			conn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
