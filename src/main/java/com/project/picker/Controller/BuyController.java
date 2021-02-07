package com.project.picker.Controller;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.project.picker.DTO.CartDTO;
import com.project.picker.DTO.MemberDTO;
import com.project.picker.Service.BuyService;
import com.project.picker.Service.CartService;
import com.project.picker.Service.ItemService;
import com.project.picker.Service.MemberService;

@SuppressWarnings("unchecked")
@Controller
public class BuyController {

	@Inject
	BuyService Bservice;

	@Inject
	CartService Cservice;
	
	@Inject
	MemberService Mservice;
	
	@Inject
	ItemService Iservice;
	
	
	@RequestMapping(value="gobuyPage_FromCart", method={RequestMethod.GET, RequestMethod.POST})
	public String gobuyPage_FromCart(HttpSession session, Model model, HttpServletRequest request){
		// 구매 페이지로 이동 (장바구니에서 구매시 이동하는 맵핑)
		String m_id = "";
		ArrayList<CartDTO> payList = new ArrayList<>();
		CartDTO cdto = new CartDTO();
		if(session.getAttribute("u_id") != null) {		
			m_id =  (String)session.getAttribute("u_id");
			String[] i_code = request.getParameterValues("i_code");
			for(int i = 0;i < i_code.length;i++) {
				cdto = Bservice.payList_cart(m_id, i_code[i]);
				payList.add(cdto);
			}
		}else {
			m_id = "";
			ArrayList<CartDTO> list;	
			String[] i_code = request.getParameterValues("i_code");
			list = (ArrayList<CartDTO>)session.getAttribute("sessionList");
			for(int i=0;i<list.size();i++) {
				for(int j=0;j<i_code.length;j++) {
					if(list.get(i).getI_code().equals(i_code[j])) {
						payList.add(list.get(i));
					}
				}
			}
		}
		model.addAttribute("payList", payList);
		
		MemberDTO mdto =  Mservice.viewMember(m_id);
		model.addAttribute("mdto", mdto);
		
		model.addAttribute("section", "buy/Buy.jsp");
		return "Index";
	}
	
	@RequestMapping(value="gobuyPage_FromDetail", method={RequestMethod.GET, RequestMethod.POST})
	public String gobuyPage_FromDetail(HttpSession session, Model model, HttpServletRequest request){
		// 구매 페이지로 이동 (상품 상세페이지에서 구매시 이동하는 맵핑)
		String m_id = "";
		ArrayList<CartDTO> payList = new ArrayList<>();
		CartDTO cdto = new CartDTO();
		if(session.getAttribute("u_id") != null){		
			m_id =  (String)session.getAttribute("u_id");
		}else{
			m_id = "";
		}
		
		cdto.setC_cnt(Integer.parseInt(request.getParameter("c_cnt")));
		cdto.setI_code(request.getParameter("i_code"));
		cdto.setI_img(request.getParameter("i_img"));
		cdto.setI_name(request.getParameter("i_name"));
		cdto.setI_price(Integer.parseInt(request.getParameter("i_price")));
		
		payList.add(cdto);
		
		model.addAttribute("payList", payList);
		
		MemberDTO mdto =  Mservice.viewMember(m_id);
		model.addAttribute("mdto", mdto);
		
		model.addAttribute("section", "buy/Buy.jsp");
		return "Index";
	}
}
