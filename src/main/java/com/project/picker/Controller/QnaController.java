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

import com.project.picker.DTO.PagingDTO;
import com.project.picker.DTO.QnaDTO;
import com.project.picker.DTO.ReplyDTO;
import com.project.picker.Service.QnaService;

@Controller
public class QnaController {
	
	private static final Logger logger = LoggerFactory.getLogger(QnaController.class);
	
	@Inject
	QnaService service;
	
	@RequestMapping("qnaMemberList")
	public String qnaMemberList(Model model, @RequestParam(required=false, defaultValue="5") int row, HttpSession session) {
		String id = (String)session.getAttribute("u_id");
		model.addAttribute("qnacnt", service.getCountByMember(id));
		model.addAttribute("row", row);
		ArrayList<QnaDTO> list = service.getQnaListByMember(id, row);
		model.addAttribute("qnalist", list);
		model.addAttribute("replylist", service.getReplyByMember(list));
		return "myPage/QnaInfo";
	}
	
	@RequestMapping("itemQnaList")
	public String ItemQnaList(@ModelAttribute("code") String code, @RequestParam(required=false, defaultValue="1") int num, Model model) {
		int cnt = service.getCountByItem(code);
		model.addAttribute("qnacnt", cnt);
		if(cnt > 0) {
			PagingDTO paging = new PagingDTO(num, cnt, 5, 5);
			model.addAttribute("paging", paging);
			model.addAttribute("qnalist", service.getQnaListByItem(code, paging.getStartRow(), paging.getEndRow()));
		}
		return "item/ItemQna";
	}
	
	@RequestMapping("qnaPop")
	public String qnaPop(@RequestParam int q_num, Model model) {
		model.addAttribute("qna", service.getQnaWithReplyCount(q_num));
		model.addAttribute("replylist", service.getReplyByQna(q_num));
		return "item/ItemQnaPop";
	}
	
	@RequestMapping("qnaWrite")
	public String qnaWrite(Model model, @RequestParam(required=false) String code) {
		if(code != null) model.addAttribute("item", service.getItemInfo(code));
		model.addAttribute("section", "board/BoardWrite.jsp");
		return "Index";
	}
	
	@ResponseBody
	@RequestMapping("qnaWriteProc")
	public Map<String, Object> qnaWriteProc(QnaDTO dto, HttpSession session){
		logger.info("글쓰기 작동");
		logger.info("title : " + dto.getQ_title());
		Map<String, Object> json = new HashMap<>();
		dto.setM_id((String)session.getAttribute("u_id"));
		dto.setM_name((String)session.getAttribute("u_name"));
		json.put("chk", service.writeQna(dto));
		return json;
	}
	
	@RequestMapping("qnaModify")
	public String qnaModify(@RequestParam int q_num, Model model) {
		model.addAttribute("dto", service.getQnaByNum(q_num));
		model.addAttribute("section", "board/BoardModify.jsp");
		return "Index";
	}
	
	@ResponseBody
	@RequestMapping("qnaModifyProc")
	public Map<String, Object> qnaModifyProc(QnaDTO dto) {
		logger.info("글쓰기 수정");
		Map<String, Object> json = new HashMap<>();
		json.put("chk", service.modifyQna(dto));
		return json;
	}
	
	@ResponseBody
	@RequestMapping("qnaDelete")
	public Map<String, Object> qnaDelete(@RequestParam int q_num) {
		Map<String, Object> json = new HashMap<>();
		json.put("chk", service.deleteQna(q_num));
		return json;
	}
	
	@RequestMapping("qnaError")
	public String qnaError(@RequestParam(required=false, defaultValue="0") int loc, Model model) {
		model.addAttribute("msg", "비밀글입니다.");
		if(loc != 0) model.addAttribute("loc", 2);
		return "Error";
	}
	
	@ResponseBody
	@RequestMapping("replyWrite")
	public Map<String, Object> replyWriteByMember(ReplyDTO dto, @RequestParam(required=false, defaultValue="0") int ref, HttpSession session) {
		dto.setM_id((String)session.getAttribute("u_id"));
		dto.setM_name((int)session.getAttribute("u_type") == 0 ? "더피커" : (String)session.getAttribute("u_name"));
		dto.setR_content(dto.getR_content().replace("\r\n", "<br>"));
		Map<String, Object> json = new HashMap<>();
		json.put("chk", service.writeReply(dto, ref));
		if(session.getAttribute("u_type") != null && (int)session.getAttribute("u_type") == 0) service.setAdminReplied(dto.getQ_num());
		return json;
	}
	
	@ResponseBody
	@RequestMapping("replyGetContent")
	public String replyGetContent(@RequestParam int r_num) {
		return service.getReplyContent(r_num).replace("<br>", "\r\n");
	}
	
	@ResponseBody
	@RequestMapping("replyModify")
	public Map<String, Object> replyModify(ReplyDTO dto) {		
		dto.setR_content(dto.getR_content().replace("\r\n", "<br>"));
		Map<String, Object> json = new HashMap<>();
		json.put("chk", service.modifyReply(dto));
		return json;
	}

	@ResponseBody
	@RequestMapping("replyDelete")
	public Map<String, Object> replyDelete(@RequestParam int r_num) {
		Map<String, Object> json = new HashMap<>();
		json.put("chk", service.deleteReply(r_num));
		return json;
	}
	
	@RequestMapping("replyError")
	public String replyError(Model model) {
		model.addAttribute("msg", "비정상적인 접근입니다.");
		return "Error";
	}
	
}
