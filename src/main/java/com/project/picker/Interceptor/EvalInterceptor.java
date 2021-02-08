package com.project.picker.Interceptor;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.project.mapper.EvalMapperDAO;

public class EvalInterceptor extends HandlerInterceptorAdapter {
	
	private static final Logger logger = LoggerFactory.getLogger(EvalInterceptor.class);
	
	@Inject
	EvalMapperDAO dao;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		throws Exception {
		logger.info("EvalInterceptor ½ÇÇà");
		HttpSession session = request.getSession();
		String i_code = request.getParameter("i_code");
		if(session.getAttribute("pcsCode") == null) {
			if(session.getAttribute("u_id") != null) {
				String id = (String)session.getAttribute("u_id");
				if(dao.memberBuyCheck(i_code, id) > 0) {
					session.setAttribute("pcsCode", dao.getMemberBuyNumber(i_code, id));
					if(session.getAttribute("pcsEvalCode") == null && dao.memberEvalCheck(i_code, id) > 0) {
						session.setAttribute("pcsEvalCode", dao.getMemberEvalNumber(i_code, id));
					}
				}
			} else {
				if(request.getParameter("b_code") != null && request.getParameter("phone") != null) {
					int b_code = Integer.parseInt(request.getParameter("b_code"));
					String phone = request.getParameter("phone");
					if(dao.noneMemberBuyCheck(b_code, phone, i_code) == 1) {
						session.setAttribute("pcsCode", b_code);
						if(session.getAttribute("pcsEvalCode") == null && dao.noneMemberEvalCheck(b_code, i_code) > 0) {
							session.setAttribute("pcsEvalCode", dao.getNoneMemberEvalNumber(b_code, i_code));
						}
					}
				}
			}
		}
		if(session.getAttribute("pcsCode") != null) logger.info("pcsCode : " + (int)session.getAttribute("pcsCode"));
		if(session.getAttribute("pcsEvalCode") != null) logger.info("pcsEvalCode : " + (int)session.getAttribute("pcsEvalCode"));
		return true;
	}

}
