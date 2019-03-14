package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebFilter(filterName = "myfilter", urlPatterns = { "/*" }, initParams = {
		@WebInitParam(name = "excludedurls", value = "/index.html,/login.html,/signup.html,/login,/signup") })
public class myfilter implements Filter {

	List<String> excludedurls = new ArrayList<String>();

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String path = ((HttpServletRequest) request).getServletPath();

		if (!excludedurls.contains(path)) {
			HttpSession session = ((HttpServletRequest) request).getSession(false);
			if (session == null) {
				((HttpServletResponse) response).sendRedirect("index.html");
			} else {
				chain.doFilter(request, response);
			}
		} else

		{
			chain.doFilter(request, response);

		}
	}

	public void init(FilterConfig filterConfig) throws ServletException {

		String excludePattern = filterConfig.getInitParameter("excludedurls");
		excludedurls = Arrays.asList(excludePattern.split(","));
	}

}
