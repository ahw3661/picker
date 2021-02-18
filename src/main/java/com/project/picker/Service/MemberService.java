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
	
	public MemberDTO loginCheck(LoginDTO ldto); // �α���
	public MemberDTO viewMember(String m_id); // ȸ������
	public void updateMemberType(String m_id); // ȸ������ ����
	public void updateMember(MemberDTO mdto); // ȸ������ ����
	public int pwCheck(MemberDTO mdto); // ��й�ȣ üũ
	public List<PointDTO> pointList(String m_id, int startRow, int endRow); // ȸ�� ����Ʈ ���
	public int onePoint(String m_id); // ȸ�� �� ����Ʈ
	public String findId(MemberDTO mdto); // ���̵� ã��
	public String findPw(String m_id); // ��й�ȣ ã��
	public List<BuyDTO> buyList(String m_id, String start_date, String end_date, int startRow, int endRow); // ȸ�� ���� ���
	public ArrayList<BuyitemDTO> buyItem(); // ȸ�� ���� ��� ��ǰ
	public BuyDTO oneBuyInfo(String m_id, int b_code); // ȸ�� ���� ��
	public ArrayList<BuyitemDTO> oneBuyItemInfo(int b_code); // ȸ�� ���� �� ��ǰ
	public int getBuyCount(String m_id, String start_date, String end_date); // ȸ�� �ֹ� �Ǽ�
	public int getPointCount(String m_id); // ȸ�� ����Ʈ �Ǽ�
	public void loginRemember(String sessionId, Date sessionLimit, String m_id); // ���� id�� ��ȿ�ð�
	public MemberDTO getSessionUser(String sessionId); // ���� �α��� ���� Ȯ��
	public BuyDTO buyCheck(BuyDTO bdto); // ��ȸ�� �ֹ���ȸ
	public BuyDTO noneOneBuyInfo(int b_code, String b_order_phone); // ��ȸ�� ���� ����
	public ArrayList<BuyitemDTO> noneOneBuyItemInfo(int b_code); // ��ȸ�� ���� ��ǰ
	public int sumBuyPrice(int b_code); // ���� �� �ݾ�
	public int buyCancelListCount(String m_id); // ȸ�� ���� ��� ���� ��� �Ǽ�
	public ArrayList<BuyDTO> buyCancelList(String m_id); // ȸ�� ���� ��� ���� ���
	public Integer usePoint(int b_code); // ����� ����Ʈ
	public void buyState(int b_code); // ���� ���
	public void buyCancelPoint(String m_id, int b_code); // ȸ�� ���� ��� ����Ʈ ����
	public int sumPoint(String m_id); // ���� ��� �� �� ����Ʈ
	public void updatePoint(String m_id, int m_point); // ȸ�� �� ����Ʈ ����
	public int getBuyCancelCount(String m_id, String start_date, String end_date); // ȸ�� ���� ��� �Ϸ� ��� �Ǽ�
	public List<BuyDTO> buyCancelContent(String m_id, String start_date, String end_date, int startRow, int endRow); // ȸ�� ���� ��� �Ϸ� ���
	public BuyDTO oneBuyCancelInfo(String m_id, int b_code); // ȸ�� ���� ��� �Ϸ� ��
	public ArrayList<BuyitemDTO> oneBuyCancelItemInfo(int b_code); // ȸ�� ���� ��� �Ϸ� �� ��ǰ
	public Integer preUsePoint(int b_code); // ���� ��� �� ��� ����Ʈ
	public Date getCancelDate(int b_code); // ���� �������
	
}
