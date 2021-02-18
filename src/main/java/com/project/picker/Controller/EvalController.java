package com.project.picker.Controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

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

	@Inject
	EvalService service;
	
    // 리뷰리스트로 가는 맵핑
	@RequestMapping(value="itemEvalList",method= {RequestMethod.GET, RequestMethod.POST})
	public String goitem_eval(@RequestParam String i_code, @RequestParam int pageNum, Model model) {
		model.addAttribute("i_code", i_code);
		int totCnt = service.codeCount(i_code);
		model.addAttribute("totCnt", totCnt);
		if(totCnt > 0){
			PagingDTO pgdto = new PagingDTO(pageNum, totCnt, 5, 5);
			model.addAttribute("pgdto", pgdto);
			ArrayList<EvalDTO> edtoarr = service.evalList(i_code, pgdto.getStartRow(), pgdto.getEndRow());
			model.addAttribute("edtoarr", edtoarr);
		}
		return "eval/EvalList";
	}
	
	// 리뷰 작성 팝업 띄우기
	@RequestMapping("evalPop")
	public String evalPop(@RequestParam String i_code, HttpSession session, Model model) {
		String rtn = "eval/EvalAuthen";
		if(session.getAttribute("pcsCode") != null) {
			if(session.getAttribute("pcsEvalCode") != null) {
				rtn = "eval/EvalModify";
				model.addAttribute("eval", service.getEvalByNumber((int)session.getAttribute("pcsEvalCode")));
			} else {
				rtn = "eval/EvalWrite";
			}
		}
		return rtn;
	}
	
	// 비회원 구매평 확인
	@RequestMapping("evalAuthen")
	public String evalAuthen(@RequestParam String i_code, HttpSession session, Model model){
		if(session.getAttribute("pcsCode") == null) model.addAttribute("authenFail", true);
		return evalPop(i_code, session, model); 
	}
	
	// 리뷰 작성 및 수정
	@ResponseBody
	@RequestMapping("evalWrite")
	public Map<String, Object> evalWrite(EvalDTO dto, HttpSession session, Model model) {
		Map<String, Object> map = new HashMap<>();
		boolean chk = false;
		if(session.getAttribute("pcsCode") != null) {
			dto.setE_content(dto.getE_content().replace("\r\n", "<br>"));
			if(session.getAttribute("pcsEvalCode") != null) {
				dto.setE_num((int)session.getAttribute("pcsEvalCode"));
				chk = service.evalUpdate(dto);
			} else {
				if(session.getAttribute("u_id") != null && session.getAttribute("u_name") != null) {
					dto.setM_id((String)session.getAttribute("u_id"));
					dto.setM_name((String)session.getAttribute("u_name"));
				} else {
					dto.setM_name(service.getBuyer((int)session.getAttribute("pcsCode")));
				}
				dto.setB_code((int)session.getAttribute("pcsCode"));
				chk = service.evalInsert(dto);
			}
		}
		if(chk) endSessionEval(session);
		map.put("chk", chk);
		return map;
	}
	
	// 리뷰 삭제
	@ResponseBody
	@RequestMapping("evalErase")
	public Map<String, Object> evalErase(HttpSession session, Model model){
		Map<String, Object> map = new HashMap<>();
		boolean chk = false;
		if(session.getAttribute("pcsCode") != null && session.getAttribute("pcsEvalCode") != null) {
			chk = service.evalDelete((int)session.getAttribute("pcsEvalCode"));
			if(chk) endSessionEval(session);
		}
		map.put("chk", chk);
		return map;
	}
	
	// 구매평 세션 종료
	@ResponseBody
	@RequestMapping("endSessionEval")
	public void endSessionEval(HttpSession session) {
		if(session.getAttribute("pcsCode") != null) session.setAttribute("pcsCode", null);
		if(session.getAttribute("pcsEvalCode") != null) session.setAttribute("pcsEvalCode", null);
	}
	
}
