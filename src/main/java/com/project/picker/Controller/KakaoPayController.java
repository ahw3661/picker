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
	
	@RequestMapping(value="buy/kakaoPaySuccess", method={RequestMethod.GET, RequestMethod.POST})
	public void kakaoPaySuccess(@RequestParam("pg_token") String pg_token, Model model, HttpServletRequest request, HttpSession session, BuyDTO bdto) {
		System.out.println("kakaoPaySuccess get............................................");
		System.out.println("kakaoPaySuccess pg_token : " + pg_token);
		model.addAttribute("info", kakaopay.kakaoPayInfo(request, session, bdto, pg_token));
    }
	
	@RequestMapping(value="buy/kakaoPayCancel", method={RequestMethod.GET, RequestMethod.POST})
	public String kakaoPayCancel(@RequestParam("pg_token") String pg_token, Model model) {
		System.out.println("kakaoPaySuccess get............................................");
		System.out.println("kakaoPaySuccess pg_token : " + pg_token);
		return "kakaoPayCancel";
    }
	
	@RequestMapping(value="buy/kakaoPaySuccessFail", method={RequestMethod.GET, RequestMethod.POST})
	public String kakaoPaySuccessFail(@RequestParam("pg_token") String pg_token, Model model) {
		System.out.println("kakaoPaySuccess get............................................");
		System.out.println("kakaoPaySuccess pg_token : " + pg_token);
		
		return "kakaoPaySuccessFail";
    }
	
	@RequestMapping(value="buy/insertBuyitems", method={RequestMethod.GET, RequestMethod.POST})
	public String insertBuyitems(HttpServletRequest request, HttpSession session, BuyDTO bdto, Model model) {
		kakaopay.insertBuyitems(bdto, request, session, model);		
		//BuyitemDTO bidto, PointDTO pdto, int sum_point, String id, String i_code,
		String m_id = (String)session.getAttribute("u_id");
		String [] request_num = request.getParameterValues("c_num");
		int c_num =0;
		if(session.getAttribute("u_id")!=null) {
			// ↓ point메서드를 위한 request값
			int plus_point = Integer.parseInt("+"+request.getParameter("saving_P"));  //적립포인트
			int minus_point = Integer.parseInt("-"+request.getParameter("usePoint_hidden")); //사용포인트
			kakaopay.insertPoint(plus_point, minus_point);
			kakaopay.updatePoint(m_id);
			for(int i=0; i<request_num.length;i++) {
				c_num = Integer.valueOf(request_num[i].toString());
				kakaopay.delCartItem(c_num);
			}	
		}
		
		return "redirect:../section";
	}
	
	
}
