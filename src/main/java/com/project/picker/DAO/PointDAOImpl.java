package com.project.picker.DAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.project.picker.DTO.PointDTO;

@Repository
public class PointDAOImpl implements PointDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(PointDAOImpl.class);

	@Inject
	SqlSession sqlSession;
	
	@Override
	public List<PointDTO> allPointList(int startRow, int endRow) {
		logger.info(">>> 전체 회원 포인트 목록");
		Map<String, Object>map = new HashMap<>();
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		return sqlSession.selectList("point.allPointList", map);
	}

	@Override
	public List<PointDTO> onePointDetail(String m_id, int startRow, int endRow) {
		logger.info(">>> 회원별 포인트 내역");
		Map<String, Object>map = new HashMap<>();
		map.put("m_id", m_id);
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		return sqlSession.selectList("point.onePointDetail", map);
	}

}
