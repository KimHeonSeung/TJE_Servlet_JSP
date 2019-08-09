package tje.servlet.request_scope;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RequestEXServlet
 */
@WebServlet("/request_ex")
public class RequestEXServlet extends HttpServlet {

	
	
	//포워딩을 할 html 파일의 경로
		//포워딩의 경우 '/'->WebContent
		private static final String formPage="/WEB-INF/forms/inputNumberForm.html";
		//post 방식의 요청인 경우
		//포워딩을 통해서 이동할 서블릿 클래스의 url
		private static final String nextPage="/request_ex_plus";
		
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			RequestDispatcher rd=request.getRequestDispatcher(formPage);
			rd.forward(request, response);
		}

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String strNum1=request.getParameter("num1").trim();
			String strNum2=request.getParameter("num2").trim();
			
			boolean checkNumber=true;
			
			int num1=0;
			int num2=0;
			
			try {
				num1=Integer.parseInt(strNum1);
				num2=Integer.parseInt(strNum2);
			} catch (Exception e) {
				// TODO: handle exception
				checkNumber=false;
			}
			
			if(!checkNumber) {
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out=response.getWriter();
				
				out.println("<h2>ERROR</h2>");
				out.println("<h3>숫자 타입을 입력하세요</h3>");
				out.flush();
				
				return;
			}
			
			request.setAttribute("num1", num1);
			request.setAttribute("num2", num2);
			
			RequestDispatcher rd=request.getRequestDispatcher(nextPage);
			rd.forward(request, response);
		}
}
