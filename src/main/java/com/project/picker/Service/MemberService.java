package com.project.picker.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.project.picker.DTO.BuyDTO;
import com.project.picker.DTO.BuyitemDTO;
import com.project.picker.DTO.LoginDTO;
import com.project.picker.DTO.MemberDTO;
import com.project.picker.DTO.PointDTO;

public interface MemberService {

	//public ArrayList<MemberDTO> memberList();
	public MemberDTO loginCheck(LoginDTO ldto);
	public MemberDTO viewMember(String m_id);
	public void updateMemberType(String m_id);
	public void updateMember(MemberDTO mdto);
	public int pwCheck(MemberDTO mdto);
	public List<PointDTO> pointList(String m_id, int startRow, int endRow);
	public int onePoint(String m_id);
	public String findId(MemberDTO mdto);
	public String findPw(String m_id);
	public List<BuyDTO> buyList(String m_id, String start_date, String end_date, int startRow, int endRow);
	public ArrayList<BuyitemDTO> buyItem();
	public BuyDTO oneBuyInfo(String m_id, int b_code);
	public ArrayList<BuyitemDTO> oneBuyItemInfo(int b_code);
	public int getBuyCount(String m_id, String start_date, String end_date);
	public int getPointCount(String m_id);
	public void loginRemember(String sessionId, Date sessionLimit, String m_id);
	public MemberDTO getSessionUser(String sessionId);
	public BuyDTO buyCheck(BuyDTO bdto);
	public BuyDTO noneOneBuyInfo(int b_code, String b_order_phone);
	public ArrayList<BuyitemDTO> noneOneBuyItemInfo(int b_code);
	public int sumBuyPrice(int b_code);
	public int buyCancelListCount(String m_id);
	public ArrayList<BuyDTO> buyCancelList(String m_id);
	public Integer usePoint(int b_code);
	public void buyState(int b_code);
	public void buyCancelPoint(String m_id, int b_code);
	public int sumPoint(String m_id);
	public void updatePoint(String m_id, int m_point);
	public int getBuyCancelCount(String m_id, String start_date, String end_date);
	public List<BuyDTO> buyCancelContent(String m_id, String start_date, String end_date, int startRow, int endRow);
	public BuyDTO oneBuyCancelInfo(String m_id, int b_code);
	public ArrayList<BuyitemDTO> oneBuyCancelItemInfo(int b_code);
	public Integer preUsePoint(int b_code);
	public Date getCancelDate(int b_code);
	
}
