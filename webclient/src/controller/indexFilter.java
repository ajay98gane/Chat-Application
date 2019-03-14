package controller;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(filterName = "indexfilter", urlPatterns = { "/index.html" })
public class indexFilter implements Filter {


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

			HttpSession session = ((HttpServletRequest) request).getSession(false);
			if (session != null) {
				((HttpServletResponse) response).sendRedirect("home");
			} else {

				chain.doFilter(request, response);
			}
		
	}

}
