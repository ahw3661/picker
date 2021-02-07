package com.project.picker.Service;

import java.util.ArrayList;

import com.project.picker.DTO.ItemDTO;

public interface ItemService {
	
	public ArrayList<ItemDTO> listBy(String i_category, String option);
	public ArrayList<ItemDTO> ItemList(String i_category);
	public ItemDTO itemView(String i_code);
	public ItemDTO cateName(String i_category);

}
