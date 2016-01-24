package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class StuLoginCheckFilter implements Filter {

	public void destroy() {
		
	}

	/**
	 * session中有currentUser这个属性就认为已经登录
	 * 
	 * 如果没登陆，那么要redirect到登录页面
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession();
		
		if (session.getAttribute("stuUser") == null) {
			((HttpServletResponse) response).sendRedirect(httpRequest
					.getContextPath() + "/login.jsp");
		} else
			chain.doFilter(request, response);
	}

	public void init(FilterConfig arg0) throws ServletException {

	}

}
