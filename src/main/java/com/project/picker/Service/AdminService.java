package com.project.picker.Service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.project.picker.DTO.BuyDTO;
import com.project.picker.DTO.BuyitemDTO;
import com.project.picker.DTO.ItemDTO;
import com.project.picker.DTO.ItemInsertDTO;
import com.project.picker.DTO.ItemQnaDTO;
import com.project.picker.DTO.MemberDTO;
import com.project.picker.DTO.PointDTO;

public interface AdminService {

	public List<MemberDTO> memberList(String s_type, String m_keyword, int m_type, int startRow, int endRow);
	public MemberDTO oneList(String m_id);
	public List<ItemDTO> itemList(String s_type, String m_keyword, String i_category, int i_chk, int startRow, int endRow);
	public int itemListCount(String s_type, String m_keyword, String i_category, int i_chk);
	public ItemDTO oneItemList(String i_code);
	public int memberCount(); // 총 회원 수
	public int itemCount(); // 총 상품 수
	public String getCode();
	public void ItemInsert(ItemInsertDTO idto, HttpSession session);
	public List<PointDTO> allPointList(int startRow, int endRow); // 전체 포인트 목록
	public List<PointDTO> onePointDetail(String m_id, int startRow, int endRow); // 회원 포인트 상세
	public int getAllPointCount(); // 전체 포인트 건수
	public int getOnePointCount(String m_id); // 회원 포인트 건수
	public int getAllMemberCount();
	public int getAllBuyCount(String start_date, String end_date); // 전체 구매 목록 건수
	public List<BuyDTO> allBuyList(String start_date, String end_date, int startRow, int endRow); // 전체 구매 목록
	public ArrayList<BuyitemDTO> allBuyItem(); // 전체 구매 목록 상품
	public BuyDTO getOneBuyInfo(int b_code); // 구매 목록 상세
	public ArrayList<BuyitemDTO> getOneBuyItemInfo(int b_code); // 구매 목록 상세 상품
	public int getSumBuyPrice(int b_code); // 구매 총 금액
	public ArrayList<BuyitemDTO> buyItemList(); // 전체 구매 취소 완료 목록 상품 
	public int getAllBuyCancelCount(String start_date, String end_date); // 전체 구매 취소 완료 건수
	public List<BuyDTO> allBuyCancel(String start_date, String end_date, int startRow, int endRow); // 전체 구매 취소 완료 목록
	public BuyDTO oneBuyCancel(int b_code); // 전체 구매 취소 완료 상세
	public void itemUpdate(ItemInsertDTO idto, HttpSession session); // 상품 정보 수정
	public int getAllMemberCount(String s_type, String m_keyword, int m_type); // 전체 회원 검색 카운트
	public int qnaCount(String column, String keyword, String code, int rchk);
	public List<ItemQnaDTO> qnaList(String column, String keyword, String code, int rchk, int startRow, int endRow);
	public ArrayList<ItemDTO> getItemNameList();
	
}
