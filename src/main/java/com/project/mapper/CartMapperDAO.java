package com.project.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.project.picker.DTO.CartDTO;

@Repository
public interface CartMapperDAO {

/*	@Select("SELECT * FROM picker_cart WHERE m_id is null ORDER BY c_num")
	public ArrayList<CartDTO> nullCartList(); // 회원 아이디가 null인 장바구니 전체 출력
*/	
	@Select("SELECT * FROM picker_cart WHERE m_id = #{m_id} ORDER BY c_num")
	public ArrayList<CartDTO> allCartList(@Param("m_id") String m_id); // 회원 아이디별 장바구니 전체 출력
	
	@Select("SELECT NVL(MAX(c_num)+1, 1) FROM picker_cart")
	public int getNum();
	
	@Insert("INSERT INTO picker_cart (c_num, i_img ,i_code, i_name, c_cnt, i_price, m_id) VALUES(#{c_num}, #{i_img}, #{i_code}, #{i_name}, #{c_cnt}, #{i_price}, #{m_id})")
	public void insertCart(CartDTO cdto); // 장바구니에 상품 삽입

	@Select("SELECT count(*) FROM picker_cart WHERE i_code = #{i_code} AND m_id= #{m_id}")
	public int cartCount(CartDTO cdto); // 회원별 코드가 같은 값의 카운트 출력
	
	@Update("UPDATE picker_cart SET c_cnt = #{c_cnt} + c_cnt WHERE i_code = #{i_code} AND m_id= #{m_id}")
	public void cartCntUpdate(CartDTO cdto); // 코드가 같으면 장바구니 수량 업데이트
	
	@Update("UPDATE picker_cart SET c_cnt = #{c_cnt} + c_cnt WHERE c_num = #{c_num}")
	public void cartCntUpdate_Non(@Param("c_cnt") int c_cnt, @Param("c_num") int c_num); // 코드가 같으면 장바구니 수량 업데이트
	
	@Delete("DELETE FROM picker_cart WHERE m_id = #{m_id} AND i_code = #{i_code}")
	public void delOne(@Param("m_id") String m_id, @Param("i_code") String i_code); // 장바구니 체크박스 선택된 상품의 회원 아이디와 코드가 같으면 삭제 
	
	@Delete("DELETE FROM picker_cart WHERE c_num = #{c_num}")
	public void delOne_Non(@Param("c_num") int c_num); // 장바구니 체크박스 선택된 상품의 장바구니번호가 세션 번호와 같으면 삭제 
	
	@Delete("DELETE FROM picker_cart WHERE m_id = #{m_id}")
	public void delAll(@Param("m_id") String m_id); // 회원 장바구니 전체삭제
	
	@Delete("DELETE FROM picker_cart WHERE c_num = #{c_num}")
	public void delAll_Non(@Param("c_num") int c_num); // 비회원 장바구니 전체삭제
	
	@Select("SELECT count(*) FROM picker_cart WHERE m_id = #{m_id}")
	public int totalCartCount(@Param("m_id") String m_id); // 장바구니 회원아이디별 카운트수 출력
	
	@Update("UPDATE picker_cart SET m_id=#{m_id} WHERE c_num = #{c_num}")
	public void changeId(@Param("m_id") String m_id, @Param("c_num") int c_num); // 카트 번호가 같으면 세션에 로그인된 아이디로 업데이트
	
	@Update("UPDATE picker_cart SET c_cnt = #{c_cnt} + c_cnt WHERE i_code = #{i_code} AND m_id = #{m_id}") //아이디와 코드가 일치하면 수량 업데이트
	public void updateCnt(@Param("c_cnt") int c_cnt,@Param("i_code") String i_code,@Param("m_id") String m_id);
	
	@Update("UPDATE picker_cart SET c_cnt = #{c_cnt} WHERE i_code = #{i_code} AND m_id = #{m_id}") //아이디와 코드가 일치하면 수량 업데이트
	public void plmaupdateCnt(CartDTO cdto);
	
	@Update("UPDATE picker_cart SET c_cnt = #{c_cnt} WHERE c_num = #{c_num}") //아이디와 코드가 일치하면 수량 업데이트
	public void plmaupdateCnt_Non(CartDTO cdto);
	
	@Delete("DELETE FROM picker_cart WHERE c_num = #{c_num}")
	public void delOriginList(@Param("c_num") int c_num);

	@Select("SELECT * FROM picker_cart WHERE i_code = #{i_code}")
	public CartDTO getCartItem(@Param("i_code") String i_code);
	
	@Select("SELECT * FROM picker_cart WHERE c_num = #{c_num}")
	public CartDTO getCartNum(@Param("c_num") int c_num);
	
	
}
