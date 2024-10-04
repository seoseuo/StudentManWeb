package com.ssk.web.common;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;

/**
 * Servlet Filter implementation class TimeCheckFilter
 */
@WebFilter(urlPatterns = "*.do")
public class TimeCheckFilter extends HttpFilter implements Filter {
    
	private static final long serialVersionUID = 1L;
	
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("---> destroy() 호출");
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest req = (HttpServletRequest) request;
		String uri = req.getRequestURI(); // /~~~/~~~/board/getBoardList.do
		String path = uri.substring(uri.lastIndexOf("/"));
		
		
		//System.out.println("---> doFilter() 호출");

		// pass the request along the filter chain
		//System.out.println("---[ 사전 처리 ]---");
		long startTime = System.currentTimeMillis();
		chain.doFilter(request, response);
		long endTime = System.currentTimeMillis();
		//System.out.println("---[ 사후 처리 ]---");
		
		System.out.println(path + " 요청 처리에 소요된 시간 : " + (endTime - startTime) + "(ms)초\n");
		//System.out.println("서블릿 수행에 소요된 시간 : " + (endTime - startTime) + "(ms)초");
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("---> init() 호출");
	}

}
