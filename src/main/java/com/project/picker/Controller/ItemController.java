package com.project.picker.Controller;

import java.util.ArrayList;
import java.util.List;

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
		
	/*// �̸��� ���� ���� ������ �����ϴ� �Լ�
		@RequestMapping(value="itemUp", method= {RequestMethod.GET, RequestMethod.POST})
		public String itemUp(@RequestParam(required=false, defaultValue="item_up") String item_sort, Model model){
			System.out.println(item_sort);
			ArrayList<ItemDTO> idto = Iservice.itemUp(item_sort);
			model.addAttribute("idto", idto);
			
			
			model.addAttribute("section", "item/ItemList.jsp");
			return "Index";
		}*/
	
	@RequestMapping(value="searchItem", method={RequestMethod.GET, RequestMethod.POST})
	public String itemSearch( @RequestParam String item_search, Model model, ItemDTO itdto) {
		
			logger.info("��ǰ���");
		
			System.out.println("item_search :" +item_search);
			item_search = "%" + item_search + "%";
			ArrayList<ItemDTO> idto = Iservice.itemSearch(item_search);
			model.addAttribute("idto", idto);
			
			int cnt = Iservice.itemSearchCnt(item_search);
			System.out.println("cnt :" + cnt);
			
				model.addAttribute("cnt", cnt);
				model.addAttribute("section", "item/ItemSearch.jsp");
	
				return "Index";
	}
	
	// ī�װ� �� ��ǰ����Ʈ�� ���� ����
	@RequestMapping(value="goList", method={RequestMethod.GET, RequestMethod.POST})
	public String goList( @RequestParam(required=false, defaultValue="item_up") String item_sort, @ RequestParam String i_category, Model model){
		 
		logger.info("��ǰ����Ʈ ���");
/*		ArrayList<ItemDTO> itemlist = Iservice.ItemList(i_category);
		model.addAttribute("itemlist", itemlist);*/
		System.out.println(item_sort);
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
		
		logger.info("��ǰ�� ���");							 
		ItemDTO idto = Iservice.itemView(i_code);
		
		model.addAttribute("idto", idto);
		
		model.addAttribute("section", "item/ItemDetail.jsp");
		return "Index";
	} 
	
	
}
