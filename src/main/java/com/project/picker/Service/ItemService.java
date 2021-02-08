package com.project.picker.Service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.project.picker.DTO.ItemDTO;

public interface ItemService {
	
	public List<ItemDTO> itemListBySort(String i_category, String item_sort);
	public ArrayList<ItemDTO> ItemList(String i_category);
	public ItemDTO itemView(String i_code);
	public ItemDTO cateName(String i_category);
	public ArrayList<ItemDTO> itemSearch(String item_search);
	public int itemSearchCnt(@Param("item_search") String item_search);

}
