package com.ssk.web.common;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpFilter;

/**
 * Servlet Filter implementation class CharacterEncodingFilter
 */
@WebFilter(urlPatterns = { "*.do" }, initParams = @WebInitParam(name = "studentEncoding", value = "UTF-8"))
public class CharacterEncodingFilter extends HttpFilter implements Filter {

	private static final long serialVersionUID = 1L;
	private String encoding;

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// 서블릿이 수행되기 전에 인코딩을 처리합니다.
		request.setCharacterEncoding(encoding);

		// 응답의 Content-Type 설정
		response.setContentType("text/html;charset=" + encoding);

		// 필터 체인으로 요청을 전달합니다.
		chain.doFilter(request, response);
	}

	public void init(FilterConfig config) throws ServletException {
		// 초기화 파라미터에서 인코딩 값을 읽어옵니다.
		encoding = config.getInitParameter("studentEncoding");
	}

}
