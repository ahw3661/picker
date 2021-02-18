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
	public List<ItemDTO> itemList(String s_type, String m_keyword, String i_category, int i_chk, int startRow, int endRow);
	public int itemListCount(String s_type, String m_keyword, String i_category, int i_chk);
	public ItemDTO oneItemList(String i_code);
	public int memberCount(); // �� ȸ�� ��
	public int itemCount(); // �� ��ǰ ��
	public String getCode();
	public void ItemInsert(ItemInsertDTO idto, HttpSession session);
	public List<PointDTO> allPointList(int startRow, int endRow); // ��ü ����Ʈ ���
	public List<PointDTO> onePointDetail(String m_id, int startRow, int endRow); // ȸ�� ����Ʈ ��
	public int getAllPointCount(); // ��ü ����Ʈ �Ǽ�
	public int getOnePointCount(String m_id); // ȸ�� ����Ʈ �Ǽ�
	public int getAllMemberCount();
	public int getAllBuyCount(String start_date, String end_date); // ��ü ���� ��� �Ǽ�
	public List<BuyDTO> allBuyList(String start_date, String end_date, int startRow, int endRow); // ��ü ���� ���
	public ArrayList<BuyitemDTO> allBuyItem(); // ��ü ���� ��� ��ǰ
	public BuyDTO getOneBuyInfo(int b_code); // ���� ��� ��
	public ArrayList<BuyitemDTO> getOneBuyItemInfo(int b_code); // ���� ��� �� ��ǰ
	public int getSumBuyPrice(int b_code); // ���� �� �ݾ�
	public ArrayList<BuyitemDTO> buyItemList(); // ��ü ���� ��� �Ϸ� ��� ��ǰ 
	public int getAllBuyCancelCount(String start_date, String end_date); // ��ü ���� ��� �Ϸ� �Ǽ�
	public List<BuyDTO> allBuyCancel(String start_date, String end_date, int startRow, int endRow); // ��ü ���� ��� �Ϸ� ���
	public BuyDTO oneBuyCancel(int b_code); // ��ü ���� ��� �Ϸ� ��
	public void itemUpdate(ItemInsertDTO idto, HttpSession session); // ��ǰ ���� ����
	public int getAllMemberCount(String s_type, String m_keyword, int m_type); // ��ü ȸ�� �˻� ī��Ʈ
	public int qnaCount(String column, String keyword, String code, int rchk);
	public List<ItemQnaDTO> qnaList(String column, String keyword, String code, int rchk, int startRow, int endRow);
	public ArrayList<ItemDTO> getItemNameList();
	
}
