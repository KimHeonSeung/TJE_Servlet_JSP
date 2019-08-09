
package pro.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainCommand extends Command {
	private String formPage = "/WEB-INF/forms/main.jsp";
	protected String processForm(HttpServletRequest request, HttpServletResponse response) {
		
		return formPage;
	}

	protected String processSubmit(HttpServletRequest request, HttpServletResponse response) {
		
		return null;
	}	
}
