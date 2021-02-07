package com.project.picker.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MenuController {

	@RequestMapping(value="section", method= {RequestMethod.GET, RequestMethod.POST})
	public String goIndex() {
		return "Index";
	}
	
	@RequestMapping(value="introPage", method= {RequestMethod.GET, RequestMethod.POST})
	public String introPage(Model model) {
		model.addAttribute("section", "Intro.jsp");
		return "Index";
	}
}
