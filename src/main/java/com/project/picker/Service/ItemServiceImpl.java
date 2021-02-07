package com.project.picker.Service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.project.mapper.ItemMapperDAO;
import com.project.picker.DAO.ItemDAO;
import com.project.picker.DTO.ItemDTO;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Inject
	ItemMapperDAO idao;
	
	@Inject
	ItemDAO xmldao;

	@Override
	public List<ItemDTO> itemListBySort(String i_category, String item_sort) {
		// option : ORDER BY �ڿ� ���� ������, ������, ��ϼ�
		// list?order=pricehigh list?order=pricelow list?order=date
/*		String queryoption = "";
		if(option.equals("price_down")) {queryoption = "i_price DESC";}
		else if(option.equals("price_up")) {queryoption = "i_price ASC";}
		else if(option.equals("item_down")) {queryoption = "i_name DESC";}
		else if(option.equals("item_up")) {queryoption = "i_name ASC";}*/
		return xmldao.itemList(i_category, item_sort);
	}

	//�� ���� ��ǰ �ҷ����� �Լ�
	@Override
	public ItemDTO itemView(String i_code) {
		
		return idao.itemView(i_code);
	}

	//��ǰ ����Ʈ �ҷ����� �Լ�
	@Override
	public ArrayList<ItemDTO> ItemList(String i_category) {
		
		return idao.ItemList(i_category);
	}
	
	// �Ѱ��� ī�װ� �ҷ����� �Լ�
	@Override
	public ItemDTO cateName(String i_category) {
		
		return idao.cateName(i_category);
	}

	@Override
	public ArrayList<ItemDTO> itemSearch(String item_search) {
				
		return idao.itemSearch(item_search);
	}

	@Override
	public int itemSearchCnt(String item_search) {
		
		return idao.itemSearchCnt(item_search);
	}

	

}
