package tje.servlets.cookie;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/calculator")
public class CookieCal extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		
		Cookie[] cookies=request.getCookies();
		String save_n1=null;
		String save_n2=null;
		String save_op=null;
		
		for (int i=0;cookies!=null&&i<cookies.length;i++) {
			if(cookies[i].getName().equals("save_n1"))
				save_n1=cookies[i].getValue();
			
			if(cookies[i].getName().equals("save_n2"))
				save_n2=cookies[i].getValue();
			
			if(cookies[i].getName().equals("save_op"))
				save_op=cookies[i].getValue();
		}
		
		System.out.printf("%s %s %s\n",save_n1,save_op,save_n2);
		
		PrintWriter out=response.getWriter();
		StringBuilder sb=new StringBuilder();
		
		sb.append("<form action=\"./calculator\" method=\"post\">");
		sb.append("<table>");
		sb.append("<tr>");
		sb.append("<th>n1</th>");
		sb.append("<th>op</th>");
		sb.append("<th>n2</th>");
		sb.append("</tr>");
		sb.append("<tr>");
		
		if(save_n1==null) {
			sb.append("<td><input type=\"text\" name=\"n1\" required></td>");
		}else {
			sb.append("<td><input type=\"text\" name=\"n1\" value='"+save_n1+"' required></td>");
		}
		
		sb.append("<td><select name=\"op\">");
		if(save_op==null) {
			sb.append("<option value=\"+\">+</option>");
			sb.append("<option value=\"-\">-</option>");
			sb.append("<option value=\"*\">*</option>");
			sb.append("<option value=\"/\">/</option>");
		}else {
			if(save_op.equals("+"))
				sb.append("<option value=\"+\" selected>+</option>");
			else
				sb.append("<option value=\"+\">+</option>");
			
			if(save_op.equals("-"))
				sb.append("<option value=\"-\" selected>-</option>");
			else
				sb.append("<option value=\"-\">-</option>");
			
			if(save_op.equals("*"))
				sb.append("<option value=\"*\" selected>*</option>");
			else
				sb.append("<option value=\"*\">*</option>");
			
			if(save_op.equals("/"))
				sb.append("<option value=\"/\">/</option>");
			else
				sb.append("<option value=\"/\">/</option>");
			
		}
		sb.append("</select></td>");
		
		if(save_n2==null) {
			sb.append("<td><input type=\"text\" name=\"n2\" required></td>");
		}else {
			sb.append("<td><input type=\"text\" name=\"n2\" value='"+save_n2+"' required></td>");
		}
		
		sb.append("</tr>");
		sb.append("</table>");
		sb.append("<input type=\"submit\">");
		sb.append("</form>");
		
		out.println(sb);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		PrintWriter out=response.getWriter();
		StringBuilder sb=new StringBuilder();
		
		String sN1=request.getParameter("n1").trim();
		String op=request.getParameter("op");
		String sN2=request.getParameter("n2").trim();
		
		System.out.printf("%s %s %s\n",sN1,op,sN2);
		
		int n1=0;
		int n2=0;
		try {
			n1=Integer.parseInt(sN1);
			n2=Integer.parseInt(sN2);
		} catch (Exception e) {
			System.out.println("casting fail");
			out.println("<h2>값 확인</h2>");
		}
		
		double result=0;
		
		if(op.equals("+"))
			result=n1+n2;
		else if(op.equals("-"))
			result=n1-n2;
		else if(op.equals("*"))
			result=n1*n2;
		else if(op.equals("/"))
			result=(double)n1/n2;
		else 
			System.out.println("op fail");
		
		String save_n1=request.getParameter("save_n1");
		String save_op=request.getParameter("save_op");
		String save_n2=request.getParameter("save_n2");
		
		System.out.printf("%s %s %s\n",save_n1,save_op,save_n2);
		
		Cookie cookie=null;
		
		if(save_n1!=null) {
			cookie=new Cookie("save_n1","");
			cookie.setMaxAge(0);
			response.addCookie(cookie);			
		}else if(save_n1==null) {
			cookie=new Cookie("save_n1",sN1);
			response.addCookie(cookie);
		}
		
		if(save_op!=null) {
			cookie=new Cookie("save_op","");
			cookie.setMaxAge(0);
			response.addCookie(cookie);			
		}else if(save_op==null) {
			cookie=new Cookie("save_op",op);
			response.addCookie(cookie);
		}
		
		if(save_n2!=null) {
			cookie=new Cookie("save_n2","");
			cookie.setMaxAge(0);
			response.addCookie(cookie);			
		}else if(save_n2==null) {
			cookie=new Cookie("save_n2",sN2);
			response.addCookie(cookie);
		}
		
		sb.append("<h2>");
		sb.append(String.format("%d %s %d = %.2f", n1,op,n2,result));
		sb.append("</h2>");
		
		out.println(sb);
	}

}
