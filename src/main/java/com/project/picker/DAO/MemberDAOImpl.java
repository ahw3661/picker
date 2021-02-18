package com.project.picker.DAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.project.picker.DTO.BuyDTO;
import com.project.picker.DTO.MemberDTO;
import com.project.picker.DTO.PointDTO;

@Repository // 서버가 startup 될 때 이 클래스가 메모리에 자동으로 등록됨
public class MemberDAOImpl implements MemberDAO {

	// sqlSession 객체를 개발자가 직접 생성하지 않고 스프링에서 연결시켜 줌
	@Inject // 의존관계 주입
	SqlSession sqlSession;

	@Override
	public void updateMember(MemberDTO mdto) {
		if(mdto.getM_newpassword().equals("")) {
			// 내 정보 수정(비밀번호 제외)
			sqlSession.selectOne("member.updateMember", mdto);
		}else {
			// 비밀번호 수정
			sqlSession.selectOne("member.updatePassword", mdto);
		}
	}

	@Override
	public List<BuyDTO> buyList(String m_id, String start_date, String end_date, int startRow, int endRow) {
		// 구매 내역
		Map<String, Object>map = new HashMap<>();
		map.put("m_id", m_id);
		map.put("start_date", start_date);
		map.put("end_date", end_date);
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		return sqlSession.selectList("member.buyList", map);
	}
	
	@Override
	public List<PointDTO> pointList(String m_id, int startRow, int endRow) {
		// 포인트 내역
		Map<String, Object>map = new HashMap<>();
		map.put("m_id", m_id);
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		return sqlSession.selectList("member.pointList", map);
	}

	@Override
	public List<BuyDTO> buyCancelContent(String m_id, String start_date, String end_date, int startRow, int endRow) {
		// 구매취소 내역
		Map<String, Object>map = new HashMap<>();
		map.put("m_id", m_id);
		map.put("start_date", start_date);
		map.put("end_date", end_date);
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		return sqlSession.selectList("member.buyCancelContent", map);
	}

}
