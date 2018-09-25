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

public class ForceLoginFilter implements Filter {

	public ForceLoginFilter() {
		// TODO Auto-generated constructor stub
	}

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpSession session = ((HttpServletRequest) request).getSession(false);
		System.out.println(session.getAttribute("usernameLog"));
		if (session.getAttribute("usernameLog") == null) {
			((HttpServletResponse) response)
					.sendRedirect(((HttpServletRequest) request).getContextPath() + "/Login.jsp");
		} else {
			chain.doFilter(request, response);
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

}
