package com.project.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.project.picker.DTO.BuyDTO;
import com.project.picker.DTO.BuyitemDTO;
import com.project.picker.DTO.CartDTO;
import com.project.picker.DTO.PointDTO;

public interface BuyMapperDAO {
	@Select("SELECT * FROM picker_cart WHERE (m_id = #{m_id} AND i_code = #{i_code})")
	public CartDTO payList_cart(@Param("m_id") String m_id, @Param("i_code") String i_code); // 회원별 코드가 같은 상품 출력 => 구매 jsp에서 사용
	
	@Select("SELECT NVL(MAX(b_code)+1,#{minb_code}+1) FROM picker_buy where b_code between #{minb_code} and #{maxb_code}")
	public int maxCode(@Param("minb_code") int minb_code, @Param("maxb_code") int maxb_code);

	@Insert("INSERT INTO picker_buy "
			+ "(b_code,	b_order_name, b_order_phone, b_order_email, b_take_name, b_take_phone, b_take_zipcode, b_take_roadaddr, b_take_detailaddr, m_id, b_price, b_date, b_agree)"
			+ "VALUES "
			+ "(#{b_code}, #{b_order_name}, #{b_order_phone}, #{b_order_email}, #{b_take_name}, #{b_take_phone}, #{b_take_zipcode}, #{b_take_roadaddr}, #{b_take_detailaddr}, #{m_id}, #{b_price}, SYSDATE, 'Y')")
	public void insertBuy(BuyDTO bdto);
	
	@Insert("INSERT INTO picker_buyitem "
			+ "(bi_num, b_code, i_img, i_code, i_name, bi_cnt, i_price)"
			+ "VALUES "
			+ "((SELECT NVL(MAX(bi_num)+1, 1) FROM picker_buyitem), #{b_code}, #{i_img}, #{i_code}, #{i_name}, #{bi_cnt}, #{i_price})")
	public void insertBuyitem(BuyitemDTO bidto);
	public void insertPoint(PointDTO pdto);		
	
}
