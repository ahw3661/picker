package com.project.picker.DAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.project.picker.DTO.BuyDTO;
import com.project.picker.DTO.MemberDTO;
import com.project.picker.DTO.PointDTO;

@Repository // 서버가 startup 될 때 이 클래스가 메모리에 자동으로 등록됨
public class MemberDAOImpl implements MemberDAO {

	// 로깅 처리를 위한 객체 선언
	private static final Logger logger = LoggerFactory.getLogger(MemberDAOImpl.class);
	
	// sqlSession 객체를 개발자가 직접 생성하지 않고 스프링에서 연결시켜 줌
	@Inject // 의존관계 주입
	SqlSession sqlSession;

	@Override
	public void updateMember(MemberDTO mdto) {
		if(mdto.getM_newpassword().equals("")) {
			logger.info(">>> 내 정보 수정(비밀번호 제외");
			sqlSession.selectOne("member.updateMember", mdto);
		}else {
			logger.info(">>> 비밀번호 수정");
			sqlSession.selectOne("member.updatePassword", mdto);
		}
	}

	@Override
	public List<BuyDTO> buyList(String m_id, int startRow, int endRow) {
		logger.info(">>> 구매 내역");
		Map<String, Object>map = new HashMap<>();
		map.put("m_id", m_id);
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		return sqlSession.selectList("member.buyList", map);
	}
	
	@Override
	public List<PointDTO> pointList(String m_id, int startRow, int endRow) {
		logger.info(">>> 포인트 내역");
		Map<String, Object>map = new HashMap<>();
		map.put("m_id", m_id);
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		return sqlSession.selectList("member.pointList", map);
	}

}
