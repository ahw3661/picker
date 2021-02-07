package com.project.picker.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.project.picker.DTO.MemberDTO;

public class AdminInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		MemberDTO login = (MemberDTO)session.getAttribute("login");
		
		if(request.getSession().getAttribute("u_id") != null && request.getSession().getAttribute("u_name") != null && 
			request.getSession().getAttribute("u_type") != null && (int)request.getSession().getAttribute("u_type") == 0) {
			return true;
		}
		if(request.getHeader("ajax") != null && request.getHeader("ajax").equals("true")) {
			response.sendRedirect(request.getContextPath() + "/ajaxError");
			return false;
		}
		
		if(login != null && login.getM_type() == 1) { // 일반회원이 로그인 후 관리자페이지에 접근하려는 경우
			response.sendRedirect(request.getContextPath() + "/wrongAccess");
			return false;
		}
		response.sendRedirect(request.getContextPath() + "/noticeAdminError");
		return false;
	}
	
}
