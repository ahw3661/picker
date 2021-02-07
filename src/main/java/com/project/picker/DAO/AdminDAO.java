package com.project.picker.DAO;

import java.util.List;

import com.project.picker.DTO.BuyDTO;
import com.project.picker.DTO.MemberDTO;

public interface AdminDAO {

	List<MemberDTO> memberList(int startRow, int endRow);

	List<BuyDTO> allBuyList(int startRow, int endRow);

}
