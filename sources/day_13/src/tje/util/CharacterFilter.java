package tje.util;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharacterFilter implements Filter {
	private String strEncoding;
	
	public void init(FilterConfig fConfig) throws ServletException {
		strEncoding = fConfig.getInitParameter("encoding");
		Filter.super.init(fConfig);
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		if( strEncoding != null ) {
			request.setCharacterEncoding(strEncoding);
		}
		
		chain.doFilter(request, response);
	}
	
	public void destroy() {
		Filter.super.destroy();
	}

}
