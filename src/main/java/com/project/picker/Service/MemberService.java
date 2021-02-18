package com.project.picker.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.project.picker.DTO.BuyDTO;
import com.project.picker.DTO.BuyitemDTO;
import com.project.picker.DTO.LoginDTO;
import com.project.picker.DTO.MemberDTO;
import com.project.picker.DTO.PointDTO;

public interface MemberService {
	
	public MemberDTO loginCheck(LoginDTO ldto); // 로그인
	public MemberDTO viewMember(String m_id); // 회원정보
	public void updateMemberType(String m_id); // 회원유형 변경
	public void updateMember(MemberDTO mdto); // 회원정보 수정
	public int pwCheck(MemberDTO mdto); // 비밀번호 체크
	public List<PointDTO> pointList(String m_id, int startRow, int endRow); // 회원 포인트 목록
	public int onePoint(String m_id); // 회원 총 포인트
	public String findId(MemberDTO mdto); // 아이디 찾기
	public String findPw(String m_id); // 비밀번호 찾기
	public List<BuyDTO> buyList(String m_id, String start_date, String end_date, int startRow, int endRow); // 회원 구매 목록
	public ArrayList<BuyitemDTO> buyItem(); // 회원 구매 목록 상품
	public BuyDTO oneBuyInfo(String m_id, int b_code); // 회원 구매 상세
	public ArrayList<BuyitemDTO> oneBuyItemInfo(int b_code); // 회원 구매 상세 상품
	public int getBuyCount(String m_id, String start_date, String end_date); // 회원 주문 건수
	public int getPointCount(String m_id); // 회원 포인트 건수
	public void loginRemember(String sessionId, Date sessionLimit, String m_id); // 세션 id와 유효시간
	public MemberDTO getSessionUser(String sessionId); // 이전 로그인 여부 확인
	public BuyDTO buyCheck(BuyDTO bdto); // 비회원 주문조회
	public BuyDTO noneOneBuyInfo(int b_code, String b_order_phone); // 비회원 구매 정보
	public ArrayList<BuyitemDTO> noneOneBuyItemInfo(int b_code); // 비회원 구매 상품
	public int sumBuyPrice(int b_code); // 구매 총 금액
	public int buyCancelListCount(String m_id); // 회원 구매 취소 가능 목록 건수
	public ArrayList<BuyDTO> buyCancelList(String m_id); // 회원 구매 취소 가능 목록
	public Integer usePoint(int b_code); // 사용한 포인트
	public void buyState(int b_code); // 구매 취소
	public void buyCancelPoint(String m_id, int b_code); // 회원 구매 취소 포인트 변경
	public int sumPoint(String m_id); // 구매 취소 후 총 포인트
	public void updatePoint(String m_id, int m_point); // 회원 총 포인트 변경
	public int getBuyCancelCount(String m_id, String start_date, String end_date); // 회원 구매 취소 완료 목록 건수
	public List<BuyDTO> buyCancelContent(String m_id, String start_date, String end_date, int startRow, int endRow); // 회원 구매 취소 완료 목록
	public BuyDTO oneBuyCancelInfo(String m_id, int b_code); // 회원 구매 취소 완료 상세
	public ArrayList<BuyitemDTO> oneBuyCancelItemInfo(int b_code); // 회원 구매 취소 완료 상세 상품
	public Integer preUsePoint(int b_code); // 구매 취소 전 사용 포인트
	public Date getCancelDate(int b_code); // 구매 취소일자
	
}
