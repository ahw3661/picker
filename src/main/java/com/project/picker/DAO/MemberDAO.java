package com.project.picker.DAO;

import java.util.List;

import com.project.picker.DTO.BuyDTO;
import com.project.picker.DTO.MemberDTO;
import com.project.picker.DTO.PointDTO;

public interface MemberDAO {

	public void updateMember(MemberDTO mdto);
	public List<PointDTO> pointList(String m_id, int startRow, int endRow);
	public List<BuyDTO> buyList(String m_id, int startRow, int endRow);
	
}
