package Services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class Service {
	public String service (HttpServletRequest request, HttpServletResponse response) {
		String method = request.getMethod();
		if( method.equals("GET") ) {
			return processGet(request, response);
		} else if( method.equals("POST") ) {
			return processPost(request, response);
		} else {
			response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
	
	protected abstract String processGet(HttpServletRequest request, HttpServletResponse response);
	protected abstract String processPost(HttpServletRequest request, HttpServletResponse response);
	
}
