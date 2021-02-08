package com.project.picker.Service;

import java.util.ArrayList;

import com.project.picker.DTO.EvalDTO;

public interface EvalService {
	
	public boolean evalInsert(EvalDTO dto);
	public boolean evalUpdate(EvalDTO dto);
	public boolean evalDelete(int e_num);
	public int codeCount(String i_code);
	public ArrayList<EvalDTO> evalList(String i_code, int startRow, int endRow);
	public EvalDTO getEvalByNumber(int num);
	public String getBuyer(int b_code);
	
}
