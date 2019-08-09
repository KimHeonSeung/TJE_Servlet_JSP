package tje.servlet_jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.dbcp.dbcp2.PStmtKey;

import com.sun.org.apache.bcel.internal.generic.Type;

@WebServlet(name = "MemberForm", urlPatterns = { "/memberForm" })
public class MemberServlet extends HttpServlet {
	
	private static final String path="WEB-INF/forms/memberForm.html";
	
	private static final String jdbc_driver="com.mysql.cj.jdbc.Driver";
	private static final String jdbc_url="jdbc:mysql://localhost:3306/test?serverTimezone=UTC";
	private static final String jdbc_id="root";
	private static final String jdbc_password="SystemManager304";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		
		String id=request.getParameter("id").trim();
		String password=request.getParameter("pw").trim();
		String name=request.getParameter("name").trim();
		String age=request.getParameter("age").trim();
		String tel=request.getParameter("tel").trim();
		String address=request.getParameter("address").trim();
		
		int nAge=Integer.parseInt(age);
		
		try {
			Connection conn=DriverManager.getConnection(jdbc_url,jdbc_id,jdbc_password);
			String sql="insert into member values(?,?,?,?,?,?)";
			PreparedStatement pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			pstmt.setString(3, name);
			pstmt.setInt(4, nAge);
			
			if(tel.equals("")) {
				pstmt.setNull(5, Types.NULL);
			}else {
				pstmt.setString(5, tel);
			}
			
			if(address.equals("")) {
				pstmt.setNull(6, Types.NULL);
			}else {
				pstmt.setString(6, address);
			}
			
			
			int r=pstmt.executeUpdate();
			
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out=response.getWriter();
			
			if(r==1) {
				System.out.println("input success");
				out.println("<h2>회원가입 성공</h2>");
			}else {
				System.out.println("input fail");
				out.println("<h2>회원가입 실패</h2>");
			}
			
			pstmt.close();
			conn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
