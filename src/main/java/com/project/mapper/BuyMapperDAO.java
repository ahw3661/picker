package com.project.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.project.picker.DTO.CartDTO;

public interface BuyMapperDAO {
	@Select("SELECT * FROM picker_cart WHERE (m_id = #{m_id} AND i_code = #{i_code})")
	public CartDTO payList_cart(@Param("m_id") String m_id, @Param("i_code") String i_code); // 회원별 코드가 같은 상품 출력 => 구매 jsp에서 사용
	

}
