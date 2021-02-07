package com.project.picker.Controller;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.picker.DTO.BuyDTO;
import com.project.picker.DTO.BuyitemDTO;
import com.project.picker.Service.BuyService;
import com.project.picker.Service.KakaoPayService;

@Controller
public class KakaoPayController {

	@Inject
	KakaoPayService kakaopay;

	@ResponseBody
	@RequestMapping(value="kakaoPay", method={RequestMethod.GET, RequestMethod.POST})
	public Map<String, Object> kakaoPay(HttpServletRequest request, HttpSession session, BuyDTO bdto) {
		Map<String, Object> map = new HashMap<String, Object>();
		System.out.println("kakaoPay post............................................");
        //return "redirect:" + kakaopay.kakaoPayReady(request, bdto);
        
        map.put("pc_url",kakaopay.kakaoPayReady(request, session, bdto));
        
        return map;
    }
	
	@RequestMapping(value="kakaoPaySuccess", method={RequestMethod.GET, RequestMethod.POST})
	public void kakaoPaySuccess(@RequestParam("pg_token") String pg_token, Model model, HttpServletRequest request, HttpSession session, BuyDTO bdto) {
		System.out.println("kakaoPaySuccess get............................................");
		System.out.println("kakaoPaySuccess pg_token : " + pg_token);
		model.addAttribute("info", kakaopay.kakaoPayInfo(request, session, bdto, pg_token));
    }
	
	@RequestMapping(value="kakaoPayCancel", method={RequestMethod.GET, RequestMethod.POST})
	public String kakaoPayCancel(@RequestParam("pg_token") String pg_token, Model model) {
		System.out.println("kakaoPaySuccess get............................................");
		System.out.println("kakaoPaySuccess pg_token : " + pg_token);
		return "kakaoPayCancel";
    }
	
	@RequestMapping(value="kakaoPaySuccessFail", method={RequestMethod.GET, RequestMethod.POST})
	public String kakaoPaySuccessFail(@RequestParam("pg_token") String pg_token, Model model) {
		System.out.println("kakaoPaySuccess get............................................");
		System.out.println("kakaoPaySuccess pg_token : " + pg_token);
		
		return "kakaoPaySuccessFail";
    }
	
	@RequestMapping(value="insertBuyitems", method={RequestMethod.GET, RequestMethod.POST})
	public String insertBuyitems(HttpServletRequest request, HttpSession session, BuyDTO bdto, BuyitemDTO bidto, Model model) {
		kakaopay.insertBuyitems(bdto, request, session, model);
		model.addAttribute("section", "Cart.jsp");
		return "index";
	}
	
	
}
