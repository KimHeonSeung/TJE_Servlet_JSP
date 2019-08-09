package pro.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@WebFilter("/*")
public class CookieManagerFilter implements Filter {
	public void init(FilterConfig fConfig) throws ServletException {
	}
	

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		Cookie [] cookies = req.getCookies();
		if(cookies != null && cookies.length>0) {
			req.setAttribute("cookieManager", new CookieManager(cookies));
		}
		
		chain.doFilter(request, response);
	}

	public void destroy() {
	}

}
