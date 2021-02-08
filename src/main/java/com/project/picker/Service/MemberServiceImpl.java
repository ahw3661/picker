package com.project.picker.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.project.mapper.MemberMapperDAO;
import com.project.picker.DAO.MemberDAO;
import com.project.picker.DAO.PointDAO;
import com.project.picker.DTO.BuyDTO;
import com.project.picker.DTO.BuyitemDTO;
import com.project.picker.DTO.LoginDTO;
import com.project.picker.DTO.MemberDTO;
import com.project.picker.DTO.PointDTO;

@Service
public class MemberServiceImpl implements MemberService {

	@Inject
	MemberDAO mdao;
	
	@Inject
	MemberMapperDAO mmdao;
	
	@Inject
	PointDAO pdao;
	
	//@Override
	//public ArrayList<MemberDTO> memberList() {
	//	return mmdao.memberList();
	//}

	@Override
	public MemberDTO viewMember(String m_id) {
		return mmdao.viewMember(m_id);
	}

	@Override
	public void updateMemberType(String m_id) {
		mmdao.deletePoint(m_id);
		mmdao.deleteCart(m_id);
		mmdao.updateQna(m_id);
		mmdao.updateEval(m_id);
		mmdao.updateReply(m_id);
		mmdao.updateMemberType(m_id);
	}

	@Override
	public void updateMember(MemberDTO mdto) {
		mdao.updateMember(mdto);
	}

	@Override
	public MemberDTO loginCheck(LoginDTO ldto) {
		return mmdao.loginCheck(ldto);
	}

	@Override
	public int pwCheck(MemberDTO mdto) {
		return mmdao.pwCheck(mdto);
	}

	@Override
	public List<PointDTO> pointList(String m_id, int startRow, int endRow) {
		return mdao.pointList(m_id, startRow, endRow);
	}

	@Override
	public int onePoint(String m_id) {
		return mmdao.onePoint(m_id);
	}

	@Override
	public String findId(MemberDTO mdto) {
		if(mdto.getM_email() != null) {
			return mmdao.findEmailId(mdto.getM_email());
		}else {
			return mmdao.findNamePhoneId(mdto);
		}
	}

	@Override
	public String findPw(String m_id) {
		return mmdao.findIdPassword(m_id);
	}

	@Override
	public List<BuyDTO> buyList(String m_id, String start_date, String end_date, int startRow, int endRow) {
		return mdao.buyList(m_id, start_date, end_date, startRow, endRow);
	}
	
	@Override
	public ArrayList<BuyitemDTO> buyItem() {
		return mmdao.buyItem();
	}

	@Override
	public BuyDTO oneBuyInfo(String m_id, int b_code) {
		return mmdao.oneBuyInfo(m_id, b_code);
	}

	@Override
	public ArrayList<BuyitemDTO> oneBuyItemInfo(int b_code) {
		return mmdao.oneBuyItemInfo(b_code);
	}

	@Override
	public int getBuyCount(String m_id, String start_date, String end_date) {
		return mmdao.getBuyCount(m_id, start_date, end_date);
	}

	@Override
	public int getPointCount(String m_id) {
		return mmdao.getPointCount(m_id);
	}

	@Override
	public void loginRemember(String sessionId, Date sessionLimit, String m_id) {
		mmdao.loginRemember(sessionId, sessionLimit, m_id);
	}

	@Override
	public MemberDTO getSessionUser(String sessionId) {
		return mmdao.getSessionUser(sessionId);
	}

	@Override
	public BuyDTO buyCheck(BuyDTO bdto) {
		return mmdao.buyCheck(bdto);
	}

	@Override
	public BuyDTO noneOneBuyInfo(int b_code, String b_order_phone) {
		return mmdao.noneOneBuyInfo(b_code, b_order_phone);
	}

	@Override
	public ArrayList<BuyitemDTO> noneOneBuyItemInfo(int b_code) {
		return mmdao.noneOneBuyItemInfo(b_code);
	}

	@Override
	public int sumBuyPrice(int b_code) {
		return mmdao.sumBuyPrice(b_code);
	}

	@Override
	public ArrayList<BuyDTO> buyCancelList(String m_id) {
		return mmdao.buyCancelList(m_id);
	}

	@Override
	public Integer usePoint(int b_code) {
		return mmdao.usePoint(b_code);
	}

	@Override
	public void buyState(int b_code) {
		mmdao.buyState(b_code);		
	}

	@Override
	public void buyCancelPoint(String m_id, int b_code) {
		pdao.buyCancelPoint(m_id, b_code);		
	}
	
	@Override
	public int sumPoint(String m_id) {
		return mmdao.sumPoint(m_id);
	}
	
	@Override
	public void updatePoint(String m_id, int m_point) {
		mmdao.updatePoint(m_id, m_point);
		
	}

	@Override
	public int getBuyCancelCount(String m_id, String start_date, String end_date) {
		return mmdao.getBuyCancelCount(m_id, start_date, end_date);
	}

	@Override
	public List<BuyDTO> buyCancelContent(String m_id, String start_date, String end_date, int startRow, int endRow) {
		return mdao.buyCancelContent(m_id, start_date, end_date, startRow, endRow);
	}

	@Override
	public BuyDTO oneBuyCancelInfo(String m_id, int b_code) {
		return mmdao.oneBuyCancelInfo(m_id, b_code);
	}

	@Override
	public ArrayList<BuyitemDTO> oneBuyCancelItemInfo(int b_code) {
		return mmdao.oneBuyCancelItemInfo(b_code);
	}

	@Override
	public Integer preUsePoint(int b_code) {
		return mmdao.preUsePoint(b_code);
	}

	@Override
	public Date getCancelDate(int b_code) {
		return mmdao.getCancelDate(b_code);
	}

	@Override
	public int buyCancelListCount(String m_id) {
		return mmdao.buyCancelListCount(m_id);
	}
	
}
