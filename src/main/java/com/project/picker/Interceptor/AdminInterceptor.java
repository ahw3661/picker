package com.project.picker.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AdminInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if(request.getSession().getAttribute("u_id") != null && request.getSession().getAttribute("u_name") != null && 
			request.getSession().getAttribute("u_type") != null && (int)request.getSession().getAttribute("u_type") == 0) {
			return true;
		}
		if(request.getHeader("ajax") != null && request.getHeader("ajax").equals("true")) {
			response.sendRedirect(request.getContextPath() + "/ajaxError");
			return false;
		}
		response.sendRedirect(request.getContextPath() + "/noticeAdminError");
		return false;
	}
	
}
