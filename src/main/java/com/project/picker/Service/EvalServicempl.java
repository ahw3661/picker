package com.project.picker.Service;

import java.util.ArrayList;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.project.mapper.EvalMapperDAO;
import com.project.picker.DTO.EvalDTO;

@Service
public class EvalServicempl implements EvalService {

	@Inject
	EvalMapperDAO edao; // ¡÷¿‘

	@Override
	public boolean EvalInsert(EvalDTO edto) {
		return edao.EvalInsert(edto) == 1;
	}

	@Override
	public int codeCount(String i_code) {
		return edao.codeCount(i_code);
	}

	@Override
	public ArrayList<EvalDTO> evalList(String i_code, int startRow, int endRow) {
		ArrayList<EvalDTO> list = edao.evalList(i_code, startRow, endRow);
		for(EvalDTO dto : list) {
			dto.setE_date(dto.getE_date().substring(0, 16));
		}
		return list;
	}
	
}
