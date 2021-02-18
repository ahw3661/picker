package com.project.picker.DAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.project.picker.DTO.PointDTO;

@Repository
public class PointDAOImpl implements PointDAO {
	
	@Inject
	SqlSession sqlSession;
	
	@Override
	public List<PointDTO> allPointList(int startRow, int endRow) {
		// 전체 회원 포인트 목록
		Map<String, Object>map = new HashMap<>();
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		return sqlSession.selectList("point.allPointList", map);
	}

	@Override
	public List<PointDTO> onePointDetail(String m_id, int startRow, int endRow) {
		// 회원별 포인트 내역
		Map<String, Object>map = new HashMap<>();
		map.put("m_id", m_id);
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		return sqlSession.selectList("point.onePointDetail", map);
	}

	@Override
	public void buyCancelPoint(String m_id, int b_code) {
		// 구매 취소 포인트 변경
		Map<String, Object>map = new HashMap<>();
		map.put("m_id", m_id);
		map.put("b_code", b_code);
		sqlSession.selectList("point.buyCancelPoint", map);
		
	}

}
