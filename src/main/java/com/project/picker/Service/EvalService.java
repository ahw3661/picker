package com.project.picker.Service;

import java.util.ArrayList;

import com.project.picker.DTO.EvalDTO;

public interface EvalService {
	
	public boolean EvalInsert(EvalDTO edto);
	public int codeCount(String i_code);
	public ArrayList<EvalDTO>evalList(String i_code, int startRow, int endRow );
}
