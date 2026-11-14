package br.gasc.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Interceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String uri = request.getRequestURI();
		
		if(uri.endsWith("loginAdmin") || uri.endsWith("efetuaLogin") || uri.endsWith("adminDefault") || uri.contains("resources"))
			 return true;
		
		if(request.getSession().getAttribute("adminLogado") != null)
			return true;
		
		response.sendRedirect(String.format("%s/loginAdmin", request.getContextPath()));
		
		return false;
	}

	
	
}
