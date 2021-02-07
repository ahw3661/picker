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

@Repository
public class AdminDAOImpl implements AdminDAO {

	private static final Logger logger = LoggerFactory.getLogger(AdminDAOImpl.class);
	
	@Inject
	SqlSession sqlSession;
	
	@Override
	public List<MemberDTO> memberList(int startRow, int endRow) {
		logger.info(">>> 전체 회원 목록");
		Map<String, Object>map = new HashMap<>();
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		return sqlSession.selectList("admin.memberList", map);
	}

	@Override
	public List<BuyDTO> allBuyList(int startRow, int endRow) {
		logger.info(">>> 전체 구매 목록");
		Map<String, Object>map = new HashMap<>();
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		return sqlSession.selectList("admin.allBuyList", map);
	}

}
