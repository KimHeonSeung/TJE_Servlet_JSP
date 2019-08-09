package tje.jdbc;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

public class JDBCDriverLoader extends HttpServlet {
	
	// 서버가 구동될 때 단 한번만 만들어지게 해서 실행되게 web.xml에 load on start up 사용
	private static final String JDBC_DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
       
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		String driverClassName = config.getServletContext().getInitParameter("JDBC_DRIVER_CLASS_NAME");
		
		try {
			Class.forName(driverClassName);
			System.out.println("드라이버 클래스 로드 성공");
			
			// 드라이버 클래스가 로딩이 됐을때만 아래 과정을 실행할 것이다.
			JDBCInfo info = new JDBCInfo(config.getServletContext().getInitParameter("JDBC_URL"),
					config.getServletContext().getInitParameter("JDBC_USER"),
					config.getServletContext().getInitParameter("JDBC_PASSWORD"));
			
			config.getServletContext().setAttribute("jdbc_info", info);
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 클래스 로드 실패");
			e.printStackTrace();
		}
		super.init(config);
	}
	


}
