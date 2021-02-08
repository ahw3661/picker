package com.project.mapper;

import java.util.ArrayList;
import java.util.Date;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.project.picker.DTO.BuyDTO;
import com.project.picker.DTO.BuyitemDTO;
import com.project.picker.DTO.LoginDTO;
import com.project.picker.DTO.MemberDTO;

@Repository
public interface MemberMapperDAO {
	// ȸ������ - �� ����
	@Select("SELECT * FROM picker_member WHERE m_id = #{m_id}")
	public MemberDTO viewMember(@Param("m_id") String m_id);
	
	// �α��� üũ
	@Select("SELECT m_id, m_password, m_name, m_type FROM picker_member WHERE m_id = #{m_id} AND m_password = #{m_password} AND m_type IN (0, 1)")
	public MemberDTO loginCheck(LoginDTO ldto);
	
	// �α��� ���� ���� - �α��� ���� �� ���� id �� ��ȿ�ð� ���̺� ����
	@Select("UPDATE picker_member SET session_key = #{sessionId}, session_limit = #{sessionLimit} WHERE m_id = #{m_id}")
	public void loginRemember(@Param("sessionId") String sessionId, @Param("sessionLimit") Date sessionLimit, @Param("m_id") String m_id);
	
	// �α��� ���� ���� - ���� �α��� ����. ��ȿ�ð��� ���� �ְ� �ش� sessionId�� ������ ����� ����
	@Select("SELECT m_id, m_name, m_type FROM picker_member WHERE session_key = #{sessionId} and session_limit > sysdate")
	public MemberDTO getSessionUser(@Param("sessionId") String sessionId);
	
	// ��й�ȣ ��ġ ���� üũ
	@Select("SELECT COUNT(*) FROM picker_member WHERE m_id = #{m_id} AND m_password = #{m_password}")
	public int pwCheck(MemberDTO mdto);
	
	// ȸ��Ż�� - ȸ������ ����
	@Update("UPDATE picker_member SET m_point = 0, m_type = 2 WHERE m_id = #{m_id}")
	public void updateMemberType(@Param("m_id") String m_id);
	
	// ȸ��Ż�� - qna id ����
	@Update("UPDATE picker_qna SET m_id = '' WHERE m_id = #{m_id}")
	public void updateQna(@Param("m_id") String m_id);
	
	// ȸ��Ż�� - eval id ����
	@Update("UPDATE picker_eval SET m_id = '' WHERE m_id = #{m_id}")
	public void updateEval(@Param("m_id") String m_id);
	
	// ȸ��Ż�� - reply id ����
	@Update("UPDATE picker_reply SET m_id = '' WHERE m_id = #{m_id}")
	public void updateReply(@Param("m_id") String m_id);
	
	// ȸ��Ż�� - ����Ʈ ����
	@Delete("DELETE FROM picker_point WHERE m_id = #{m_id}")
	public void deletePoint(@Param("m_id") String m_id);
	
	// ȸ��Ż�� - ��ٱ��� ����
	@Delete("DELETE FROM picker_cart WHERE m_id = #{m_id}")
	public void deleteCart(@Param("m_id") String m_id);
	
	// ȸ���� �� ����Ʈ
	@Select("SELECT m_point FROM picker_member WHERE m_id = #{m_id}")
	public int onePoint(@Param("m_id") String m_id);
	
	// ���̵�ã�� - �̸���
	@Select("SELECT m_id FROM picker_member WHERE m_email = #{m_email}")
	public String findEmailId(@Param("m_email") String m_email);
	
	// ���̵� ã�� - �̸�, ����ó
	@Select("SELECT m_id FROM picker_member WHERE m_name = #{m_name} AND m_phone = #{m_phone}")
	public String findNamePhoneId(MemberDTO mdto);
	
	// ��й�ȣ ã�� - ���̵�
	@Select("SELECT m_password FROM picker_member WHERE m_id = #{m_id}")
	public String findIdPassword(@Param("m_id") String m_id);
	
	// ȸ���� ���� ��� ī��Ʈ
	@Select("SELECT COUNT(*) FROM picker_buy WHERE m_id = #{m_id} AND b_chk = 0 AND b_date BETWEEN TO_DATE(#{start_date}, 'yy/MM/dd') AND TO_DATE(#{end_date}, 'yy/MM/dd')+1")
	public int getBuyCount(@Param("m_id") String m_id, @Param("start_date") String start_date, @Param("end_date") String end_date);
	
	// ȸ���� ����Ʈ ī��Ʈ
	@Select("SELECT COUNT(*) FROM picker_point WHERE m_id = #{m_id}")
	public int getPointCount(@Param("m_id") String m_id);

	// ���Ż�ǰ
	@Select("SELECT * FROM picker_buyitem ORDER BY bi_num DESC")
	public ArrayList<BuyitemDTO> buyItem();

	// ���Ż�1
	@Select("SELECT b_code, b_take_name, b_take_phone, b_take_zipcode, b_take_roadaddr, b_take_detailaddr, b_price, b_date FROM picker_buy WHERE m_id = #{m_id} AND b_code = #{b_code} AND b_chk = 0")
	public BuyDTO oneBuyInfo(@Param("m_id") String m_id, @Param("b_code") int b_code);

	// ���Ż�2
	@Select("SELECT i_name, bi_cnt, i_price FROM picker_buyitem WHERE b_code = #{b_code}")
	public ArrayList<BuyitemDTO> oneBuyItemInfo(@Param("b_code") int b_code);
	
	// ��ǰ���� �ݾ� �հ�
	@Select("SELECT SUM(bi_cnt*i_price) FROM picker_buyitem WHERE b_code = #{b_code}")
	public int sumBuyPrice(@Param("b_code") int b_code);

	// ��ȸ�� ���ų��� üũ
	@Select("SELECT b_code, b_order_phone FROM picker_buy WHERE b_code = #{b_code} AND b_order_phone = #{b_order_phone} AND b_chk = 0")
	public BuyDTO buyCheck(BuyDTO bdto);
	
	// ��ȸ�� ���Ż�1
	@Select("SELECT b_code, b_take_name, b_take_phone, b_take_zipcode, b_take_roadaddr, b_take_detailaddr, b_price, b_date FROM picker_buy WHERE b_code = #{b_code} AND b_order_phone = #{b_order_phone} AND b_chk = 0")
	public BuyDTO noneOneBuyInfo(@Param("b_code") int b_code, @Param("b_order_phone") String b_order_phone);

	// ��ȸ�� ���Ż�2
	@Select("SELECT i_img, i_name, bi_cnt, i_price FROM picker_buyitem WHERE b_code = #{b_code}")
	public ArrayList<BuyitemDTO> noneOneBuyItemInfo(@Param("b_code") int b_code);

	// ȸ���� �ֹ���� ���� ���� ���
	@Select("SELECT b_code, m_id, b_date, b_price FROM picker_buy WHERE m_id = #{m_id} AND b_date >= TO_CHAR(sysdate - 1, 'yy/MM/dd') AND b_chk = 0 ORDER BY b_date DESC, b_code DESC")
	public ArrayList<BuyDTO> buyCancelList(@Param("m_id") String m_id);
	
	// ȸ���� �ֹ���� ���� ���� ��� ī��Ʈ
	@Select("SELECT COUNT(*) FROM picker_buy WHERE m_id = #{m_id} AND b_date >= TO_CHAR(sysdate - 1, 'yy/MM/dd') AND b_chk = 0")
	public int buyCancelListCount(@Param("m_id") String m_id);

	// ���Ž� ����� ����Ʈ
	@Select("SELECT p_point FROM picker_point WHERE b_code = #{b_code} AND p_point < 0")
	public Integer usePoint(@Param("b_code") int b_code); // int�� null�� ����ȯ�� �� ����. Integer�� �޾ƾ� ��.
	
	// ������� - ������� ����, �������
	@Update("UPDATE picker_buy SET b_chk = 1, u_date = sysdate WHERE b_code = #{b_code} AND b_chk = 0")
	public void buyState(@Param("b_code") int b_code);
	
	// ������� - ���� ��� �� �� ����Ʈ
	@Select("SELECT SUM(p_point) AS p_point FROM picker_point WHERE m_id = #{m_id}")
	public int sumPoint(@Param("m_id") String m_id);
	
	// ������� - �� ����Ʈ ���� 
	@Insert("UPDATE picker_member SET m_point = #{m_point} WHERE m_id = #{m_id}")
	public void updatePoint(@Param("m_id") String m_id, @Param("m_point") int m_point);

	// ȸ���� �ֹ���ҿϷ� ī��Ʈ
	@Select("SELECT COUNT(*) FROM picker_buy WHERE m_id = #{m_id} AND b_chk = 1 AND b_date BETWEEN TO_DATE(#{start_date}, 'yy/MM/dd') AND TO_DATE(#{end_date}, 'yy/MM/dd')+1")
	public int getBuyCancelCount(@Param("m_id") String m_id, @Param("start_date") String start_date, @Param("end_date") String end_date);
	
	// ������ҿϷ� ��1
	@Select("SELECT b_code, b_take_name, b_take_phone, b_take_zipcode, b_take_roadaddr, b_take_detailaddr, b_price, b_date FROM picker_buy WHERE m_id = #{m_id} AND b_code = #{b_code} AND b_chk = 1")
	public BuyDTO oneBuyCancelInfo(@Param("m_id") String m_id, @Param("b_code") int b_code);

	// ������ҿϷ� ��2
	@Select("SELECT i_name, bi_cnt, i_price FROM picker_buyitem WHERE b_code = #{b_code}")
	public ArrayList<BuyitemDTO> oneBuyCancelItemInfo(@Param("b_code") int b_code);
	
	// ������� �� ����� ����Ʈ
	@Select("SELECT p_point FROM picker_point WHERE b_code = #{b_code} AND p_history LIKE '%���%'")
	public Integer preUsePoint(@Param("b_code") int b_code); // int�� null�� ����ȯ�� �� ����. Integer�� �޾ƾ� ��.
	
	// �����������
	@Select("SELECT DISTINCT(u_date) FROM picker_buy WHERE b_code = #{b_code} AND b_chk = 1")
	public Date getCancelDate(@Param("b_code") int b_code);
		
}
