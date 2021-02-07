package com.project.picker;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.project.picker.Service.CartService;

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
	CartService Cservice;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpSession session) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		String m_id = "";
		
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
