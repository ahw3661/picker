package com.project.picker.Service;

import com.project.picker.DTO.MemberDTO;

public interface JoinService {

	public void insertMember(MemberDTO mdto); // ȸ������
	public void insertJoinPoint(String m_id, String p_history, int p_point); // ȸ������ �� ����Ʈ ����
	public int idCheck(String id); // ���̵� ��� ���� ���� üũ
	public int emailCheck(String m_email); // �̸��� ��� ���� ���� üũ
	public int phoneCheck(String m_phone); // ��ȭ��ȣ ��� ���� ���� üũ
	
}
