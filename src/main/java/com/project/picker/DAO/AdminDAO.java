package com.project.picker.DAO;

import java.util.List;

import com.project.picker.DTO.BuyDTO;
import com.project.picker.DTO.ItemDTO;
import com.project.picker.DTO.ItemQnaDTO;
import com.project.picker.DTO.MemberDTO;

public interface AdminDAO {

	List<MemberDTO> memberList(String s_type, String m_keyword, int m_type, int startRow, int endRow);

	List<BuyDTO> allBuyList(int startRow, int endRow);

	int qnaCount(String column, String keyword, String code, int rchk);
	
	List<ItemQnaDTO> qnaList(String column, String keyword, String code, int rchk, int startRow, int endRow);
	
	List<BuyDTO> allBuyCancel(int startRow, int endRow);

	public void itemUpdate(ItemDTO idto);

	public int getAllMemberCount(String s_type, String m_keyword, int m_type);

}
