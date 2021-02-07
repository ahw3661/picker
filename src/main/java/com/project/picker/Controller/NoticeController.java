package com.project.picker.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.picker.DTO.NoticeDTO;
import com.project.picker.DTO.PagingDTO;
import com.project.picker.Service.NoticeServiceImpl;

@Controller
public class NoticeController {
	
	private static final Logger logger = LoggerFactory.getLogger(NoticeController.class);
	
	@Inject
	NoticeServiceImpl service;
	
	@RequestMapping("noticeList")
	public String noticeList(@RequestParam(required=false, defaultValue="") String keyword, 
		@RequestParam(required=false, defaultValue="1") int num, Model model) {
		model.addAttribute("section", "board/NoticeList.jsp");
		model.addAttribute("keyword", keyword);
		logger.info("리스트 출력");
		keyword = "%" + keyword + "%";
		int cnt = service.getNoticeCount(keyword);
		model.addAttribute("noticecnt", cnt);
		if(cnt > 0) {
			PagingDTO paging = new PagingDTO(num, cnt, 20, 5);
			model.addAttribute("paging", paging);
			ArrayList<NoticeDTO> list = service.getNoticeList(keyword, paging.getStartRow(), paging.getEndRow());
			model.addAttribute("noticelist", list);
		}
		return "Index";
	}
	
	@RequestMapping("noticeWrite")
	public String noticeWrite(Model model) {
		model.addAttribute("section", "board/NoticeWrite.jsp");
		return "Index";
	}
	
	@ResponseBody
	@RequestMapping("noticeWriteProc")
	public Map<String, Object> noticeWriteProc(NoticeDTO dto, HttpSession session) {
		logger.info("공지 작성");
		dto.setM_id((String)session.getAttribute("u_id"));
		Map<String, Object> json = new HashMap<>();
		json.put("chk", service.writeInsert(dto));
		return json;
	}
	
	@RequestMapping("noticeRead")
	public String noticeRead(@ModelAttribute("no") int no, Model model) {
		model.addAttribute("notice", service.getNoticeByNum(no));
		int next = service.getNext(no);
		if(next != 0) {
			model.addAttribute("nextNum", next);
			model.addAttribute("nextTitle", service.getTitle(next));
		}
		int prev = service.getPrev(no);
		if(prev != 0) {
			model.addAttribute("prevNum", prev);
			model.addAttribute("prevTitle", service.getTitle(prev));
		}
		model.addAttribute("section", "board/NoticeInfo.jsp");
		return "Index";
	}
	
	@RequestMapping("noticeModify")
	public String noticeModify(@RequestParam int no, Model model) {
		model.addAttribute("notice", service.getNoticeByNum(no));
		model.addAttribute("section", "board/NoticeModify.jsp");
		return "Index";
	}
	
	@ResponseBody
	@RequestMapping("noticeModifyProc")
	public Map<String, Object> noticeModifyProc(NoticeDTO dto) {
		Map<String, Object> json = new HashMap<>();
		json.put("chk", service.modifyNotice(dto));
		return json;
	}
	
	@ResponseBody
	@RequestMapping("noticeErase")
	public Map<String, Object> noticeErase(@RequestParam int no) {
		Map<String, Object> json = new HashMap<>();
		json.put("chk", service.eraseNotice(no));
		return json;
	}
	
	@RequestMapping("noticeAdminError")
	public String noticeAdminError(Model model) {
		model.addAttribute("msg", "비정상적인 접근입니다.");
		model.addAttribute("log", 2);
		return "Error";
	}
	
	@ResponseBody
	@RequestMapping("ajaxError")
	public Map<String, Object> noticeAdminAjaxError(){
		logger.info("ajax interceptor : false");
		Map<String, Object> json = new HashMap<>();
		json.put("chk", false);
		json.put("logError", true);
		return json;
	}

}
