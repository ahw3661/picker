package com.project.picker.Interceptor;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.project.mapper.QnaMapperDAO;

public class QnaInterceptor extends HandlerInterceptorAdapter {
	
	private final static Logger logger = LoggerFactory.getLogger(QnaInterceptor.class);
	
	@Inject
	QnaMapperDAO dao;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		throws Exception {
		logger.info("qnaInterceptor ½ÇÇà");
		HttpSession session = request.getSession();
		if(session.getAttribute("u_id") != null && session.getAttribute("u_type") != null) {
			int q_num = Integer.parseInt(request.getParameter("q_num"));
			if(request.getHeader("admin") != null && request.getHeader("admin").equals("accessible") && (int)session.getAttribute("u_type") == 0 || 
				((String)session.getAttribute("u_id")).equals(dao.getWriter(q_num))) {
				return true;
			}
		}
		if(request.getHeader("ajax") != null && request.getHeader("ajax").equals("json")) {
			response.sendRedirect(request.getContextPath() + "/ajaxError");
			return false;
		}
		if(request.getHeader("ajax") != null && request.getHeader("ajax").equals("html")) {
			response.sendRedirect(request.getContextPath() + "/qnaError");
			return false;
		}
		response.sendRedirect(request.getContextPath() + "/qnaError?loc=2");
		return false;
	}

}
