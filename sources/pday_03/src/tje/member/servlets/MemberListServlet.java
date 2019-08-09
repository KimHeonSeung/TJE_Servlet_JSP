package tje.member.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member_select")
public class MemberListServlet extends HttpServlet {
	
	private static final String jdbc_driver="com.mysql.cj.jdbc.Driver";
	private static final String jdbc_url="jdbc:mysql://localhost:3306/test?serverTimezone=UTC";
	private static final String jdbc_id="root";
	private static final String jdbc_password="SystemManager304";
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Class.forName(jdbc_driver);
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 클래스를 찾을 수 없습니다.");
		}
		
		try {
			Connection conn=DriverManager.getConnection(jdbc_url,jdbc_id,jdbc_password);
			String sql="select * from member";
			PreparedStatement ptstm=conn.prepareStatement(sql);
			ResultSet rs=ptstm.executeQuery();
			
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out=response.getWriter();
			StringBuilder sb=new StringBuilder();
			
			sb.append("<table border='1'>");
			sb.append("<tr>");
			sb.append("<th>id</th>");
			sb.append("<th>password</th>");
			sb.append("<th>name</th>");
			sb.append("<th>age</th>");
			sb.append("<th>tel</th>");
			sb.append("<th>address</th>");
			sb.append("</tr>");
			while(rs.next()) {
				String id=rs.getString(1);
				String password=rs.getString(2);
				String name=rs.getString(3);
				String age=rs.getString(4);
				String tel=rs.getString(5);
				String address=rs.getString(6);
				
				
				sb.append("<tr>");
				sb.append("<td>"+id+"</td>");
				sb.append("<td>"+password+"</td>");
				sb.append("<td>"+name+"</td>");
				sb.append("<td>"+age+"</td>");
				sb.append("<td>"+tel+"</td>");
				sb.append("<td>"+address+"</td>");
				sb.append("</tr>");
				
			}
			
			sb.append("</table></br>");
			sb.append("<a href='./member_main'>메인 페이지로</a>");
			
			out.println(sb);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
