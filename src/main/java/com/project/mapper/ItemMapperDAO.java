package com.project.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.project.picker.DTO.ItemDTO;

@Repository 
public interface ItemMapperDAO {
	
	// 매개변수 @Param 작업 할 것!
	
	//상품 검색하는 함수
	@Select("SELECT * FROM picker_item WHERE i_name LIKE #{item_search}")
	public ArrayList<ItemDTO> itemSearch(@Param("item_search") String item_search);
	
	// 검색한 상품의 개수를 구하는 함수
	@Select("SELECT COUNT(*) FROM picker_item WHERE i_name LIKE #{item_search}")
	public int itemSearchCnt(@Param("item_search") String item_search);
	// 카테고리 및 정렬기준 설정해서 리스트 불러오는 함수
	@Select("SELECT * FROM picker_item WHERE i_category = #{i_category}")
	public ArrayList<ItemDTO> listBy( @Param("i_category") String i_category, @Param("option") String option);
	
	// 카테고리 값을 가진 리스트를 불러오는 함수
	@Select("SELECT * FROM picker_item WHERE i_category = #{i_category}")
	public ArrayList<ItemDTO> ItemList(@Param("i_category") String i_category);
	
	// 상품 상세정보 불러오는 함수
	@Select("SELECT * FROM picker_item WHERE i_code = #{i_code}")
	public ItemDTO itemView(@Param("i_code") String i_code);
	
	// 카테고리 불러오는 함수
		@Select("SELECT DISTINCT(i_category) FROM picker_item where i_category=#{i_category }")
		public ItemDTO cateName(@Param("i_category") String i_category);

}
