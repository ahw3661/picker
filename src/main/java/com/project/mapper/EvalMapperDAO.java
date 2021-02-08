package com.project.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.project.picker.DTO.EvalDTO;

@Repository
public interface EvalMapperDAO {

	// ���� ���
	@Insert("INSERT INTO picker_eval VALUES ((SELECT NVL(MAX(e_num)+1,1) FROM picker_eval), "
			+ "#{e_content}, #{m_id}, #{m_name}, SYSDATE, #{b_code}, #{i_code}, #{e_level}, 0)")
	public int evalInsert(EvalDTO dto);
	// ���� ����
	@Update("UPDATE picker_eval SET e_content = #{e_content}, e_level = #{e_level} WHERE e_num = #{e_num}")
	public int evalUpdate(EvalDTO dto);
	
	// ���� ����
	@Update("UPDATE picker_eval SET e_chk = 1 WHERE e_num = #{e_num}")
	public int evalDelete(@Param("e_num") int e_num);
	
	// ���� ���� 
	@Select("SELECT COUNT(*) FROM picker_eval WHERE i_code=#{i_code} AND e_chk = 0")
	public int codeCount(@Param("i_code") String i_code);
	
	// ���� ���
	@Select("SELECT e_num, e_content, m_id, m_name, e_date, i_code, e_level FROM (SELECT i.*,ROWNUM AS rnum FROM "
			+ "(SELECT * FROM picker_eval WHERE i_code=#{i_code} AND e_chk = 0 ORDER BY e_num DESC) i) "
			+ "WHERE rnum BETWEEN #{startRow} AND #{endRow}")
	public ArrayList<EvalDTO> evalList(@Param("i_code") String i_code, @Param("startRow") int startRow, @Param("endRow") int endRow);
	
	// ȸ�� ���ű�� üũ
	@Select("SELECT COUNT(*) FROM picker_buyitem bi INNER JOIN picker_buy b ON bi.b_code = b.b_code "
			+ "WHERE bi.i_code = #{i_code} AND b.m_id = #{id}")
	public int memberBuyCheck(@Param("i_code") String i_code, @Param("id") String id);
	
	// ȸ�� ���Ź�ȣ üũ
	@Select("SELECT MIN(bi.b_code) FROM picker_buyitem bi INNER JOIN picker_buy b ON bi.b_code = b.b_code "
			+ "WHERE bi.i_code = #{i_code} AND b.m_id = #{id}")
	public int getMemberBuyNumber(@Param("i_code") String i_code, @Param("id") String id);
	// ȸ�� ������ �ۼ���� üũ
	@Select("SELECT COUNT(*) FROM picker_eval WHERE i_code = #{i_code} AND m_id = #{id} AND e_chk = 0")
	public int memberEvalCheck(@Param("i_code") String i_code, @Param("id") String id);
	
	// ȸ�� ���� ������ ��ȣ
	@Select("SELECT e_num FROM picker_eval WHERE i_code = #{i_code} AND m_id = #{id} AND e_chk = 0")
	public int getMemberEvalNumber(@Param("i_code") String i_code, @Param("id") String id);
	
	// ȸ�� ���� ������ ����
	@Select("SELECT e_num, e_content, e_level FROM picker_eval WHERE i_code = #{i_code} AND m_id = #{id} AND e_chk = 0")
	public EvalDTO getEvalById(@Param("i_code") String i_code, @Param("id") String id);
	// ��ȸ�� ���ű�� üũ
	@Select("SELECT COUNT(*) FROM picker_buy b INNER JOIN picker_buyitem bi ON b.b_code = bi.b_code "
			+ "WHERE b.b_code = #{b_code} AND b.b_order_phone = #{phone} AND b.m_id = 'none' AND bi.i_code = #{i_code}")
	public int noneMemberBuyCheck(@Param("b_code") int b_code, @Param("phone") String phone, @Param("i_code") String i_code);
	
	// ��ȸ�� ������ �ۼ���� üũ
	@Select("SELECT COUNT(*) FROM picker_eval WHERE b_code = #{b_code} AND i_code = #{i_code} AND e_chk = 0")
	public int noneMemberEvalCheck(@Param("b_code") int b_code, @Param("i_code") String i_code);
	
	// ��ȸ�� ���� ������ ��ȣ
	@Select("SELECT e_num FROM picker_eval WHERE b_code = #{b_code} AND i_code = #{i_code} AND e_chk = 0")
	public int getNoneMemberEvalNumber(@Param("b_code") int b_code, @Param("i_code") String i_code);
	
	// ��ȸ�� ������ ����
	@Select("SELECT b_order_name FROM picker_buy WHERE b_code = #{b_code}")
	public String getBuyer(@Param("b_code") int b_code);
	
	// ���� ������ ����
	@Select("SELECT e_num, e_content, e_level FROM picker_eval WHERE e_num = #{num}")
	public EvalDTO getEvalByNumber(@Param("num") int num);
	
	
}
