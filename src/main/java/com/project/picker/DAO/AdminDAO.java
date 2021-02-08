package com.project.picker.DAO;

import java.util.List;

import com.project.picker.DTO.BuyDTO;
import com.project.picker.DTO.ItemDTO;
import com.project.picker.DTO.ItemQnaDTO;
import com.project.picker.DTO.MemberDTO;

public interface AdminDAO {

	List<MemberDTO> memberList(String s_type, String m_keyword, int m_type, int startRow, int endRow);

	List<BuyDTO> allBuyList(String start_date, String end_date, int startRow, int endRow);

	int qnaCount(String column, String keyword, String code, int rchk);
	
	List<ItemQnaDTO> qnaList(String column, String keyword, String code, int rchk, int startRow, int endRow);
	
	List<BuyDTO> allBuyCancel(String start_date, String end_date, int startRow, int endRow);

	void itemUpdate(ItemDTO idto);

	int getAllMemberCount(String s_type, String m_keyword, int m_type);

	int itemListCount(String s_type, String m_keyword, String i_category, int i_chk);

	List<ItemDTO> itemList(String s_type, String m_keyword, String i_category, int i_chk, int startRow, int endRow);
	

}
