package com.project.picker.Interceptor;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.project.mapper.QnaMapperDAO;

public class ReplyInterceptor extends HandlerInterceptorAdapter {
	
	private final static Logger logger = LoggerFactory.getLogger(ReplyInterceptor.class);
	
	@Inject
	QnaMapperDAO dao;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		throws Exception {
		logger.info("replyInterceptor ½ÇÇà");
		HttpSession session = request.getSession();
		if(session.getAttribute("u_id") != null && session.getAttribute("u_type") != null) {
			int r_num = Integer.parseInt(request.getParameter("r_num"));
			if(((String)session.getAttribute("u_id")).equals(dao.getReplyWriter(r_num))) {
				return true;
			}
		}
		if(request.getHeader("ajax") != null && request.getHeader("ajax").equals("json")) {
			response.sendRedirect(request.getContextPath() + "/ajaxError");
			return false;
		}
		if(request.getHeader("ajax") != null && request.getHeader("ajax").equals("html")) {
			response.sendRedirect(request.getContextPath() + "/replyError");
			return false;
		}
		response.sendRedirect(request.getContextPath() + "/replyError");
		return false;
	}

}
