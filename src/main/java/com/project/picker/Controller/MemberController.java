package com.project.picker.Controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import com.project.picker.DTO.BuyDTO;
import com.project.picker.DTO.BuyitemDTO;
import com.project.picker.DTO.CartDTO;
import com.project.picker.DTO.LoginDTO;
import com.project.picker.DTO.MemberDTO;
import com.project.picker.DTO.PagingDTO;
import com.project.picker.DTO.PointDTO;
import com.project.picker.Service.CartService;
import com.project.picker.Service.MemberService;

@Controller
public class MemberController {

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Inject
	MemberService mservice;
	
	@Inject
	CartService Cservice;
	
	// 로그인 화면
	@RequestMapping(value="loginPage", method= {RequestMethod.GET, RequestMethod.POST})
	public String loginPage(HttpSession session, Model model, HttpServletRequest request) {
    	String referrer = request.getHeader("referer");
    	
    	if(referrer == null) {
    		model.addAttribute("msg", "잘못된 접근입니다.");
    	}else {
    		
    		if(referrer.equals("http://localhost:8090/picker/loginPage") || referrer.equals("http://localhost:8090/picker/logout")) {
        		session.setAttribute("url", null);
        	}else {
        		session.setAttribute("url", referrer);
        	}
    	}
		model.addAttribute("section", "login/Login.jsp");
		return "Index";
	}
	
	// 로그인
	@SuppressWarnings("unchecked")
	@RequestMapping(value="login", method= {RequestMethod.GET, RequestMethod.POST}, produces="application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> login(LoginDTO ldto, @RequestParam boolean log, Model model, 
			HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		MemberDTO mdto = mservice.loginCheck(ldto);
		Map<String, Object> map = new HashMap<>();
		
		if(mdto != null) {
			logger.info(">>> 입력 정보 일치 사용자 존재 로그인 진행");
			session.setAttribute("login", mdto);
			session.setAttribute("u_id", mdto.getM_id());
			session.setAttribute("u_name", mdto.getM_name());
			session.setAttribute("u_type", mdto.getM_type());
			int count = Cservice.totalCartCount(mdto.getM_id());
			session.setAttribute("cnt", count);
			//session.setMaxInactiveInterval(10*2);
			
			if(log == true) { // 로그인 상태 유지에 체크를 한 경우 쿠키 생성
				String sessionId = (String)session.getAttribute("u_id");
				Cookie cookie = new Cookie("loginCookie", sessionId); // 쿠키에 세션 id 저장
				cookie.setPath("/"); // 쿠키를 찾을 경로를 context 경로로 변경
				int limitTime = 60 * 60 * 24 * 7; // 7일
				cookie.setMaxAge(limitTime); // 쿠키 유효시간 지정. 단위는 초
				response.addCookie(cookie); // 쿠키 적용
				
				Date sessionLimit = new Date(System.currentTimeMillis() + (1000 * limitTime)); // currentTimeMillis()가 1/1000초 단위. 1000 곱하는 계산 필요
				mservice.loginRemember(sessionId, sessionLimit, mdto.getM_id()); // 현재 세션 id와 유효시간 DB 저장
				logger.info(">>> 로그인 유지 쿠키 생성");
			}
			
			if(session.getAttribute("sessionList") != null) {	
				ArrayList<CartDTO> list = (ArrayList<CartDTO>)session.getAttribute("sessionList");
				for(int i=0;i<list.size();i++) {
					list.get(i).setM_id(mdto.getM_id());
					if(Cservice.cartCount(list.get(i))==0) {
						Cservice.changeId(mdto.getM_id(),list.get(i).getC_num());
					}else{
						Cservice.updateCnt(list.get(i).getC_cnt(),list.get(i).getI_code(), mdto.getM_id());
						System.out.println(mdto.getM_id());
						System.out.println(list.get(i).getC_cnt());
						System.out.println(list.get(i).getI_code());
						System.out.println(list.get(i).getC_num());
						Cservice.delOriginList(list.get(i).getC_num());
					}
				}
				session.setAttribute("sessionList", null);
			}
			logger.info(">>> 로그인 성공");
		}else {
			logger.info(">>> 입력 정보 일치 사용자 비존재");
			map.put("msg", "fail");
			logger.info(">>> 로그인 실패");
		}
		return map;
	}
	
	// 아이디 찾기/비밀번호 찾기 화면
	@RequestMapping(value="findIdPw", method= {RequestMethod.GET, RequestMethod.POST})
	public String findIdPw(Model model) {
		model.addAttribute("section", "login/FindIdPw.jsp");
		return "Index";
	}
	
	// 아이디 찾기 결과 화면
	@RequestMapping(value="findId", method= {RequestMethod.GET, RequestMethod.POST})
	public String findId(MemberDTO mdto, Model model) {
		if(mservice.findId(mdto) == null) {
			logger.info(">>> 존재하지 않는 아이디");
			model.addAttribute("msg", "일치하는 정보가 없습니다.<br>입력하신 정보를 확인 후 다시 시도해주세요.");
		}else {
			logger.info(">>> 존재하는 아이디");
			model.addAttribute("m_id", mservice.findId(mdto));
		}
		return "login/FindId";
	}
	
	// 비밀번호 찾기 결과 화면
	@RequestMapping(value="findPw", method= {RequestMethod.GET, RequestMethod.POST})
	public String findPw(@RequestParam String m_id, Model model) {
		if(mservice.findPw(m_id) == null) {
			logger.info(">>> 존재하지 않는 비밀번호");
			model.addAttribute("msg", "일치하는 정보가 없습니다.<br>입력하신 정보를 확인 후 다시 시도해주세요.");
		}else {
			logger.info(">>> 존재하는 비밀번호");
			model.addAttribute("m_password", mservice.findPw(m_id));
		}
		return "login/FindPw";
	}
	
	// 로그아웃
	@RequestMapping(value="logout", method= {RequestMethod.GET, RequestMethod.POST})
	public String logout(HttpSession session, Model model, HttpServletRequest request, HttpServletResponse response) {
		Cookie loginCookie = WebUtils.getCookie(request, "loginCookie"); // 생성했던 쿠키
		
		if(loginCookie != null) { // 쿠키가 존재하는 경우 쿠키 제거
			String sId = (String)session.getAttribute("u_id");
			String sessionId = "none"; // DB 세션 id 초기화에 사용 
			MemberDTO mdto = new MemberDTO();
			mdto.setM_id(sId);
			loginCookie.setPath("/");
			loginCookie.setMaxAge(0); // 유효시간 0으로 초기화
			response.addCookie(loginCookie); // 쿠키 적용
			
			Date date = new Date(System.currentTimeMillis()); // 현재시간
			mservice.loginRemember(sessionId, date, mdto.getM_id()); // 유효시간을 현재시간으로 변경
			logger.info(">>> 로그아웃 쿠키 제거");
		}
		// 세션 초기화
		session.setAttribute("u_id", null);
		session.setAttribute("u_name", null);
		session.setAttribute("u_type", null);
		session.setAttribute("cnt", null);
		logger.info(">>> 세션 초기화");
		
		model.addAttribute("section", "Section.jsp");
		return "Index";
	}
	
	// 비회원 주문조회
	@RequestMapping(value="noneSearch", method= {RequestMethod.GET, RequestMethod.POST}, produces="application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> noneSearch(BuyDTO bdto, Model model, HttpSession session) {
		BuyDTO bdto1 = mservice.buyCheck(bdto);
		Map<String, Object> map = new HashMap<>();
		
		if(bdto1 != null) {
			logger.info(">>> 입력 정보 일치 구매내역 존재");
			session.setAttribute("u_code", bdto1.getB_code());
			session.setAttribute("u_phone", bdto1.getB_order_phone());
			logger.info(">>> 조회 성공");
		}else {
			logger.info(">>> 입력 정보 일치 구매내역 비존재");
			map.put("msg", "fail");
			logger.info(">>> 조회 실패");
		}
		return map;
	}
	
	// 비회원 조회 화면
	@RequestMapping(value="nonePage", method= {RequestMethod.GET, RequestMethod.POST})
	public String nonePage(HttpSession session, Model model, HttpServletRequest request) {
		String b_code = (String)session.getAttribute("u_code");
		String b_order_phone = (String)session.getAttribute("u_phone");
		BuyDTO bdto = mservice.noneOneBuyInfo(b_code, b_order_phone);
		ArrayList<BuyitemDTO> bidto = mservice.noneOneBuyItemInfo(b_code);
		int total = mservice.sumBuyPrice(b_code);
		model.addAttribute("bdto", bdto);
		model.addAttribute("bidto", bidto);
		model.addAttribute("total", total);
		model.addAttribute("section", "NonePage.jsp");
		return "Index";
	}
	
	// 비회원 조회 화면 닫기
	@RequestMapping(value="closed", method= {RequestMethod.GET, RequestMethod.POST})
	public String goIndex(HttpSession session) {
		session.setAttribute("u_code", null);
		session.setAttribute("u_phone", null);
		return "Index";
	}
	
	// 잘못된 접근
	@RequestMapping(value="wrongAccess", method= {RequestMethod.GET, RequestMethod.POST})
	public String wrongAccess(Model model) {
		model.addAttribute("msg", "잘못된 접근입니다.");
		model.addAttribute("loc", 3);
		return "Error";
	}
	
	// 에러 메시지
	@RequestMapping(value="errorPage", method= {RequestMethod.GET, RequestMethod.POST})
	public String errorPage(Model model) {
		model.addAttribute("msg", "로그인 후 이용 가능합니다.");
		model.addAttribute("loc", 1);
		return "Error";
	}
	
	// 마이페이지 화면
	@RequestMapping(value="myPage", method= {RequestMethod.GET, RequestMethod.POST})
	public String myPage(Model model, HttpSession session) {
		String m_id = (String)session.getAttribute("u_id");
		int point = mservice.onePoint(m_id);
		logger.info(">>> 회원 포인트 : "+point);
		model.addAttribute("point", point);
		model.addAttribute("section", "myPage/MyPage.jsp");
		return "Index";
	}
	
	// 회원별 주문 목록
	@RequestMapping(value="buyInfo", method= {RequestMethod.GET, RequestMethod.POST})
	public String buyInfo(@RequestParam int pageNum, Model model, HttpSession session) {
		String m_id = (String)session.getAttribute("u_id");
		ArrayList<BuyitemDTO> buyitem = mservice.buyItem();
		int cnt = mservice.getBuyCount(m_id);
		
		if(cnt > 0) {
			PagingDTO pgdto = new PagingDTO(pageNum, cnt, 10, 5);
			model.addAttribute("pgdto", pgdto);
			List<BuyDTO> buylist = mservice.buyList(m_id, pgdto.getStartRow(), pgdto.getEndRow());
			model.addAttribute("buylist", buylist);
		}
		model.addAttribute("buyitem", buyitem);
		return "myPage/BuyInfo";
	}
	
	// 회원별 주문 상세
	@RequestMapping(value="buyInfoDetail", method= {RequestMethod.GET, RequestMethod.POST})
	public String buyInfoDetail(@RequestParam String b_code, @RequestParam int pageNum, Model model, HttpSession session) {
		String m_id = (String)session.getAttribute("u_id");
		BuyDTO bdto = mservice.oneBuyInfo(m_id, b_code);
		ArrayList<BuyitemDTO> bidto = mservice.oneBuyItemInfo(b_code);
		int total = mservice.sumBuyPrice(b_code);
		model.addAttribute("bdto", bdto);
		model.addAttribute("bidto", bidto);
		model.addAttribute("total", total);
		model.addAttribute("pageNum", pageNum);
		return "myPage/BuyInfoDetail";
	}
	
	// 회원별 주문취소 가능 주문 목록
	@RequestMapping(value="buyCancel", method= {RequestMethod.GET, RequestMethod.POST})
	public String buyCancel(Model model, HttpSession session) {
		String m_id = (String)session.getAttribute("u_id");
		ArrayList<BuyDTO> buyCancelList = mservice.buyCancelList(m_id);
		ArrayList<BuyitemDTO> buyitem = mservice.buyItem();
		model.addAttribute("buyCancelList", buyCancelList);
		model.addAttribute("buyitem", buyitem);
		return "myPage/BuyCancel";
	}
	
	// 회원별 주문 상세
	@RequestMapping(value="buyCancelDetail", method= {RequestMethod.GET, RequestMethod.POST})
	public String buyCancelDetail(@RequestParam String b_code, Model model, HttpSession session) {
		String m_id = (String)session.getAttribute("u_id");
		BuyDTO bdto = mservice.oneBuyInfo(m_id, b_code);
		ArrayList<BuyitemDTO> bidto = mservice.oneBuyItemInfo(b_code);
		int total = mservice.sumBuyPrice(b_code);
		model.addAttribute("bdto", bdto);
		model.addAttribute("bidto", bidto);
		model.addAttribute("total", total);
		return "myPage/BuyCancelDetail";
	}
	
	// 내 정보 수정 화면
	@RequestMapping(value="myInfo", method= {RequestMethod.GET, RequestMethod.POST})
	public String myInfo(HttpSession session, Model model) {
		String m_id = (String)session.getAttribute("u_id");
		model.addAttribute("mdto", mservice.viewMember(m_id));
		return "myPage/MyInfo";
	}
	
	// 내 정보 수정
	@RequestMapping(value="myInfoUpdate", method= {RequestMethod.GET, RequestMethod.POST}, produces="application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> myInfoUpdate(MemberDTO mdto, Model model) {
		Map<String, Object> map = new HashMap<>();
		
		if(mservice.pwCheck(mdto) == 0) {
			logger.info(">>> 비밀번호 불일치");
			map.put("msg", "fail");
		}else {
			logger.info(">>> 비밀번호 일치 정보수정 진행");
			mservice.updateMember(mdto);
		}
		return map;
	}
	
	// 회원별 포인트 목록
	@RequestMapping(value="pointInfo", method= {RequestMethod.GET, RequestMethod.POST})
	public String pointInfo(@RequestParam int pageNum, Model model, HttpSession session) {
		String m_id = (String)session.getAttribute("u_id");
		int cnt = mservice.getPointCount(m_id);
		
		if(cnt > 0) {
			PagingDTO pgdto = new PagingDTO(pageNum, cnt, 10, 5); // 10 10
			model.addAttribute("pgdto", pgdto);
			List<PointDTO> list = mservice.pointList(m_id, pgdto.getStartRow(), pgdto.getEndRow());
			model.addAttribute("list", list);
		}
		return "myPage/PointInfo";
	}
	
	// 회원탈퇴 화면
	@RequestMapping(value="withdrawPage", method= {RequestMethod.GET, RequestMethod.POST})
	public String withdrawPage() {
		return "myPage/WithdrawPage";
	}
	
	// 회원탈퇴
	@RequestMapping(value="withdraw", method= {RequestMethod.GET, RequestMethod.POST}, produces="application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> withdraw(MemberDTO mdto, Model model, HttpSession session) {
		String m_id = (String)session.getAttribute("u_id");
		mdto.setM_id(m_id);
		Map<String, Object> map = new HashMap<>();
		
		if(mservice.pwCheck(mdto) == 0) {
			logger.info(">>> 비밀번호 불일치");
			map.put("msg", "fail");
		}else {
			logger.info(">>> 비밀번호 일치 탈퇴 진행");
			mservice.deleteMember(m_id);
			session.setAttribute("u_id", null);
			session.setAttribute("u_name", null);
			session.setAttribute("u_type", null);
		}
		return map;
	}
	
}
