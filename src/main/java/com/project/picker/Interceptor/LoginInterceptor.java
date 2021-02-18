package com.project.picker.Interceptor;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import com.project.picker.DTO.MemberDTO;
import com.project.picker.Service.MemberService;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	@Inject
	MemberService mservice;
	
	@Override // 요청 전
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		MemberDTO login = (MemberDTO)session.getAttribute("login");
		
		if(login != null) {
			// 세션 아이디 존재
			return true;
		}else {
			// 세션 아이디 없음
			Cookie loginCookie = WebUtils.getCookie(request, "loginCookie"); // 생성했던 쿠키
			
			if(loginCookie != null) { // 쿠키가 존재하는 경우
				String sessionId = loginCookie.getValue(); // 쿠키에 저장했던 세션 id
				MemberDTO mdto = mservice.getSessionUser(sessionId); // 이전 로그인 여부 확인. 유효시간이 남은 사용자
				
				if(mdto != null) { // 사용자가 있는 경우 세션 생성
					session.setAttribute("login", mdto);
					session.setAttribute("u_id", mdto.getM_id());
					session.setAttribute("u_name", mdto.getM_name());
					session.setAttribute("u_type", mdto.getM_type());
					return true;
				}
			}
			
			if((request.getHeader("ajax") != null && request.getHeader("ajax").equals("true"))) {
				// ajax 관련 에러 메시지 화면으로 이동
				response.sendRedirect(request.getContextPath() + "/errorPage");
				return false;
			}else if((request.getHeader("ajax") != null && request.getHeader("ajax").equals("json"))) {
				// ajax 관련 에러 메시지 화면으로 이동
				response.sendRedirect(request.getContextPath() + "/ajaxErrorPage");
				return false;
			}else {
				// 로그인 화면으로 이동
				response.sendRedirect(request.getContextPath() + "/loginPage");
				return false;
			}
		}
	}
	
}
