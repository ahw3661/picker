package com.project.picker.Service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.project.mapper.JoinMapperDAO;
import com.project.picker.DAO.JoinDAO;
import com.project.picker.DTO.MemberDTO;

@Service
public class JoinServiceImpl implements JoinService {

	@Inject // 스프링 컨테이너가 만든 dao 객체가 연결됨(의존관계 주입)
	JoinDAO jdao;
	
	@Inject
	JoinMapperDAO jmdao;
	
	@Override
	public void insertMember(MemberDTO mdto) {
		jdao.insertMember(mdto);
	}

	@Override
	public void insertJoinPoint(String m_id, String p_history, int p_point) {
		jdao.insertJoinPoint(m_id, p_history, p_point);
	}
	
	@Override
	public int idCheck(String m_id) {
		return jmdao.idCheck(m_id);
	}

}
