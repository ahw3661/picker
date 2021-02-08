package com.project.picker.Service;

import java.util.ArrayList;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.project.mapper.EvalMapperDAO;
import com.project.picker.DTO.EvalDTO;

@Service
public class EvalServicempl implements EvalService {

	@Inject
	EvalMapperDAO dao; // ¡÷¿‘

	@Override
	public boolean evalInsert(EvalDTO dto) {
		return dao.evalInsert(dto) == 1;
	}
	
	@Override
	public boolean evalUpdate(EvalDTO dto) {
		return dao.evalUpdate(dto) == 1;
	}

	@Override
	public boolean evalDelete(int e_num) {
		return dao.evalDelete(e_num) == 1;
	}
	
	@Override
	public int codeCount(String i_code) {
		return dao.codeCount(i_code);
	}

	@Override
	public ArrayList<EvalDTO> evalList(String i_code, int startRow, int endRow) {
		ArrayList<EvalDTO> list = dao.evalList(i_code, startRow, endRow);
		for(EvalDTO dto : list) {
			dto.setE_date(dto.getE_date().substring(0, 16));
		}
		return list;
	}

	@Override
	public EvalDTO getEvalByNumber(int num) {
		EvalDTO dto = dao.getEvalByNumber(num); 
		dto.setE_content(dto.getE_content().replace("<br>", "\r\n"));
		return dto; 
	}

	@Override
	public String getBuyer(int b_code) {
		return dao.getBuyer(b_code);
	}
	
}
