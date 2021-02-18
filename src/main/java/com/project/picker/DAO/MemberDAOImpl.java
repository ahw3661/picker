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

@Repository // ������ startup �� �� �� Ŭ������ �޸𸮿� �ڵ����� ��ϵ�
public class MemberDAOImpl implements MemberDAO {

	// sqlSession ��ü�� �����ڰ� ���� �������� �ʰ� ���������� ������� ��
	@Inject // �������� ����
	SqlSession sqlSession;

	@Override
	public void updateMember(MemberDTO mdto) {
		if(mdto.getM_newpassword().equals("")) {
			// �� ���� ����(��й�ȣ ����)
			sqlSession.selectOne("member.updateMember", mdto);
		}else {
			// ��й�ȣ ����
			sqlSession.selectOne("member.updatePassword", mdto);
		}
	}

	@Override
	public List<BuyDTO> buyList(String m_id, String start_date, String end_date, int startRow, int endRow) {
		// ���� ����
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
		// ����Ʈ ����
		Map<String, Object>map = new HashMap<>();
		map.put("m_id", m_id);
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		return sqlSession.selectList("member.pointList", map);
	}

	@Override
	public List<BuyDTO> buyCancelContent(String m_id, String start_date, String end_date, int startRow, int endRow) {
		// ������� ����
		Map<String, Object>map = new HashMap<>();
		map.put("m_id", m_id);
		map.put("start_date", start_date);
		map.put("end_date", end_date);
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		return sqlSession.selectList("member.buyCancelContent", map);
	}

}
