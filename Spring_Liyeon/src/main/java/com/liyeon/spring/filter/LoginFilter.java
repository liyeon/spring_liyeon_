package com.liyeon.spring.filter;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter({"/users/private/*"})
public class LoginFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//부모 type을 자식 type으로 Casting 후
		HttpServletRequest req = (HttpServletRequest) request;
		//객체의 참조값을 얻어낸다.
		HttpSession session = req.getSession();
		//로그인된 아이디가 있는지얻어와본다.
		String id = (String) session.getAttribute("id");
		
		if(id !=null) {
			//로그인 된 상태면 요청 흐름을 계속 이어간다.
			chain.doFilter(request, response);
		}else {
			//원래가려던 url정보를 읽어온다.
			String url=req.getRequestURI();
			
			/*
			 * Get방식 전송파라미터를 query String 으로 얻어온다.
			 * 쿼리 문자열 뒤에 달고다니는 문자열을 말한다. 
			 */
			String query=req.getQueryString();
			
			//인코딩한다.
			String encodedUrl=null;
			
			if(query==null) {//전송파라미터가 없다면 
				encodedUrl=URLEncoder.encode(url);
			}else {//전송파라미터가 있다면
				encodedUrl=URLEncoder.encode(url+"?"+query);
			}
			//로그인 폼으로 리다이렉트 이동 하라고 응답
			HttpServletResponse res=(HttpServletResponse) response;
			String cPath=req.getContextPath();
			res.sendRedirect(cPath+"/users/loginform.do?url"+encodedUrl);
		}
	}//====doFilter====

	@Override
	public void destroy() {}
}//=====LoginFilter=====