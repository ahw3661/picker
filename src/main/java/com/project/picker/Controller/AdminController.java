package com.project.picker.Controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.picker.DTO.BuyDTO;
import com.project.picker.DTO.BuyitemDTO;
import com.project.picker.DTO.ItemDTO;
import com.project.picker.DTO.ItemInsertDTO;
import com.project.picker.DTO.MemberDTO;
import com.project.picker.DTO.PagingDTO;
import com.project.picker.DTO.PointDTO;
import com.project.picker.Service.AdminService;
import com.project.picker.Service.MemberService;

@Controller 
public class AdminController {

	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Inject
	AdminService aservice;
	
	@Inject
	MemberService mservice;
	
	// 관리자 화면
	@RequestMapping(value="adminPage", method= {RequestMethod.GET, RequestMethod.POST})
	public String adminPage(Model model) {
		int memberCount = aservice.memberCount();
		int itemCount = aservice.itemCount();
		logger.info(">>> 회원수 : "+memberCount);
		logger.info(">>> 상품수 : "+itemCount);
		model.addAttribute("memberCount", memberCount);
		model.addAttribute("itemCount", itemCount);
		model.addAttribute("section", "admin/AdminPage.jsp");
		return "Index";
	}
	
	// 전체 회원 및 비회원 구매 목록
	@RequestMapping(value="allBuyList", method={RequestMethod.GET, RequestMethod.POST})
	public String allBuyList(@RequestParam(required=false) String start_date, @RequestParam(required=false) String end_date, 
			@RequestParam(defaultValue = "1") int pageNum, Model model) {
		
		if(start_date == null && end_date == null) {
			logger.info("주문내역관리 메뉴 클릭");
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate now = LocalDate.now();
			LocalDate preMonth = now.minusMonths(3);
			
			start_date = dtf.format(preMonth);
			end_date = dtf.format(now);
		}
		ArrayList<BuyitemDTO> buyItem = aservice.allBuyItem();
		int cnt = aservice.getAllBuyCount(start_date, end_date);
		
		if(cnt > 0) {
			PagingDTO pgdto = new PagingDTO(pageNum, cnt, 10, 5);
			model.addAttribute("pgdto", pgdto);
			List<BuyDTO> buyList = aservice.allBuyList(start_date, end_date, pgdto.getStartRow(), pgdto.getEndRow());
			model.addAttribute("buyList", buyList);
		}
		model.addAttribute("start_date", start_date);
		model.addAttribute("end_date", end_date);
		model.addAttribute("buyItem", buyItem);
		model.addAttribute("cnt", cnt);
		return "admin/AdminBuyList";
	}
	
	// 전체 회원 및 비회원 주문 상세
	@RequestMapping(value="buyDetail", method= {RequestMethod.GET, RequestMethod.POST})
	public String buyDetail(@RequestParam int b_code, @RequestParam int pageNum, @RequestParam String start_date, 
			@RequestParam String end_date, Model model, HttpSession session) {
		BuyDTO bdto = aservice.getOneBuyInfo(b_code);
		ArrayList<BuyitemDTO> bidto = aservice.getOneBuyItemInfo(b_code);
		int total = aservice.getSumBuyPrice(b_code);
		Integer point = mservice.usePoint(b_code);
		model.addAttribute("bdto", bdto);
		model.addAttribute("bidto", bidto);
		model.addAttribute("total", total);
		model.addAttribute("point", point);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("start_date", start_date);
		model.addAttribute("end_date", end_date);
		return "admin/AdminBuyDetail";
	}
	
	// 회원별 구매취소 완료된 목록
	@RequestMapping(value="allBuyCancel", method= {RequestMethod.GET, RequestMethod.POST})
	public String buyCancel(@RequestParam(required=false) String start_date, @RequestParam(required=false) String end_date, 
			@RequestParam(defaultValue = "1") int pageNum, Model model) {
		
		if(start_date == null && end_date == null) {
			logger.info("주문취소관리 메뉴 클릭");
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate now = LocalDate.now();
			LocalDate preMonth = now.minusMonths(3);
			
			start_date = dtf.format(preMonth);
			end_date = dtf.format(now);
		}
		ArrayList<BuyitemDTO> buyitem = aservice.buyItemList();
		int	cnt = aservice.getAllBuyCancelCount(start_date, end_date);
		
		if(cnt > 0) {
			PagingDTO pgdto = new PagingDTO(pageNum, cnt, 10, 5);
			model.addAttribute("pgdto", pgdto);
			List<BuyDTO> allBuyCancel = aservice.allBuyCancel(start_date, end_date, pgdto.getStartRow(), pgdto.getEndRow());
			model.addAttribute("allBuyCancel", allBuyCancel);
			logger.info(">>> 구매 취소 완료 목록 존재");
		}
		logger.info(">>> 구매 취소 완료 목록");
		model.addAttribute("start_date", start_date);
		model.addAttribute("end_date", end_date);
		model.addAttribute("buyitem", buyitem);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("cnt", cnt);
		return "admin/AdminBuyCancelList";
	}
	
	// 주문취소 완료 주문 상세
	@RequestMapping(value="oneBuyCancel", method= {RequestMethod.GET, RequestMethod.POST})
	public String buyCancelDetail(@RequestParam int b_code, @RequestParam int pageNum, @RequestParam String start_date, 
			@RequestParam String end_date, Model model, HttpSession session) {
		logger.info("주문취소 완료 건");
		BuyDTO bdto = aservice.oneBuyCancel(b_code);
		ArrayList<BuyitemDTO> bidto = aservice.getOneBuyItemInfo(b_code);
		int total = aservice.getSumBuyPrice(b_code);
		Integer point = mservice.preUsePoint(b_code);
		Date cancelDate = mservice.getCancelDate(b_code);
		model.addAttribute("bdto", bdto);
		model.addAttribute("bidto", bidto);
		model.addAttribute("total", total);
		model.addAttribute("point", point);
		model.addAttribute("cancelId", bdto.getM_id());
		model.addAttribute("cancelDate", cancelDate);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("start_date", start_date);
		model.addAttribute("end_date", end_date);
		return "admin/AdminBuyCancelDetail";
	}
	
	// 전체 회원 포인트 목록
	@RequestMapping(value="allPointList", method={RequestMethod.GET, RequestMethod.POST})
	public String allPointList(@RequestParam(defaultValue = "1") int pageNum, Model model) {
		int cnt = aservice.getAllPointCount();
		
		if(cnt > 0) {
			PagingDTO pgdto = new PagingDTO(pageNum, cnt, 10, 5);
			model.addAttribute("pgdto", pgdto);
			List<PointDTO> pdto = aservice.allPointList(pgdto.getStartRow(), pgdto.getEndRow());
			model.addAttribute("pdto", pdto);
		}
		return "admin/AdminPointList";
	}
	
	// 회원별 포인트 목록
	@RequestMapping(value="pointDetail", method= {RequestMethod.GET, RequestMethod.POST})
	public String pointDetail(@RequestParam String m_id, @RequestParam(defaultValue = "1") int pageNum, @RequestParam int listPageNum, Model model) {
		int cnt = aservice.getOnePointCount(m_id);
		
		if(cnt > 0) {
			PagingDTO pgdto = new PagingDTO(pageNum, cnt, 10, 5);
			model.addAttribute("pgdto", pgdto);
			List<PointDTO> list = aservice.onePointDetail(m_id, pgdto.getStartRow(), pgdto.getEndRow());
			model.addAttribute("m_id", m_id);
			model.addAttribute("list", list);
		}
		model.addAttribute("listPageNum", listPageNum);
		model.addAttribute("cnt", cnt);
		return "admin/AdminPointDetail";
	}
	
	// 사용자 정보 리스트 보이는 맵핑
	@RequestMapping(value="goMemberList", method={RequestMethod.GET, RequestMethod.POST})
	public String goList(@RequestParam(required=false, defaultValue="") String s_type, @RequestParam(required=false, defaultValue="") String m_keyword, 
			@RequestParam(defaultValue="-1") int m_type, @RequestParam(defaultValue = "1") int pageNum, Model model) {
		model.addAttribute("s_type", s_type);
		model.addAttribute("m_keyword", m_keyword);
		model.addAttribute("m_type", m_type);
		m_keyword = "%" + m_keyword + "%";
		
		int cnt = aservice.getAllMemberCount(s_type, m_keyword, m_type);
		
		if(cnt > 0) {
			PagingDTO pgdto = new PagingDTO(pageNum, cnt, 10, 5);
			model.addAttribute("pgdto", pgdto);
			List<MemberDTO> mdto = aservice.memberList(s_type, m_keyword, m_type, pgdto.getStartRow(), pgdto.getEndRow());
			model.addAttribute("mdto", mdto);
		}
		model.addAttribute("cnt", cnt);
		return "admin/AdminMemberList";
	}
	
	//한 명의 회원정보 리스트 보이는 맵핑
	@RequestMapping(value="goOneList", method= {RequestMethod.GET, RequestMethod.POST})
	public String goOneList(@RequestParam String m_id, @RequestParam int pageNum, Model model, HttpSession session) {
		MemberDTO mdto = aservice.oneList(m_id);
		logger.info(">>> 회원유형 : "+mdto.getM_type());
		int type = mdto.getM_type();
		String m_type = "";
		
		if(type == 1){
			m_type = "회원";
			model.addAttribute("mdto", mdto);
			model.addAttribute("m_type", m_type);
			model.addAttribute("pageNum", pageNum);
		}else {
			m_type = "탈퇴회원";
			model.addAttribute("mdto", mdto);
			model.addAttribute("m_type", m_type);
			model.addAttribute("pageNum", pageNum);
		}
		return "admin/AdminOneUserInfo";
	}
	
	// 상품 전체 리스트 보는 맵핑
	@RequestMapping(value="goItemList", method={RequestMethod.GET, RequestMethod.POST})
	public String goItemList(@RequestParam(required=false, defaultValue="") String s_type, @RequestParam(required=false, defaultValue="") String m_keyword, 
			@RequestParam(defaultValue="all") String i_category, @RequestParam(defaultValue="-1") int i_chk, @RequestParam(defaultValue = "1") int pageNum, Model model) {
		model.addAttribute("s_type", s_type);
		model.addAttribute("m_keyword", m_keyword);
		model.addAttribute("i_category", i_category);
		model.addAttribute("i_chk", i_chk);
		m_keyword = "%" + m_keyword + "%";
		
		int cnt = aservice.itemListCount(s_type, m_keyword, i_category, i_chk);
		
		if(cnt>0){
			PagingDTO pgdto = new PagingDTO(pageNum,cnt,10,5);
			model.addAttribute("pgdto", pgdto);
			List<ItemDTO> itemList = aservice.itemList(s_type, m_keyword, i_category, i_chk, pgdto.getStartRow(), pgdto.getEndRow());
			model.addAttribute("itemList", itemList);
		}
		model.addAttribute("cnt", cnt);
		return "admin/AdminItemList";
	}
	
	// 한개의 상품을  보이는 맵핑
	@RequestMapping(value="goAdminItemDetail", method={RequestMethod.GET, RequestMethod.POST})
	public String goAdminItemDetail(@RequestParam String i_code, @RequestParam int pageNum, Model model) {
		ItemDTO idto = aservice.oneItemList(i_code);
		model.addAttribute("idto", idto);
		model.addAttribute("pageNum", pageNum);
		return "admin/AdminItemDetail";
	}
	
	// 상품정보 수정 화면
	@RequestMapping(value="itemUpdatePage", method= {RequestMethod.GET, RequestMethod.POST})
	public String itemUpdatePage(@RequestParam String i_code, @RequestParam int pageNum, Model model) {
		ItemDTO idto = aservice.oneItemList(i_code);
		model.addAttribute("idto", idto);
		model.addAttribute("pageNum", pageNum);
		return "admin/AdminItemUpdate";
	}
	
	// 상품정보 수정
	@RequestMapping(value="itemUpdate", method= {RequestMethod.GET, RequestMethod.POST})
	public String itemUpdate(ItemInsertDTO idto, HttpSession session, Model model, @RequestParam int pageNum) {
		aservice.itemUpdate(idto, session);
		ItemDTO idto2 = aservice.oneItemList(idto.getI_code());
		model.addAttribute("idto", idto2);
		model.addAttribute("pageNum", pageNum);
		return "admin/AdminItemDetail";
	}

	// 상품 등록화면으로 이동하는 맵핑
	@RequestMapping(value="goItemInsert", method={RequestMethod.GET, RequestMethod.POST})
	public String goItemInsert(Model model) {
		return "admin/AdminItemInsert";
	}
	
	// 상품 등록하는 맵핑
	@RequestMapping(value="ItemInsert", method={RequestMethod.GET, RequestMethod.POST})
	public String ItemInsert(ItemInsertDTO idto, HttpSession session, Model model) {
		aservice.ItemInsert(idto, session);		
		return "admin/AdminItemInsert";
	}
	
	// 문의 목록
	@RequestMapping("allQnaList")
	public String allQnaList(@RequestParam(required=false, defaultValue="1") int pageNum, @RequestParam(required=false) String column,
		@RequestParam(required=false) String keyword, @RequestParam(required=false, defaultValue="") String code, 
		@RequestParam(required=false, defaultValue="-1") int rchk, Model model){
		model.addAttribute("itemList", aservice.getItemNameList());
		model.addAttribute("code", code);
		model.addAttribute("rchk", rchk);
		if(keyword != null && column != null) {
			model.addAttribute("keyword", keyword);
			model.addAttribute("column", column);
			if(column.equals("title")) keyword = "%" + keyword + "%";
		}
		int qnacnt = aservice.qnaCount(column, keyword, code, rchk);
		model.addAttribute("qnacnt", qnacnt);
		if(qnacnt > 0) {
			PagingDTO paging = new PagingDTO(pageNum, qnacnt, 10, 5);
			model.addAttribute("paging", paging);
			model.addAttribute("qnalist", aservice.qnaList(column, keyword, code, rchk, paging.getStartRow(), paging.getEndRow()));
		}
		return "admin/AdminQnaList";
	}
}
