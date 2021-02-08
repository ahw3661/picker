package com.project.picker.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.picker.DTO.EvalDTO;
import com.project.picker.DTO.PagingDTO;
import com.project.picker.Service.EvalService;

@Controller
public class EvalController {

	private static final Logger logger = LoggerFactory.getLogger(ItemController.class);
	
	@Inject
	EvalService eservice;
	
	    // ¸®ºä¸®½ºÆ®·Î °¡´Â ¸ÊÇÎ
		@RequestMapping(value="itemEvalList",method= {RequestMethod.GET, RequestMethod.POST})
		public String goitem_eval(@RequestParam String i_code, @RequestParam int pageNum, Model model) {
			
			System.out.println("i_code : " + i_code);
			model.addAttribute("i_code", i_code);
			
			int totCnt = eservice.codeCount(i_code);
			System.out.println("totCnt : " + totCnt);
			model.addAttribute("totCnt", totCnt);
			
			if(totCnt > 0){
				PagingDTO pgdto = new PagingDTO(pageNum, totCnt, 5, 5);
				model.addAttribute("pgdto", pgdto);
				ArrayList<EvalDTO> edtoarr = eservice.evalList(i_code, pgdto.getStartRow(), pgdto.getEndRow());
				model.addAttribute("edtoarr", edtoarr);
			}
			
			return "eval/EvalList";
		}
	
	    // ¸®ºäÀÛ¼ºÇÏ´Â ÆûÀ¸·Î °¡´Â ¸ÊÇÎ
		@RequestMapping(value="goEvalWrite",method= {RequestMethod.GET, RequestMethod.POST})
		public String goevalwrite(@RequestParam String i_code, Model model) {
			model.addAttribute("i_code", i_code);
			return "eval/Evalwrite";
		}
		
		// ¸®ºä ÀÛ¼ºÇÏ´Â ¸ÊÇÎ
		@ResponseBody
		@RequestMapping(value="EvalInsert",method= {RequestMethod.GET, RequestMethod.POST})
		public Map<String, Object> EvalInsert(EvalDTO edto,  Model model) {
			Map<String, Object> map = new HashMap<>();
			map.put("chk", eservice.EvalInsert(edto));
			System.out.println(map.toString());
			return map;
		}
}
