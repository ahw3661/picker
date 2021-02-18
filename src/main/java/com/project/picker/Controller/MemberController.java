package com.project.picker.Controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    		// referrer 제외 페이지
    		if(referrer.equals("http://localhost:8090/picker/loginPage") || referrer.equals("http://localhost:8090/picker/logout")
    				|| referrer.equals("http://localhost:8090/picker/joinAgree") || referrer.equals("http://localhost:8090/picker/joinWrite") 
    				|| referrer.equals("http://localhost:8090/picker/findIdPw") || referrer.equals("http://localhost:8090/picker/gobuyPage_FromDetail") 
    				|| referrer.equals("http://localhost:8090/picker/gobuyPage_FromCart") || referrer.equals("http://localhost:8090/picker/wrongAccess")
    				|| referrer.equals("http://localhost:8090/picker/errorPage")) {
        		session.setAttribute("url", null);
        	}else if((referrer.equals("http://localhost:8090/picker/wrongAccess") && (String)session.getAttribute("url") != null)
    				|| (referrer.equals("http://localhost:8090/picker/errorPage") && (String)session.getAttribute("url") != null)) {
        		session.setAttribute("url", null);
        	}else {
        		session.setAttribute("url", referrer); // 제외되지 않은 페이지
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
			// 입력 정보 일치 사용자 존재 로그인 진행
			// 로그인 성공
			session.setAttribute("login", mdto);
			session.setAttribute("u_id", mdto.getM_id());
			session.setAttribute("u_name", mdto.getM_name());
			session.setAttribute("u_type", mdto.getM_type());
			int count = Cservice.totalCartCount(mdto.getM_id());
			session.setAttribute("cnt", count);
			
			if(log == true) { // 로그인 상태 유지에 체크를 한 경우 쿠키 생성
				String sessionId = (String)session.getAttribute("u_id");
				Cookie cookie = new Cookie("loginCookie", sessionId); // 쿠키에 세션 id 저장
				cookie.setPath("/"); // 쿠키를 찾을 경로를 context 경로로 변경
				int limitTime = 60 * 60 * 24 * 7; // 7일
				cookie.setMaxAge(limitTime); // 쿠키 유효시간 지정. 단위는 초
				response.addCookie(cookie); // 쿠키 적용
				
				Date sessionLimit = new Date(System.currentTimeMillis() + (1000 * limitTime)); // currentTimeMillis()가 1/1000초 단위. 1000 곱하는 계산 필요
				mservice.loginRemember(sessionId, sessionLimit, mdto.getM_id()); // 현재 세션 id와 유효시간 DB 저장
			}
			
			if(session.getAttribute("sessionList") != null) {	
				ArrayList<CartDTO> list = (ArrayList<CartDTO>)session.getAttribute("sessionList");
				for(int i=0;i<list.size();i++) {
					list.get(i).setM_id(mdto.getM_id());
					if(Cservice.cartCount(list.get(i))==0) {
						Cservice.changeId(mdto.getM_id(),list.get(i).getC_num());
					}else{
						Cservice.updateCnt(list.get(i).getC_cnt(),list.get(i).getI_code(), mdto.getM_id());
						Cservice.delOriginList(list.get(i).getC_num());
					}
				}
				session.setAttribute("sessionList", null);
			}
		}else {
			// 입력 정보 일치 사용자 비존재
			// 로그인 실패
			map.put("msg", "fail"); // 로그인 실패 시 메시지 전달
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
			// 존재하지 않는 아이디
			model.addAttribute("msg", "일치하는 정보가 없습니다.<br>입력하신 정보를 확인 후 다시 시도해주세요.");
		}else {
			// 존재하는 아이디
			model.addAttribute("m_id", mservice.findId(mdto));
		}
		return "login/FindId";
	}
	
	// 비밀번호 찾기 결과 화면
	@RequestMapping(value="findPw", method= {RequestMethod.GET, RequestMethod.POST})
	public String findPw(@RequestParam String m_id, Model model) {
		if(mservice.findPw(m_id) == null) {
			// 존재하지 않는 비밀번호
			model.addAttribute("msg", "일치하는 정보가 없습니다.<br>입력하신 정보를 확인 후 다시 시도해주세요.");
		}else {
			// 존재하는 비밀번호
			model.addAttribute("m_password", mservice.findPw(m_id));
		}
		return "login/FindPw";
	}
	
	// 로그아웃
	@RequestMapping(value="logout", method= {RequestMethod.GET, RequestMethod.POST})
	public String logout(HttpSession session, Model model, HttpServletRequest request, HttpServletResponse response) {
		Cookie loginCookie = WebUtils.getCookie(request, "loginCookie"); // 생성했던 쿠키
		
		if(loginCookie != null) { // 쿠키가 존재하는 경우 쿠키 제거
			// 로그아웃 시 쿠키 제거
			String sId = (String)session.getAttribute("u_id");
			String sessionId = "none"; // DB 세션 id 초기화에 사용 
			MemberDTO mdto = new MemberDTO();
			mdto.setM_id(sId);
			loginCookie.setPath("/");
			loginCookie.setMaxAge(0); // 유효시간 0으로 초기화
			response.addCookie(loginCookie); // 쿠키 적용
			
			Date date = new Date(System.currentTimeMillis()); // 현재시간
			mservice.loginRemember(sessionId, date, mdto.getM_id()); // 유효시간을 현재시간으로 변경
		}
		// 세션 초기화
		session.setAttribute("u_id", null);
		session.setAttribute("u_name", null);
		session.setAttribute("u_type", null);
		session.setAttribute("cnt", null);
		
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
			// 입력 정보 일치 구매내역 존재
			session.setAttribute("u_code", bdto1.getB_code());
			session.setAttribute("u_phone", bdto1.getB_order_phone());
		}else {
			// 입력 정보 일치 구매내역 비존재
			map.put("msg", "fail"); // 구매내역 비존재 시 메시지 전달
		}
		return map;
	}
	
	// 비회원 조회 결과 화면
	@RequestMapping(value="nonePage", method= {RequestMethod.GET, RequestMethod.POST})
	public String nonePage(HttpSession session, Model model, HttpServletRequest request) {
		int b_code = (int)session.getAttribute("u_code");
		String b_order_phone = (String)session.getAttribute("u_phone");
		BuyDTO bdto = mservice.noneOneBuyInfo(b_code, b_order_phone);
		ArrayList<BuyitemDTO> bidto = mservice.noneOneBuyItemInfo(b_code);
		int total = mservice.sumBuyPrice(b_code);
		Integer point = mservice.usePoint(b_code);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yy-MM-dd");
		LocalDate now = LocalDate.now();
		LocalDate preDay = now.minusDays(1);
		String preday = dtf.format(preDay);
		String nowday = dtf.format(now);
		String bdate = bdto.getB_date();
		String b_date = bdate.substring(2, 10);
		int chk = -1; // 날짜 비교 체크키

		if(b_date.equals(preday) || b_date.equals(nowday)) {
			chk = 1; // 날짜가 같을 때
		}
		model.addAttribute("bdto", bdto);
		model.addAttribute("bidto", bidto);
		model.addAttribute("total", total);
		model.addAttribute("point", point);
		model.addAttribute("chk", chk);
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
	public String wrongAccess(Model model, HttpSession session) {
		return "redirect:myPage";
	}
	
	// 에러 메시지
	@RequestMapping(value="errorPage", method= {RequestMethod.GET, RequestMethod.POST})
	public String errorPage(Model model, HttpSession session) {
		model.addAttribute("msg", "로그인 후 이용 가능합니다.");
		model.addAttribute("loc", 1);
		return "Error";
	}
	
	// 에러 메시지
	@ResponseBody
	@RequestMapping("ajaxErrorPage")
	public Map<String, Object> ajaxErrorPage(){
		Map<String, Object> json = new HashMap<>();
		json.put("logError", true);
		return json;
	}
	
	// 마이페이지 화면
	@RequestMapping(value="myPage", method= {RequestMethod.GET, RequestMethod.POST})
	public String myPage(Model model, HttpSession session) {
		String m_id = (String)session.getAttribute("u_id");
		
		if((int)session.getAttribute("u_type") == 0) { // 관리자가 로그인 후 일반회원 페이지에 접근하려는 경우
			// 관리자는 마이페이지 접근 불가
			return "redirect:adminPage";
		}else {
			int point = mservice.onePoint(m_id);
			model.addAttribute("point", point);
			model.addAttribute("section", "myPage/MyPage.jsp");
			return "Index";
		}
		
	}
	
	// 회원별 주문 목록
	@RequestMapping(value="buyInfo", method= {RequestMethod.GET, RequestMethod.POST})
	public String buyInfo(@RequestParam(required=false) String start_date, @RequestParam(required=false) String end_date, 
			@RequestParam(defaultValue = "1") int pageNum, Model model, HttpSession session) {
		if(start_date == null && end_date == null) {
			// 주문조회 메뉴 클릭
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate now = LocalDate.now();
			LocalDate preMonth = now.minusMonths(3);
			
			start_date = dtf.format(preMonth);
			end_date = dtf.format(now);
		}
		String m_id = (String)session.getAttribute("u_id");
		ArrayList<BuyitemDTO> buyitem = mservice.buyItem();
		int cnt = mservice.getBuyCount(m_id, start_date, end_date);
		
		if(cnt > 0) {
			PagingDTO pgdto = new PagingDTO(pageNum, cnt, 10, 5);
			model.addAttribute("pgdto", pgdto);
			List<BuyDTO> buylist = mservice.buyList(m_id, start_date, end_date, pgdto.getStartRow(), pgdto.getEndRow());
			model.addAttribute("buylist", buylist);
		}
		model.addAttribute("start_date", start_date);
		model.addAttribute("end_date", end_date);
		model.addAttribute("buyitem", buyitem);
		model.addAttribute("cnt", cnt);
		return "myPage/BuyInfo";
	}
	
	// 회원별 주문 상세
	@RequestMapping(value="buyInfoDetail", method= {RequestMethod.GET, RequestMethod.POST})
	public String buyInfoDetail(@RequestParam int b_code, @RequestParam int pageNum, @RequestParam String start_date, 
			@RequestParam String end_date, Model model, HttpSession session) {
		String m_id = (String)session.getAttribute("u_id");
		BuyDTO bdto = mservice.oneBuyInfo(m_id, b_code);
		ArrayList<BuyitemDTO> bidto = mservice.oneBuyItemInfo(b_code);
		int total = mservice.sumBuyPrice(b_code);
		Integer point = mservice.usePoint(b_code);
		model.addAttribute("bdto", bdto);
		model.addAttribute("bidto", bidto);
		model.addAttribute("total", total);
		model.addAttribute("point", point);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("start_date", start_date);
		model.addAttribute("end_date", end_date);
		return "myPage/BuyInfoDetail";
	}
	
	// 회원별 주문취소 가능 주문 목록 및 구매취소 완료된 목록
	@RequestMapping(value="buyCancel", method= {RequestMethod.GET, RequestMethod.POST})
	public String buyCancel(@RequestParam(required=false) String start_date, @RequestParam(required=false) String end_date, 
			@RequestParam(defaultValue = "0") int pageNum, Model model, HttpSession session) {
		String m_id = (String)session.getAttribute("u_id");
		ArrayList<BuyitemDTO> buyitem = mservice.buyItem();
		
		if(pageNum == 0) {
			// 구매 취소 가능 목록
			int cnt = mservice.buyCancelListCount(m_id);
			ArrayList<BuyDTO> buyCancelList = mservice.buyCancelList(m_id);
			model.addAttribute("buyCancelList", buyCancelList);
			model.addAttribute("pageNum", pageNum);
			model.addAttribute("cnt", cnt);
		}else {
			if(start_date == null && end_date == null) {
				// 주문취소 메뉴의 주문취소 버튼 클릭
				DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate now = LocalDate.now();
				LocalDate preMonth = now.minusMonths(3); // 기본 날짜 3개월 범위 표기
				start_date = dtf.format(preMonth);
				end_date = dtf.format(now);
			}
			int cnt = mservice.getBuyCancelCount(m_id, start_date, end_date);
			
			if(cnt > 0) {
				// 구매 취소 완료 목록 존재
				PagingDTO pgdto = new PagingDTO(pageNum, cnt, 10, 5);
				model.addAttribute("pgdto", pgdto);
				List<BuyDTO> buyCancelContent = mservice.buyCancelContent(m_id, start_date, end_date, pgdto.getStartRow(), pgdto.getEndRow());
				model.addAttribute("buyCancelContent", buyCancelContent);
				model.addAttribute("pageNum", pageNum);
				model.addAttribute("start_date", start_date);
				model.addAttribute("end_date", end_date);
				model.addAttribute("cnt", cnt);
			}else {
				// 구매 취소 완료 목록 비존재
				model.addAttribute("pageNum", pageNum);
				model.addAttribute("start_date", start_date);
				model.addAttribute("end_date", end_date);
				model.addAttribute("cnt", cnt);
			}
		}
		model.addAttribute("buyitem", buyitem);
		return "myPage/BuyCancel";
	}
	
	// 회원별 주문취소 가능 및 주문취소 완료 주문 상세
	@RequestMapping(value="buyCancelDetail", method= {RequestMethod.GET, RequestMethod.POST})
	public String buyCancelDetail(@RequestParam int b_code, @RequestParam int pageNum, @RequestParam(required=false) String start_date, 
			@RequestParam(required=false) String end_date, Model model, HttpSession session) {
		String m_id = (String)session.getAttribute("u_id");
		BuyDTO bdto = null;
		ArrayList<BuyitemDTO> bidto = null;
		int total = 0;
		Integer point = new Integer(0);
		Date cancelDate = new Date();
		
		if(pageNum == 0) {
			// 주문취소 가능
			bdto = mservice.oneBuyInfo(m_id, b_code);
			bidto = mservice.oneBuyItemInfo(b_code);
			total = mservice.sumBuyPrice(b_code);
			point = mservice.usePoint(b_code);
		}else {
			// 주문취소 완료 건
			bdto = mservice.oneBuyCancelInfo(m_id, b_code);
			bidto = mservice.oneBuyCancelItemInfo(b_code);
			total = mservice.sumBuyPrice(b_code);
			point = mservice.preUsePoint(b_code);
			cancelDate = mservice.getCancelDate(b_code);
			model.addAttribute("start_date", start_date);
			model.addAttribute("end_date", end_date);
		}
		model.addAttribute("bdto", bdto);
		model.addAttribute("bidto", bidto);
		model.addAttribute("total", total);
		model.addAttribute("point", point);
		model.addAttribute("cancelDate", cancelDate);
		model.addAttribute("pageNum", pageNum);
		return "myPage/BuyCancelDetail";
	}
	
	// 회원 및 비회원 구매취소
	@RequestMapping(value="buyCancelRun", method= {RequestMethod.GET, RequestMethod.POST}, produces="application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> buyCancelRun(@RequestParam int b_code, Model model, HttpSession session) {
		Map<String, Object> map = new HashMap<>();
		String m_id = (String)session.getAttribute("u_id");
		
		if(m_id != null) {
			// 회원 구매 취소 진행
			mservice.buyState(b_code);
			mservice.buyCancelPoint(m_id, b_code);
			int m_point = mservice.sumPoint(m_id);
			mservice.updatePoint(m_id, m_point);
		}else {
			// 비회원 구매 취소 진행
			mservice.buyState(b_code);
		}
		map.put("msg", "success"); // 구매취소 성공 시 메시지 전달
		return map;
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
			// 비밀번호 불일치
			map.put("msg", "fail"); // 비밀번호 불일치 시 메시지 전달
		}else {
			// 비밀번호 일치 정보수정 진행
			mservice.updateMember(mdto);
		}
		return map;
	}
	
	// 회원별 포인트 목록
	@RequestMapping(value="pointInfo", method= {RequestMethod.GET, RequestMethod.POST})
	public String pointInfo(@RequestParam(defaultValue = "1") int pageNum, Model model, HttpSession session) {
		String m_id = (String)session.getAttribute("u_id");
		int cnt = mservice.getPointCount(m_id);
		
		if(cnt > 0) {
			PagingDTO pgdto = new PagingDTO(pageNum, cnt, 10, 5);
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
			// 비밀번호 불일치
			map.put("msg", "fail"); // 비밀번호 불일치 시 메시지 전달
		}else {
			// 비밀번호 일치 탈퇴 진행
			mservice.updateMemberType(m_id);
			session.setAttribute("u_id", null);
			session.setAttribute("u_name", null);
			session.setAttribute("u_type", null);
		}
		return map;
	}
	
}
