package tje.mvc.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Services.MainService;
import Services.Service;

@WebServlet("*.test")
public class Controller extends HttpServlet {
	private HashMap<String, Service> map = new HashMap<>();
	
	public void init(ServletConfig config) throws ServletException {
		map.put("member_main.test", new MainService());
		
		super.init(config);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	
	public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestURI = request.getRequestURI().substring(request.getContextPath().length());
		String viewPage = null;
	}
}
