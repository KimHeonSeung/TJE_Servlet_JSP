package tje.servlet_application_scope;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/application_02")
public class ApplicationServlet_02 extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ServletContext 영역 (Application 영역) 사용 시 주의사항
		// Application 영역 객체는 모든 클라이언트들이 공유하는 객체
		// 공유되는 특징으로 인해서 값의 수정 시, 올바르게 적용되도록 보장할 수 있어야 함
		// 동기화 처리 필요
		
		// ServletContext 영역 (Application 영역)을 반환받는 방법
		ServletContext application = request.getServletContext();		
		Integer count_app = null;		
		
		//아래의 동기화 코드는 현재 서블릿으로 요청을 보낸 클라이언트들이
		//블럭 상태로 빠지게 만듬
		//만약 다른 서블릿에서 ServletContext 수정하려는 경우
		//동기화 처리를 할 수 없음
		
		// synchronized (this) {
		//  
		// }
		
		// 아래의 동기화 코드는 ServletContext 객체를 사용하여
		// 동기화 처리
		// 모든 서블릿에서 특정 ServletContext의 속성을 제어하려는 경우
		// 반드시 아래와 같이 동기화를 지정해야 함
		synchronized (application) {
			count_app = (Integer)application.getAttribute("count");
			
			if( count_app == null )
				count_app = 1;
			else
				count_app++;
			application.setAttribute("count", count_app);
			
			try {
				Thread.sleep(20000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		
		response.setContentType("text/html;charset=utf-8");
		java.io.PrintWriter out = response.getWriter();
		out.println("<h3>COUNT_APP : " + count_app + "</h3>");
	}

}









