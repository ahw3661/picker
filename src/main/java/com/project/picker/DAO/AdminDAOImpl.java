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
import com.project.picker.DTO.ItemDTO;
import com.project.picker.DTO.ItemQnaDTO;
import com.project.picker.DTO.MemberDTO;

@Repository
public class AdminDAOImpl implements AdminDAO {

	private static final Logger logger = LoggerFactory.getLogger(AdminDAOImpl.class);
	
	@Inject
	SqlSession sqlSession;
	
	@Override
	public List<MemberDTO> memberList(String s_type, String m_keyword, int m_type, int startRow, int endRow) {
		logger.info(">>> ��ü ȸ�� ���");
		Map<String, Object>map = new HashMap<>();
		map.put("s_type", s_type);
		map.put("m_keyword", m_keyword);
		map.put("m_type", m_type);
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		return sqlSession.selectList("admin.memberList", map);
	}

	@Override
	public List<BuyDTO> allBuyList(String start_date, String end_date, int startRow, int endRow) {
		logger.info(">>> ��ü ���� ���");
		Map<String, Object>map = new HashMap<>();
		map.put("start_date", start_date);
		map.put("end_date", end_date);
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		return sqlSession.selectList("admin.allBuyList", map);
	}
	@Override
	public int qnaCount(String column, String keyword, String code, int rchk) {
		logger.info("���Ǳ� ���� Ȯ��");
		Map<String, Object> map = new HashMap<>();
		map.put("keyword", keyword);
		map.put("column", column);
		map.put("code", code);
		map.put("rchk", rchk);
		return sqlSession.selectOne("admin.qnaCount", map);
	}
	@Override
	public List<ItemQnaDTO> qnaList(String column, String keyword, String code, int rchk, int startRow, int endRow) {
		logger.info("���� ��� ȣ��");
		Map<String, Object> map = new HashMap<>();
		map.put("keyword", keyword);
		map.put("column", column);
		map.put("code", code);
		map.put("rchk", rchk);
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		return sqlSession.selectList("admin.qnaList", map);
	}

	@Override
	public List<BuyDTO> allBuyCancel(String start_date, String end_date, int startRow, int endRow) {
		logger.info(">>> ��ü ������� ���");
		Map<String, Object>map = new HashMap<>();
		map.put("start_date", start_date);
		map.put("end_date", end_date);
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		return sqlSession.selectList("admin.allBuyCancel", map);
	}

	@Override
	public void itemUpdate(ItemDTO idto) {
		logger.info(">>> ��ǰ ���� ����");
		sqlSession.update("admin.itemUpdate", idto);
	}

	@Override
	public int getAllMemberCount(String s_type, String m_keyword, int m_type) {
		logger.info(">>> ��ü ȸ�� �˻� ī��Ʈ");
		Map<String, Object>map = new HashMap<>();
		map.put("s_type", s_type);
		map.put("m_keyword", m_keyword);
		map.put("m_type", m_type);
		return sqlSession.selectOne("admin.getAllMemberCount", map);
	}

	@Override
	public int itemListCount(String s_type, String m_keyword, String i_category, int i_chk) {
		logger.info(">>> ��ü ��ǰ �˻� ī��Ʈ");
		Map<String, Object>map = new HashMap<>();
		map.put("s_type", s_type);
		map.put("m_keyword", m_keyword);
		map.put("i_category", i_category);
		map.put("i_chk", i_chk);
		return sqlSession.selectOne("admin.itemListCount", map);
	}

	@Override
	public List<ItemDTO> itemList(String s_type, String m_keyword, String i_category, int i_chk, int startRow, int endRow) {
		logger.info(">>> ��ü ��ǰ ���");
		Map<String, Object>map = new HashMap<>();
		map.put("s_type", s_type);
		map.put("m_keyword", m_keyword);
		map.put("i_category", i_category);
		map.put("i_chk", i_chk);
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		return sqlSession.selectList("admin.itemList", map);
	}

}
