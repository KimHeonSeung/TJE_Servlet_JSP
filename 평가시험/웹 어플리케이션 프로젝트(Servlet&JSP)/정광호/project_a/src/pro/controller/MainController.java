package pro.controller;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pro.command.Command;

public class MainController extends HttpServlet {
	private HashMap<String, Command> uriMap = new HashMap<>();

	public void init(ServletConfig config) throws ServletException {
		String strConfigFile = config.getInitParameter("ConfigFile");
		String strConfigFilePath = config.getServletContext().getRealPath(strConfigFile);

		Properties prop = new Properties();

		try (FileReader fr = new FileReader(strConfigFilePath)) {
			prop.load(fr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Iterator<Object> keyIter = prop.keySet().iterator();
		while (keyIter.hasNext()) {
			String strServiceName = (String) keyIter.next();
			String strServiceClassName = prop.getProperty(strServiceName);
			try {
				Class<?> commandClass = Class.forName(strServiceClassName);
				Command command = (Command) commandClass.newInstance();
				uriMap.put(strServiceName, command);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		super.init(config);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String requestURI = request.getRequestURI().substring(request.getContextPath().length());
		String viewPage = null;
		Command command = null;
		if((command = uriMap.get(requestURI)) != null) {
			viewPage = command.process(request, response);
		}else {
			response.sendError(404);
		}
		if(viewPage != null ) {
			request.getRequestDispatcher(viewPage).forward(request, response);
		}
	}

}
