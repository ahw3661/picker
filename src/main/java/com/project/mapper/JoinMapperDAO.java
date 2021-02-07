package com.project.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface JoinMapperDAO {

	// 아이디 사용 가능 여부 체크
	@Select("SELECT COUNT(*) FROM picker_member WHERE m_id = #{m_id}")
	public int idCheck(@Param("m_id") String m_id);
}