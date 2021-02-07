package com.project.picker.Controller;

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

import com.project.picker.DTO.MemberDTO;
import com.project.picker.Service.JoinService;

@Controller
public class JoinController {

	private static final Logger logger = LoggerFactory.getLogger(JoinController.class);
	
	@Inject
	JoinService jservice;
	
	// 가입 동의 체크 화면
	@RequestMapping(value="joinAgree", method= {RequestMethod.GET, RequestMethod.POST})
	public String joinAgree(Model model) {
		model.addAttribute("section", "join/JoinAgree.jsp");
		return "Index";
	}
	
	// 회원가입 작성 화면
	@RequestMapping(value="joinWrite", method= {RequestMethod.GET, RequestMethod.POST})
	public String joinWrite(MemberDTO mdto, Model model) {
		logger.info("동의여부1 : " + mdto.getM_terms());
		logger.info("동의여부2 : " + mdto.getM_personal());
		model.addAttribute("mdto", mdto);
		model.addAttribute("section", "join/JoinWrite.jsp");
		return "Index";
	}
	
	// 회원가입 아이디 사용 가능 여부 체크
	@RequestMapping(value="idChecking", method= {RequestMethod.GET, RequestMethod.POST}, produces="application/json; charset=utf-8")
	@ResponseBody
	public int idCheck(@RequestParam String m_id) {
		int result = jservice.idCheck(m_id);
		logger.info(">>> 아이디 사용 가능 여부 : "+result);
		return result;
	}
	
	// 회원가입 완료
	@RequestMapping(value="joinSave", method= {RequestMethod.GET, RequestMethod.POST}, produces="application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> joinSave(MemberDTO mdto, Model model) {
		Map<String, Object> map = new HashMap<>();
		logger.info(">>> 회원가입 완료");
		jservice.insertMember(mdto);
		jservice.insertJoinPoint(mdto.getM_id(), "신규회원 쇼핑지원금", 1000);
		map.put("msg", "success");
		return map;
	}
}
