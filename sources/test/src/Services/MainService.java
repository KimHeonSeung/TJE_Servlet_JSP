package Services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainService extends Service {
	private String getPage = "/member_main";
	private String postPage = "";
	
	protected String processGet(HttpServletRequest request, HttpServletResponse response) {
		return getPage;
	}
	protected String processPost(HttpServletRequest request, HttpServletResponse response) {
		return postPage;
	}
	
}
