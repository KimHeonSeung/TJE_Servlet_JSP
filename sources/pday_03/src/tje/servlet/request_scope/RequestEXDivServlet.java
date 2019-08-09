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
@WebServlet("/request_ex_div")
public class RequestEXDivServlet extends HttpServlet {
	private static final String nextPage="/request_ex_output";

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		int num1=(int) request.getAttribute("num1");
		int num2=(int) request.getAttribute("num2");
		
		double nResult=((double)num1/num2);
		String strResult=String.format("<h3>%d - %d = %.2f</h3>", num1,num2,nResult);
		
		request.setAttribute("output_div", strResult);
		
		RequestDispatcher rd=request.getRequestDispatcher(nextPage);
		rd.forward(request, response);
	}

}
