package com.project.picker.DAO;

import com.project.picker.DTO.MemberDTO;

public interface JoinDAO {

	public void insertMember(MemberDTO mdto);
	public void insertJoinPoint(String m_id, String p_history, int p_point);
}
