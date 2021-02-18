package com.project.picker.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.picker.DTO.ItemDTO;
import com.project.picker.Service.ItemService;

@Controller 
public class ItemController {

	@Inject
	ItemService Iservice;
	
	@RequestMapping(value="searchItem", method={RequestMethod.GET, RequestMethod.POST})
	public String itemSearch( @RequestParam String item_search, Model model, ItemDTO itdto) {
		
		// ��ǰ���
		item_search = "%" + item_search + "%";
		ArrayList<ItemDTO> idto = Iservice.itemSearch(item_search);
		model.addAttribute("idto", idto);
		
		int cnt = Iservice.itemSearchCnt(item_search);
		
		model.addAttribute("cnt", cnt);
		model.addAttribute("section", "item/ItemSearch.jsp");

		return "Index";
	}
	
	// ī�װ� �� ��ǰ����Ʈ�� ���� ����
	@RequestMapping(value="goList", method={RequestMethod.GET, RequestMethod.POST})
	public String goList( @RequestParam(required=false, defaultValue="item_up") String item_sort, @ RequestParam String i_category, Model model){
		 
		// ��ǰ����Ʈ ���
		List<ItemDTO> itemlist = Iservice.itemListBySort(i_category, item_sort);
		model.addAttribute("itemlist", itemlist);
		
		ItemDTO cateName = Iservice.cateName(i_category);
		model.addAttribute("cateName", cateName);
		model.addAttribute("section", "item/ItemList.jsp");
		return "Index";
	}

	// �ϳ��� ��ǰ�� ���� ����
	@RequestMapping(value="goDetail", method= {RequestMethod.GET, RequestMethod.POST})
	public String goDetail(@RequestParam String i_code, Model model){
		
		// ��ǰ�� ���							 
		ItemDTO idto = Iservice.itemView(i_code);
		
		model.addAttribute("idto", idto);
		
		model.addAttribute("section", "item/ItemDetail.jsp");
		return "Index";
	} 
	
	
}
