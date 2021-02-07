package com.project.picker.Controller;

import java.util.ArrayList;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.picker.DTO.ItemDTO;
import com.project.picker.Service.ItemService;

@Controller 
public class ItemController {

	private static final Logger logger = LoggerFactory.getLogger(ItemController.class);
	
	@Inject
	ItemService Iservice;
		
	// 카테고리 별 상품리스트를 보는 맵핑
	@RequestMapping(value="goList", method={RequestMethod.GET, RequestMethod.POST})
	public String goList(@ RequestParam String i_category,Model model){
		 
		ArrayList<ItemDTO> itemlist = Iservice.ItemList(i_category);
		model.addAttribute("itemlist", itemlist);
		
		ItemDTO cateName = Iservice.cateName(i_category);
		model.addAttribute("cateName", cateName);
		
		model.addAttribute("section", "item/ItemList.jsp");
		return "Index";
	}

	// 하나의 상품을 보는 맵핑
	@RequestMapping(value="goDetail", method= {RequestMethod.GET, RequestMethod.POST})
	public String goDetail(@RequestParam String i_code, Model model){
		
		ItemDTO idto = Iservice.itemView(i_code);
		
		model.addAttribute("idto", idto);
	
		model.addAttribute("section", "item/ItemDetail.jsp");
		return "Index";
	} 
	
}
