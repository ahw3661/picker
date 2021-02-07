package com.project.picker.Interceptor;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import com.project.picker.DTO.MemberDTO;
import com.project.picker.Service.MemberService;

public class LoginInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
	
	@Inject
	MemberService mservice;
	
	@Override // 요청 전
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.info(">>> pre");
		HttpSession session = request.getSession();
		//MemberDTO login = (MemberDTO)session.getAttribute("login");
		
		if(session.getAttribute("u_id") != null) {
			logger.info(">>> 세션 아이디 존재");
			return true;
		}else {
			logger.info(">>> 세션 아이디 없음");
			Cookie loginCookie = WebUtils.getCookie(request, "loginCookie"); // 생성했던 쿠키
			
			if(loginCookie != null) { // 쿠키가 존재하는 경우
				logger.info(">>> 쿠키 정보 존재");
				String sessionId = loginCookie.getValue(); // 쿠키에 저장했던 세션 id
				MemberDTO mdto = mservice.getSessionUser(sessionId); // 이전 로그인 여부 확인. 유효시간이 남은 사용자
				
				if(mdto != null) { // 사용자가 있는 경우 세션 생성
					session.setAttribute("u_id", mdto.getM_id());
					session.setAttribute("u_name", mdto.getM_name());
					session.setAttribute("u_type", mdto.getM_type());
					logger.info(">>> 세션 생성");
					return true;
				}
			}
			
			if(isAjaxRequest(request)) {
				logger.info(">>> ajax 관련 에러 메시지 화면으로 이동");
				response.sendRedirect(request.getContextPath() + "/errorPage");
				return false;
			}else {
				logger.info(">>> 로그인 화면으로 이동");
				response.sendRedirect(request.getContextPath() + "/loginPage");
				return false;
			}
		}
	}
	
	// ajax
	private boolean isAjaxRequest(HttpServletRequest request) {
		String header = request.getHeader("ajax");
		logger.info(">>> ajax에서 넘어온 정보");
		if("true".equals(header)) { // ajax일 경우
			return true;
		}else {
			return false;
		}
	}
	
}
