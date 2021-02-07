package com.project.picker.DAO;

import java.util.List;

import com.project.picker.DTO.ItemDTO;

public interface ItemDAO {
	
	public List<ItemDTO> itemList(String i_category, String item_sort);

}
