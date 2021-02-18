package com.project.picker.Service;

import com.project.picker.DTO.MemberDTO;

public interface JoinService {

	public void insertMember(MemberDTO mdto); // 회원가입
	public void insertJoinPoint(String m_id, String p_history, int p_point); // 회원가입 시 포인트 지급
	public int idCheck(String id); // 아이디 사용 가능 여부 체크
	public int emailCheck(String m_email); // 이메일 사용 가능 여부 체크
	public int phoneCheck(String m_phone); // 전화번호 사용 가능 여부 체크
	
}
