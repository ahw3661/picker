package com.project.picker.Controller;

import java.util.ArrayList;
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

@Controller 
public class AdminController {

	private static final Logger logger = LoggerFactory.getLogger(ItemController.class);
	
	@Inject
	AdminService aservice;
	
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
	
	// 전체 회원 비회원 구매 목록
	@RequestMapping(value="allBuyList", method={RequestMethod.GET, RequestMethod.POST})
	public String allBuyList(@RequestParam int pageNum, Model model) {
		ArrayList<BuyitemDTO> buyItem = aservice.allBuyItem();
		int cnt = aservice.getAllBuyCount();
		
		if(cnt > 0) {
			PagingDTO pgdto = new PagingDTO(pageNum, cnt, 10, 5);
			model.addAttribute("pgdto", pgdto);
			List<BuyDTO> buyList = aservice.allBuyList(pgdto.getStartRow(), pgdto.getEndRow());
			model.addAttribute("buyList", buyList);
		}
		model.addAttribute("buyItem", buyItem);
		return "admin/AdminBuyList";
	}
	
	// 전체 회원 비회원 주문 상세
	@RequestMapping(value="buyDetail", method= {RequestMethod.GET, RequestMethod.POST})
	public String buyDetail(@RequestParam String b_code, @RequestParam int pageNum, Model model, HttpSession session) {
		BuyDTO bdto = aservice.getOneBuyInfo(b_code);
		ArrayList<BuyitemDTO> bidto = aservice.getOneBuyItemInfo(b_code);
		int total = aservice.getSumBuyPrice(b_code);
		model.addAttribute("bdto", bdto);
		model.addAttribute("bidto", bidto);
		model.addAttribute("total", total);
		model.addAttribute("pageNum", pageNum);
		return "admin/AdminBuyDetail";
	}
	
	// 전체 회원 포인트 목록
	@RequestMapping(value="allPointList", method={RequestMethod.GET, RequestMethod.POST})
	public String allPointList(@RequestParam int pageNum, Model model) {
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
	public String pointDetail(@RequestParam String m_id, @RequestParam int pageNum, Model model) {
		int cnt = aservice.getOnePointCount(m_id);
		
		if(cnt > 0) {
			PagingDTO pgdto = new PagingDTO(pageNum, cnt, 10, 5);
			model.addAttribute("pgdto", pgdto);
			List<PointDTO> list = aservice.onePointDetail(m_id, pgdto.getStartRow(), pgdto.getEndRow());
			model.addAttribute("m_id", m_id);
			model.addAttribute("list", list);
		}
		model.addAttribute("pageNum", pageNum);
		return "admin/AdminPointDetail";
	}
	
	// 사용자 정보 리스트 보이는 맵핑
	@RequestMapping(value="goMemberList", method={RequestMethod.GET, RequestMethod.POST})
	public String goList(@RequestParam int pageNum, Model model) {
		int cnt = aservice.getAllMemberCount();
		
		if(cnt > 0) {
			PagingDTO pgdto = new PagingDTO(pageNum, cnt, 10, 5);
			model.addAttribute("pgdto", pgdto);
			List<MemberDTO> mdto = aservice.memberList(pgdto.getStartRow(), pgdto.getEndRow());
			model.addAttribute("mdto", mdto);
		}
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
		}else if(type == 2){
			m_type = "탈퇴회원";
			model.addAttribute("mdto", mdto);
			model.addAttribute("m_type", m_type);
			model.addAttribute("pageNum", pageNum);
		}
		return "admin/AdminOneUserInfo";
	}
	
	// 상품 전체 리스트 보는 맵핑
	@RequestMapping(value="goItemList", method={RequestMethod.GET, RequestMethod.POST})
	public String goItemList(@RequestParam int pageNum, Model model) {
		int cnt = aservice.itemListCount();
		
		if(cnt>0){
			PagingDTO pgdto = new PagingDTO(pageNum,cnt,5,5);
			model.addAttribute("pgdto", pgdto);
			ArrayList<ItemDTO> itemList = aservice.itemList(pgdto.getStartRow(), pgdto.getEndRow());
			model.addAttribute("itemList", itemList);
		}
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
}
