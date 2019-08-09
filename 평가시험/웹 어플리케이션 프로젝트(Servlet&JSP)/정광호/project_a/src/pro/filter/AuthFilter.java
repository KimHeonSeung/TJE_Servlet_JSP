package pro.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/auth/*")
public class AuthFilter implements Filter {

	private static final String authPage = "/error/authPage.jsp";

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession(false);
		if (session == null || session.getAttribute("login_member") == null) {
			HttpServletResponse res = (HttpServletResponse) response;
			res.sendRedirect(req.getContextPath() + authPage);
			return;
		}
		chain.doFilter(request, response);
	}

	public void destroy() {
	}
}
