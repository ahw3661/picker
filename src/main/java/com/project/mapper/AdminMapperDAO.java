package com.project.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.project.picker.DTO.BuyDTO;
import com.project.picker.DTO.BuyitemDTO;
import com.project.picker.DTO.ItemDTO;
import com.project.picker.DTO.MemberDTO;

@Repository 
public interface AdminMapperDAO {
	// 한 명의 회원정보를 가져오는 함수	
	@Select("SELECT * FROM picker_member WHERE m_id = #{m_id} AND m_type = 1")
	public MemberDTO oneList(@Param("m_id") String m_id);
	
	// 한 개의 상품정보를 가져오는 함수	
	@Select("SELECT * FROM picker_item WHERE i_code = #{i_code}")
	public ItemDTO oneItemList(@Param("i_code") String i_code);
	
	// 총 회원수(일반회원) - 관리자 페이지
	@Select("SELECT COUNT(*) FROM picker_member WHERE m_type = 1")
	public int memberCount();
	
	// 총 상품수 - 관리자 페이지
	@Select("SELECT COUNT(*) FROM picker_item")
	public int itemCount();
	
	// 상품등록
	@Insert("INSERT INTO picker_item VALUES(#{i_code}, #{i_name}, #{i_price}, SYSDATE, #{i_img}, #{i_detailimg}, #{i_category}, 0 )")
	public void ItemInsert(ItemDTO idto);
	
	//상품코드 최대값 구하는 쿼리
	@Select("SELECT MAX(i_code) FROM picker_item")
	public String getCode();
	
	// 전체 회원 포인트 카운트
	@Select("SELECT COUNT(SUM(a.p_point)) FROM picker_point a INNER JOIN picker_member b ON a.m_id = b.m_id WHERE b.m_type = '1' GROUP BY a.m_id")
	public int getAllPointCount();
	
	// 회원별 포인트 내역
	@Select("SELECT COUNT(*) FROM picker_point WHERE m_id = #{m_id}")
	public int getOnePointCount(@Param("m_id") String m_id);

	// 전체 회원 카운트
	@Select("SELECT COUNT(*) FROM picker_member WHERE m_type IN (1, 2)")
	public int getAllMemberCount();

	// 전체 회원 및 비회원 구매 카운트
	@Select("SELECT COUNT(*) FROM picker_buy WHERE b_chk = 0 AND b_date BETWEEN TO_DATE(#{start_date}, 'yy/MM/dd') AND TO_DATE(#{end_date}, 'yy/MM/dd')+1")
	public int getAllBuyCount(@Param("start_date") String start_date, @Param("end_date") String end_date);

	// 전체 회원 및 비회원 구매상품
	@Select("SELECT * FROM picker_buyitem ORDER BY bi_num DESC")
	public ArrayList<BuyitemDTO> allBuyItem();
	
	// 전체 회원 및 비회원 구매상세1
	@Select("SELECT b_code, b_order_name, b_take_name, b_take_phone, b_take_zipcode, b_take_roadaddr, b_take_detailaddr, m_id, b_price, b_date FROM picker_buy WHERE b_code = #{b_code}")
	public BuyDTO getOneBuyInfo(@Param("b_code") int b_code);

	// 전체 회원 및 비회원 구매상세2
	@Select("SELECT i_name, bi_cnt, i_price FROM picker_buyitem WHERE b_code = #{b_code}")
	public ArrayList<BuyitemDTO> getOneBuyItemInfo(@Param("b_code") int b_code);
	
	// 전체 회원 및 비회원 상품구매 금액 합계
	@Select("SELECT SUM(bi_cnt*i_price) FROM picker_buyitem WHERE b_code = #{b_code}")
	public int getSumBuyPrice(@Param("b_code") int b_code);

	// 전체 회원 및 비회원 구매취소상품 목록
	@Select("SELECT * FROM picker_buyitem")
	public ArrayList<BuyitemDTO> buyItemList();

	// 전체 회원 및 비회원 구매취소상품 카운트
	@Select("SELECT COUNT(*) FROM picker_buy WHERE b_chk = 1 AND b_date BETWEEN TO_DATE(#{start_date}, 'yy/MM/dd') AND TO_DATE(#{end_date}, 'yy/MM/dd')+1")
	public int getAllBuyCancelCount(@Param("start_date") String start_date, @Param("end_date") String end_date);

	// 전체 회원 및 비회원 구매취소상품 상세
	@Select("SELECT b_code, b_order_name, b_take_name, b_take_phone, b_take_zipcode, b_take_roadaddr, b_take_detailaddr, m_id, b_price, b_date FROM picker_buy WHERE b_code = #{b_code} AND b_chk = 1")
	public BuyDTO oneBuyCancel(@Param("b_code") int b_code);
	
	// 전체 상품코드 및 상품명 목록
	@Select("SELECT i_code, i_name FROM picker_item ORDER BY i_code ASC")
	public ArrayList<ItemDTO> getItemNameList();
}
