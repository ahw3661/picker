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
		
		logger.info(">>> ���� ������ ����");
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
