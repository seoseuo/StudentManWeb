package com.ssk.web.common;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpFilter;

/**
 * Servlet Filter implementation class CharacterEncodingFilter
 */
@WebFilter(urlPatterns = {"*.do"},
			initParams = @WebInitParam(name = "studentEncoding", value = "UTF-8"))
public class CharacterEncodingFilter extends HttpFilter implements Filter {
     
	private static final long serialVersionUID = 1L;
	private String encoding;
	
    /**
     * @see HttpFilter#HttpFilter()
     */
    //public CharacterEncodingFilter() {
        //super();
        // TODO Auto-generated constructor stub
    //}

	/**
	 * @see Filter#destroy()
	 */
	//public void destroy() {
		// TODO Auto-generated method stub
	//}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		
		// 서블릿이 수행되기 전에 인코딩을 처리합니다.
		//ServletContext context = request.getServletContext();
		//String encoding = context.getInitParameter("boardEncoding");
		request.setCharacterEncoding(encoding);
		
		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig config) throws ServletException {
		// TODO Auto-generated method stub
		encoding = config.getInitParameter("studentEncoding");
	}

}
