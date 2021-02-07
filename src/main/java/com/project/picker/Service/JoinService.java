package com.project.picker.Service;

import com.project.picker.DTO.MemberDTO;

public interface JoinService {

	public void insertMember(MemberDTO mdto);
	public void insertJoinPoint(String m_id, String p_history, int p_point);
	public int idCheck(String id);
}
