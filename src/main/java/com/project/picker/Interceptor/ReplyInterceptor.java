package com.project.picker.Interceptor;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.project.mapper.QnaMapperDAO;

public class ReplyInterceptor extends HandlerInterceptorAdapter {
	
	@Inject
	QnaMapperDAO dao;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		throws Exception {
		HttpSession session = request.getSession();
		if(session.getAttribute("u_id") != null && session.getAttribute("u_type") != null) {
			int num = Integer.parseInt(request.getParameter("num"));
			if(((String)session.getAttribute("u_id")).equals(dao.getReplyWriter(num))) {
				return true;
			}
		}
		if(request.getHeader("ajax") != null && request.getHeader("ajax").equals("true")) {
			response.sendRedirect(request.getContextPath() + "/ajaxError");
			return false;
		}
		response.sendRedirect(request.getContextPath() + "/replyError");
		return false;
	}

}
