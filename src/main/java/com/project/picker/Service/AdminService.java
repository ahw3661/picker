package com.project.picker.Service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.project.picker.DTO.BuyDTO;
import com.project.picker.DTO.BuyitemDTO;
import com.project.picker.DTO.ItemDTO;
import com.project.picker.DTO.ItemInsertDTO;
import com.project.picker.DTO.ItemQnaDTO;
import com.project.picker.DTO.MemberDTO;
import com.project.picker.DTO.PointDTO;

public interface AdminService {

	public List<MemberDTO> memberList(String s_type, String m_keyword, int m_type, int startRow, int endRow);
	public MemberDTO oneList(String m_id);
	public ArrayList<ItemDTO> itemList(int startRow, int endRow);
	public int itemListCount();
	public ItemDTO oneItemList(String i_code);
	public int memberCount();
	public int itemCount();
	public String getCode();
	public void ItemInsert(ItemInsertDTO idto, HttpSession session);
	public List<PointDTO> allPointList(int startRow, int endRow);
	public List<PointDTO> onePointDetail(String m_id, int startRow, int endRow);
	public int getAllPointCount();
	public int getOnePointCount(String m_id);
	public int getAllMemberCount();
	public int getAllBuyCount();
	public List<BuyDTO> allBuyList(int startRow, int endRow);
	public ArrayList<BuyitemDTO> allBuyItem();
	public BuyDTO getOneBuyInfo(int b_code);
	public ArrayList<BuyitemDTO> getOneBuyItemInfo(int b_code);
	public int getSumBuyPrice(int b_code);
	public ArrayList<BuyitemDTO> buyItemList();
	public int getAllBuyCancelCount();
	public List<BuyDTO> allBuyCancel(int startRow, int endRow);
	public BuyDTO oneBuyCancel(int b_code);
	public void itemUpdate(ItemInsertDTO idto, HttpSession session);
	public int getAllMemberCount(String s_type, String m_keyword, int m_type);
	public int qnaCount(String column, String keyword, String code, int rchk);
	public List<ItemQnaDTO> qnaList(String column, String keyword, String code, int rchk, int startRow, int endRow);
	public ArrayList<ItemDTO> getItemNameList();
	
}
