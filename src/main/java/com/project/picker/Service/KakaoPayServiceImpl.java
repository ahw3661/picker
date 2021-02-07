package com.project.picker.Service;

import java.net.URI;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.project.mapper.BuyMapperDAO;
import com.project.picker.DTO.BuyDTO;
import com.project.picker.DTO.BuyitemDTO;
import com.project.picker.DTO.KakaoPayApprovalDTO;
import com.project.picker.DTO.KakaoPayReadyDTO;
import com.project.picker.DTO.PointDTO;

@Service
public class KakaoPayServiceImpl implements KakaoPayService {

	private static final String HOST = "https://kapi.kakao.com";

	private KakaoPayReadyDTO kakaoPayReadyDTO;
	private KakaoPayApprovalDTO kakaoPayApprovalDTO;
	
	@Inject
	BuyMapperDAO bdao;

	String m_id;
	String tot;
	String getB_code;
	int b_code; // 쿼리에서 select 한 주문코드의 맥스변화 결과값 
	String [] i_code;
	String [] item_name;
	String [] sum_quantity;
	String [] item_price;
	int quantity = 0;
	int plus_point;
	int minus_point;
	
	
	@Override
	public String kakaoPayReady(HttpServletRequest request,HttpSession session, BuyDTO bdto) {
		
		String mincode; // 최소 코드
		String maxcode; // 최대 코드
		
		Date today = new Date();
		DateFormat format = new SimpleDateFormat("yyMMdd");
		System.out.println(format.format(today));

		mincode = format.format(today) + "0000";
		maxcode = format.format(today) + "9999";
		
		int minb_code = Integer.parseInt(mincode);
		int maxb_code = Integer.parseInt(maxcode);
		
		b_code = bdao.maxCode(minb_code, maxb_code);

		bdto.setB_code(b_code);
		getB_code = Integer.toString(bdto.getB_code());
	    // ------------------------------------------ 여기까지 주문코드 구해서 dto에 set 후 형변환
	    
		if(session.getAttribute("u_id") != null){
			m_id = (String) session.getAttribute("u_id");
		}else{
			m_id = "nonmember";
		}

	    tot = request.getParameter("tot");
	    int total = Integer.valueOf(tot);
	    int price = (int)(total*0.1);
	    String vat_amount = Integer.toString(price);
	    
		System.out.println("ready 이동");
	    RestTemplate restTemplate = new RestTemplate();
	 
	    // 서버로 요청할 Header
	    HttpHeaders headers = new HttpHeaders();
	    headers.add("Authorization", "KakaoAK " + "9c75223fdf027077aaee5d2e0c48507b");
	    headers.add("Accept", "application/x-www-form-urlencoded;charset=UTF-8");
	    headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");
	    
	    // 서버로 요청할 Body
	    MultiValueMap<String, Object> params = new LinkedMultiValueMap<String, Object>();
	    params.add("cid", "TC0ONETIME"); // 가맹점 코드, 10자 (테스트코드)
	    params.add("partner_order_id", getB_code); // 가맹점 주문번호, 최대 100자 -> 주문번호 (b_code) 202101160001
	    params.add("partner_user_id", m_id); // 가맹점 회원 id, 최대 100자 -> 회원ID (m_id)
	    
	    item_name = request.getParameterValues("item_name");
	    sum_quantity = request.getParameterValues("quantity");
	    i_code = request.getParameterValues("item_code");
	    item_price = request.getParameterValues("price_hidden");
	    
	    for(int i=0;i<item_name.length;i++) {
	    	int cnt = Integer.valueOf(sum_quantity[i].toString());
	    	quantity = quantity + cnt;
	    }
	    String sum_cnt = Integer.toString(quantity);
	    
	    // ↓ point메서드를 위한 request값
	    
	    plus_point = +Integer.parseInt(request.getParameter("saving_point")); 
	    minus_point = -Integer.parseInt(request.getParameter("usePoint"));
	    System.out.println(plus_point);
	    System.out.println(minus_point);
	    
	    // 상품수량 합계와 상품명 여러개 받아오기위한 for문 작성
	    params.add("item_name", item_name[0] + " 외 "+ (quantity-1)+"개"); // 상품명, 최대 100자 -> 상품명(i_name)
	    params.add("quantity", sum_cnt); // 상품 수량 (c_cnt)
	    params.add("total_amount", tot); // 상품 총액
	    params.add("tax_free_amount", "0"); // 상품 비과세 금액
	    params.add("vat_amount", vat_amount); // 상품 부과세 금액
	    params.add("approval_url", "http://localhost:8090/picker/kakaoPaySuccess"); // 결제 성공 시 redirect url, 최대 255자
	    params.add("cancel_url", "http://localhost:8090/picker/kakaoPayCancel"); // 결제 취소 시 redirect url, 최대 255자
	    params.add("fail_url", "http://localhost:8090/picker/kakaoPaySuccessFail"); // 결제 실패 시 redirect url, 최대 255자
	 
		HttpEntity<MultiValueMap<String, Object>> body = new HttpEntity<MultiValueMap<String, Object>>(params, headers);
		System.out.println("body : "+body);
	    try {
	    		System.out.println("try");
	    	kakaoPayReadyDTO = restTemplate.postForObject(new URI(HOST + "/v1/payment/ready"), body, KakaoPayReadyDTO.class);
	    		System.out.println("VO : " + kakaoPayReadyDTO.getTid());
	    	
	    	//성공시           
	        return kakaoPayReadyDTO.getNext_redirect_pc_url();
	        
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
	    //실패시
	    return "/Pay";
	}

	@Override
	public KakaoPayApprovalDTO kakaoPayInfo(HttpServletRequest request, HttpSession session, BuyDTO bdto, String pg_token) {
		System.out.println("KakaoPayInfoVO............................................");
		System.out.println("-----------------------------");
		
		if(session.getAttribute("u_id") != null){
			m_id = (String) session.getAttribute("u_id");
		}else{
			m_id = "nonmember";
		}
		
		RestTemplate restTemplate = new RestTemplate();
	 
		// 서버로 요청할 Header
	    HttpHeaders headers = new HttpHeaders();
	    headers.add("Authorization", "KakaoAK " + "9c75223fdf027077aaee5d2e0c48507b");
	    headers.add("Accept", "application/x-www-form-urlencoded;charset=UTF-8");
	    headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");
	 
	    // 서버로 요청할 Body
	    
	    System.out.println(kakaoPayReadyDTO.getTid());
	    System.out.println(getB_code);
	    System.out.println(m_id);
	    System.out.println(pg_token);
	    System.out.println(tot);
	    
	    MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
	    params.add("cid", "TC0ONETIME");
	    params.add("tid", kakaoPayReadyDTO.getTid());
	    params.add("partner_order_id", getB_code);
	    params.add("partner_user_id", m_id);
	    params.add("pg_token", pg_token);
	    params.add("total_amount", tot);
	    
	    HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<MultiValueMap<String, String>>(params, headers);
	   
		    try {
		    	System.out.println(body);
				kakaoPayApprovalDTO = restTemplate.postForObject(new URI(HOST + "/v1/payment/approve"), body, KakaoPayApprovalDTO.class);
			    System.out.println("여기 : " + kakaoPayApprovalDTO);
			      
			    return kakaoPayApprovalDTO;
			        
		    } catch (Exception e) {
			    	e.printStackTrace();
		    }
		    
	    return null;
	}
	
	
	@Override
	public void insertBuyitems(BuyDTO bdto, HttpServletRequest request, HttpSession session, Model model) {
		// picker_buyitem에 insert할 dto에 set 작업----------------------------------------------------		
		
		ArrayList<BuyitemDTO> arr = new ArrayList<>();	
		for(int i=0; i<item_name.length; i++) {
			BuyitemDTO bidto = new BuyitemDTO();
			int bi_cnt = Integer.valueOf(sum_quantity[i].toString());
			int i_price = Integer.valueOf(item_price[i].toString());
				bidto.setB_code(b_code);
				bidto.setI_img("imgs");
				bidto.setI_code(i_code[i]);
				bidto.setI_name(item_name[i]);
				bidto.setBi_cnt(bi_cnt);
				bidto.setI_price(i_price);
				arr.add(bidto);
		}
		for(int i=0;i<arr.size();i++) {
			bdao.insertBuyitem(arr.get(i));
		}
		
		
		// picker_buy에 insert할 dto에 set 작업----------------------------------------------------
		bdto.setB_code(b_code);
		bdto.setB_order_name(request.getParameter("b_order_name"));
		bdto.setB_order_phone(request.getParameter("b_order_phone"));
		bdto.setB_order_email(request.getParameter("b_order_email"));
		bdto.setB_take_name(request.getParameter("b_take_name"));
		bdto.setB_take_email(request.getParameter("b_take_email"));
		bdto.setB_take_phone(request.getParameter("b_take_phone"));
		bdto.setB_take_zipcode(request.getParameter("b_take_zipcode"));
		bdto.setB_take_roadaddr(request.getParameter("b_take_roadaddr"));
		bdto.setB_take_detailaddr(request.getParameter("b_take_detailaddr"));
		bdto.setM_id(m_id);
		bdto.setB_price(Integer.parseInt(request.getParameter("b_price")));

		bdao.insertBuy(bdto);
		
	}

	@Override
	public void insertPlusPoint(PointDTO pdto) {
		pdto.setM_id(m_id);
		String p_history;
		int p_point = plus_point;
		if(p_point<0) {
			p_history = "사용";
		}else {
			p_history = "적립";
		}
		
		
		pdto.setP_history(p_history);
		pdto.setP_point(p_point);
		pdto.setB_code(b_code);
	}

	

}
