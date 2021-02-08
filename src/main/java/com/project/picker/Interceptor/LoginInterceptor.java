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
	
	@Override // ��û ��
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.info(">>> pre");
		HttpSession session = request.getSession();
		MemberDTO login = (MemberDTO)session.getAttribute("login");
		
		if(login != null) {
			logger.info(">>> ���� ���̵� ����");
			return true;
		}else {
			logger.info(">>> ���� ���̵� ����");
			Cookie loginCookie = WebUtils.getCookie(request, "loginCookie"); // �����ߴ� ��Ű
			
			if(loginCookie != null) { // ��Ű�� �����ϴ� ���
				logger.info(">>> ��Ű ���� ����");
				String sessionId = loginCookie.getValue(); // ��Ű�� �����ߴ� ���� id
				MemberDTO mdto = mservice.getSessionUser(sessionId); // ���� �α��� ���� Ȯ��. ��ȿ�ð��� ���� �����
				
				if(mdto != null) { // ����ڰ� �ִ� ��� ���� ����
					session.setAttribute("login", mdto);
					session.setAttribute("u_id", mdto.getM_id());
					session.setAttribute("u_name", mdto.getM_name());
					session.setAttribute("u_type", mdto.getM_type());
					logger.info(">>> ���� ����");
					return true;
				}
			}
			logger.info("ajax : "+request.getHeader("ajax"));
			
			if((request.getHeader("ajax") != null && request.getHeader("ajax").equals("true"))) {
				logger.info(">>> ajax ���� ���� �޽��� ȭ������ �̵�");
				response.sendRedirect(request.getContextPath() + "/errorPage");
				return false;
			}else if((request.getHeader("ajax") != null && request.getHeader("ajax").equals("json"))) {
				logger.info(">>> ajax ���� ���� �޽��� ȭ������ �̵�");
				response.sendRedirect(request.getContextPath() + "/ajaxErrorPage");
				return false;
			}else {
				logger.info(">>> �α��� ȭ������ �̵�");
				response.sendRedirect(request.getContextPath() + "/loginPage");
				return false;
			}
		}
	}
	
}
