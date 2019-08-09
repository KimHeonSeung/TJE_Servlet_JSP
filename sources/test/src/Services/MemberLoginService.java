package Services;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tje.jdbc.JDBCInfo;

public class MemberLoginService extends Service {
	private String getPage = "/WEB-INF/forms/loginForm.html";
	private String postPage = "/WEB-INF/submits/loginResult.jsp";
	
	
	protected String processGet(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		boolean isLogin = false;
		if( session != null && session.getAttribute("login_id") != null ) {
			isLogin = true;
		}
		if( isLogin ) {
			getPage = "/WEB-INF/errors/loginError.html";
		}
		return getPage;
	}
	
	protected String processPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		response.setContentType("text/html;charset=utf-8");
		
		String id = request.getParameter("id").trim();
		String password = request.getParameter("password").trim();
		
		boolean isLogin = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		JDBCInfo info = (JDBCInfo)request.getServletContext().getAttribute("jdbc_info");
		
		try {
			conn = DriverManager.getConnection(info.getUrl(), info.getUser(), info.getPassword());
			String query = "select * from member where id = ? and password = ?";
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			rs = pstmt.executeQuery();
			// rs.next()가 true면 하나가 조회되었다는 의미.
			// 그럼 로그인 성공여부인 isLogin에 true를 넣어준다.
			if( rs.next() ) {isLogin = true;}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("isLogin", isLogin);
		try {
			// 커넥션, 스테이트먼트, 리절트셋 널이 아닌경우 닫아준다.
			if( conn != null ) {conn.close();}
			if( pstmt != null ) {pstmt.close();}
			if( rs != null ) {rs.close();}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		if( isLogin ) { // 로그인 성공했을 때,
			// 현재 로그인된 사용자의 id를 세션의 login_id 속성에 저장
			HttpSession session = request.getSession();
			session.setAttribute("login_id", id);
			
			// 현재 웹 서버(서블릿 컨테이너)에 로그인된 사용자의 수를 증가
			ServletContext application = request.getServletContext();
			
			// application에 동기화는 권장되지 않지만
			// 카운팅을 위해 어쩔수없이 사용한다.
			synchronized (application) {
				// login_member_count 값이 null 이란 뜻은, 아직 아무도 로그인 되어있지 않다는 뜻.
				if(application.getAttribute("login_member_count") == null) {
					application.setAttribute("login_member_count", 1);
				} else {
					Integer count = (Integer)application.getAttribute("login_member_count");
					application.setAttribute("login_member_count", count+1);
				}
			}
		}
		return postPage;
	}
}
