package com.project.picker;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.WebUtils;

import com.project.picker.DTO.MemberDTO;
import com.project.picker.Service.CartService;
import com.project.picker.Service.MemberService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@Inject
	MemberService mservice;
	
	@Inject
	CartService Cservice;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpSession session, HttpServletRequest request) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		String m_id = "";
		
		logger.info(">>> 메인 페이지 접속");
		Cookie loginCookie = WebUtils.getCookie(request, "loginCookie"); // 생성했던 쿠키
		
		if(loginCookie != null) { // 쿠키가 존재하는 경우
			logger.info(">>> 쿠키 정보 존재");
			String sessionId = loginCookie.getValue(); // 쿠키에 저장했던 세션 id
			MemberDTO mdto = mservice.getSessionUser(sessionId); // 이전 로그인 여부 확인. 유효시간이 남은 사용자
			
			if(mdto != null) { // 사용자가 있는 경우 세션 생성
				session.setAttribute("login", mdto);
				session.setAttribute("u_id", mdto.getM_id());
				session.setAttribute("u_name", mdto.getM_name());
				session.setAttribute("u_type", mdto.getM_type());
				logger.info(">>> 세션 생성");
			}
		}
		
		if(session.getAttribute("u_id") != null) {		
			m_id =  (String)session.getAttribute("u_id");
		}else {
			m_id = "";
		}
		int count = Cservice.totalCartCount(m_id);
		session.setAttribute("cnt", count);		

		model.addAttribute("serverTime", formattedDate );
		model.addAttribute("section", "Section.jsp");
		return "Index";
	}
	
	@RequestMapping("index")
	public String index() {
		return "redirect:/";
	}			 
}
