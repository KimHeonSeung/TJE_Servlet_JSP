package tje.servlet_jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	
	private static final String path="WEB-INF/forms/loginForm.html";
	
	private static final String jdbc_driver="com.mysql.cj.jdbc.Driver";
	private static final String jdbc_url="jdbc:mysql://localhost:3306/test?serverTimezone=UTC";
	private static final String jdbc_id="root";
	private static final String jdbc_password="SystemManager304";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sesstion=request.getSession();
		String id=(String) sesstion.getAttribute("login_id");
		String name=(String) sesstion.getAttribute("login_name");
		
		if(id!=null) {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out=response.getWriter();
			out.println("<h3>"+name+" 님은 로그인 상태입니다.</h3>");
			
			out.flush();
			return;
		}
		
		RequestDispatcher rd=request.getRequestDispatcher(path);
		rd.forward(request, response);
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
			
			if(name==null) {
				out.println("<h2>로그인 실패</h2>");
			}else {
				out.println("<h2>로그인 성공</h2>");
				out.println("<h4>"+name+"님 환영합니다.</h4>");
				
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
