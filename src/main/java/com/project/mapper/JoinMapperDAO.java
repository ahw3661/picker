package com.project.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface JoinMapperDAO {

	// ���̵� ��� ���� ���� üũ
	@Select("SELECT COUNT(*) FROM picker_member WHERE m_id = #{m_id}")
	public int idCheck(@Param("m_id") String m_id);
	
	// �̸��� ��� ���� ���� üũ
	@Select("SELECT COUNT(*) FROM picker_member WHERE m_email = #{m_email}")
	public int emailCheck(@Param("m_email") String m_email);
	
	// ��ȭ��ȣ ��� ���� ���� üũ
	@Select("SELECT COUNT(*) FROM picker_member WHERE m_phone = #{m_phone}")
	public int phoneCheck(@Param("m_phone") String m_phone);
	
}