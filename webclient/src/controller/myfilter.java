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

/**
 * Servlet Filter implementation class myfilter
 */
@WebFilter(filterName = "myfilter",
urlPatterns = {"/*"},initParams = {
	    @WebInitParam(name = "excludedurls", value = "/index.html,/login.html,/signup.html,/login,/signup")})
public class myfilter implements Filter {

    /**
     * Default constructor. 
     */
	List<String> excludedurls=new ArrayList<String>();
	//excludedurls.add("/index.html");
    public myfilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		String path=((HttpServletRequest) request).getServletPath();
//		if(path.equals("/login"))
//		{
//			HttpSession session = ((HttpServletRequest)request).getSession(true);
//			if(session==null)
//			{	System.out.println("b");
//				((HttpServletResponse)response).sendRedirect("index.html");
//			}
//			else
//				{System.out.println("c");
//				chain.doFilter(request, response);
//				}
//			
//		}
		if(!excludedurls.contains(path))
		{		HttpSession session = ((HttpServletRequest)request).getSession(false);
		if(session==null)
		{	System.out.println("b");
			((HttpServletResponse)response).sendRedirect("index.html");
		}
		else
		{			chain.doFilter(request, response);
		}
		}
		else
			
		{			chain.doFilter(request, response);

		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
//		excludedurls.add("/index.html");
//		excludedurls.add("/login.html");
//		excludedurls.add("/signup.html");
		 String excludePattern = filterConfig.getInitParameter("excludedurls");
		    excludedurls = Arrays.asList(excludePattern.split(","));
	}

}
