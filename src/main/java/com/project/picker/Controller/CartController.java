package com.project.picker.Controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.picker.DTO.CartDTO;
import com.project.picker.Service.CartService;

@SuppressWarnings("unchecked")
@Controller
public class CartController {
	
	@Inject
	CartService Cservice;
	
	@ResponseBody
	@RequestMapping(value="insertCart", method={RequestMethod.GET, RequestMethod.POST}, produces="application/json; charset=utf-8")
	public Map<String, Object> insertCart(CartDTO cdto, Model model, HttpServletRequest request, HttpSession session){
		Map<String, Object> map = new HashMap<>();
		Cservice.insertCart(cdto, session);
		map.put("msg", "suc");
		return map;
	}
	
	@RequestMapping(value="cartList", method={RequestMethod.GET, RequestMethod.POST})
	public String cartList(Model model, HttpSession session, HttpServletRequest request){
		//장바구니 리스트 출력
		String m_id = "";
		int count = 0;
		ArrayList<CartDTO> cartlist = new ArrayList<>();
		if(session.getAttribute("u_id") != null) {		
			m_id =  (String)session.getAttribute("u_id");
			System.out.println("sessionid3 : "+ session.getAttribute("u_id"));
			
			cartlist = Cservice.allCartList(m_id); // 회원아이디별 전체 카트리스트 출력
			count = Cservice.totalCartCount(m_id); // 회원아이디별 장바구니 전체 카운트수 출력
		}else {
			m_id = "";
			System.out.println("sessionid4 : "+ session.getAttribute("u_id"));
			if(session.getAttribute("sessionList") != null) {
				cartlist = (ArrayList<CartDTO>)session.getAttribute("sessionList");
				count = cartlist.size();
			}
		}
		session.setAttribute("cnt", count);
		model.addAttribute("cartlist", cartlist);
		model.addAttribute("section", "Cart.jsp");
		
		return "Index";
	}
	
	@RequestMapping(value="delOneCartList", method={RequestMethod.GET, RequestMethod.POST})
	public String delOneCartList(HttpSession session, Model model, HttpServletRequest request){
		// 장바구니 선택 삭제
		String m_id = "";
		int count = 0;
		ArrayList<CartDTO> cartlist = new ArrayList<>();
		if(session.getAttribute("u_id") != null) {		
			m_id =  (String)session.getAttribute("u_id");
			String [] i_code = request.getParameterValues("i_code[]");
			for(int i = 0;i < i_code.length;i++) {
				Cservice.delOne(m_id, i_code[i]);
			}
			cartlist = Cservice.allCartList(m_id); // 회원아이디별 전체 카트리스트 출력
			count = Cservice.totalCartCount(m_id); // 회원아이디별 장바구니 전체 카운트수 출력
		}else {
			m_id = "";
			if(session.getAttribute("sessionList") != null) {
				cartlist = (ArrayList<CartDTO>)session.getAttribute("sessionList");
				String [] num = request.getParameterValues("c_num[]");
				int c_num = 0;
				for(int i=0;i<num.length;i++) {
					c_num = Integer.parseInt(num[i]);
					Cservice.delOne_Non(c_num);
						for(int j=0;j<cartlist.size();j++) {
							if(c_num == cartlist.get(j).getC_num()) {
								cartlist.remove(j);
							}
						}
				}
				session.setAttribute("sessionList", cartlist);
				count = cartlist.size();
			}
		}
		
		model.addAttribute("cartlist", cartlist);
		session.setAttribute("cnt", count);
		return "Cart";
	}
	
	@RequestMapping(value="delAllCartList", method={RequestMethod.GET, RequestMethod.POST})
	public String delAllCartList(Model model, HttpSession session){
		// 장바구니 전체 삭제
		String m_id = "";	
		int count=0;
		ArrayList<CartDTO> cartlist = new ArrayList<>();
		if(session.getAttribute("u_id") != null) {		
			m_id =  (String)session.getAttribute("u_id");
			Cservice.delAll(m_id);
			cartlist = Cservice.allCartList(m_id);
			count = Cservice.totalCartCount(m_id);// 회원아이디별 장바구니 전체 카운트수 출력
		}else {
			m_id = "";
			if(session.getAttribute("sessionList") != null) {
				cartlist = (ArrayList<CartDTO>)session.getAttribute("sessionList");
				for(int i=0;i<cartlist.size();i++) {
					Cservice.delAll_Non(cartlist.get(i).getC_num());
				}
				cartlist = null;
				session.setAttribute("sessionList",null);
				cartlist = (ArrayList<CartDTO>)session.getAttribute("sessionList");
				count = 0; //cartlist.size();가 null로 떠서 0으로 처리
			}
		}
		model.addAttribute("cartlist", cartlist);
		session.setAttribute("cnt", count);
		return "Cart";
	}
	
	@ResponseBody 
	@RequestMapping(value="updateCartCnt", method={RequestMethod.GET, RequestMethod.POST})
	public Map<String, Object> updateCartCnt(HttpServletRequest request, Model model, HttpSession session){
		// 장바구니 수량업데이트 json값 map에 넣어서 전송
		Map<String, Object> map = new HashMap<>();
		CartDTO cdto = new CartDTO();
		cdto.setC_cnt(Integer.parseInt(request.getParameter("c_cnt")));
		cdto.setM_id(request.getParameter("m_id"));
		cdto.setI_code(request.getParameter("i_code"));
		cdto.setC_num(Integer.parseInt(request.getParameter("c_num")));
		
		System.out.println("수량 : "+ cdto.getC_cnt());
		System.out.println("코드 : "+cdto.getI_code());
		System.out.println("아이디 : "+cdto.getM_id());
		System.out.println("넘버 : "+cdto.getC_num());
		
		if(session.getAttribute("u_id")!= null) {
			Cservice.plmaupdateCnt(cdto);
		}else{
			Cservice.plmaupdateCnt_Non(cdto);
			CartDTO cdto1 = Cservice.getCartNum(cdto.getC_num());
			ArrayList<CartDTO> list;
			if(session.getAttribute("sessionList")==null) {
				list = new ArrayList<>();
			}else {
				list = (ArrayList<CartDTO>)session.getAttribute("sessionList");
			}
			for(int i = 0; i < list.size(); i++) {
				if(list.get(i).getC_num() == cdto1.getC_num()) {
					list.remove(i);
				}
			}
			list.add(cdto1);
		}
		
		map.put("cnt", cdto.getC_cnt());
		return map;
		
	}
	
}
