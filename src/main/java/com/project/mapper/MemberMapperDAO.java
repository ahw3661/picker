package com.project.mapper;

import java.util.ArrayList;
import java.util.Date;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.project.picker.DTO.BuyDTO;
import com.project.picker.DTO.BuyitemDTO;
import com.project.picker.DTO.LoginDTO;
import com.project.picker.DTO.MemberDTO;

@Repository
public interface MemberMapperDAO {

	// 회원 목록
	@Select("SELECT * FROM picker_member")
	public ArrayList<MemberDTO> memberList();
	
	// 회원정보 - 내 정보
	@Select("SELECT * FROM picker_member WHERE m_id = #{m_id}")
	public MemberDTO viewMember(@Param("m_id") String m_id);
	
	// 로그인 체크
	@Select("SELECT m_id, m_password, m_name, m_type FROM picker_member WHERE m_id = #{m_id} AND m_password = #{m_password}")
	public MemberDTO loginCheck(LoginDTO ldto);
	
	// 로그인 상태 유지 - 로그인 성공 후 세션 id 와 유효시간 테이블 세팅
	@Select("UPDATE picker_member SET session_key = #{sessionId}, session_limit = #{sessionLimit} WHERE m_id = #{m_id}")
	public void loginRemember(@Param("sessionId") String sessionId, @Param("sessionLimit") Date sessionLimit, @Param("m_id") String m_id);
	
	// 로그인 상태 유지 - 이전 로그인 여부. 유효시간이 남아 있고 해당 sessionId를 가지는 사용자 정보
	@Select("SELECT * FROM picker_member WHERE session_key = #{sessionId} and session_limit > sysdate")
	public MemberDTO getSessionUser(@Param("sessionId") String sessionId);
	
	// 비밀번호 일치 여부 체크
	@Select("SELECT COUNT(*) FROM picker_member WHERE m_id = #{m_id} AND m_password = #{m_password}")
	public int pwCheck(MemberDTO mdto);
	
	// 회원탈퇴
	@Delete("DELETE FROM picker_member WHERE m_id = #{m_id}")
	public void deleteMember(@Param("m_id") String m_id);
	
	// 회원탈퇴 - qna id 삭제
	@Update("UPDATE picker_qna SET m_id = '' WHERE m_id = #{m_id}")
	public void updateQna(@Param("m_id") String m_id);
	
	// 회원탈퇴 - eval id 삭제
	@Update("UPDATE picker_eval SET m_id = '' WHERE m_id = #{m_id}")
	public void updateEval(@Param("m_id") String m_id);
	
	// 회원탈퇴 - reply id 삭제
	@Update("UPDATE picker_reply SET m_id = '' WHERE m_id = #{m_id}")
	public void updateReply(@Param("m_id") String m_id);
	
	// 회원탈퇴 - 포인트 삭제
	@Delete("DELETE FROM picker_point WHERE m_id = #{m_id}")
	public void deletePoint(@Param("m_id") String m_id);
	
	// 회원탈퇴 - 장바구니 삭제
	@Delete("DELETE FROM picker_cart WHERE m_id = #{m_id}")
	public void deleteCart(@Param("m_id") String m_id);
	
	// 포인트 총계
	@Select("SELECT SUM(p_point) FROM picker_point WHERE m_id = #{m_id}")
	public int onePoint(@Param("m_id") String m_id);
	
	// 아이디찾기 - 이메일
	@Select("SELECT m_id FROM picker_member WHERE m_email = #{m_email}")
	public String findEmailId(@Param("m_email") String m_email);
	
	// 아이디 찾기 - 이름, 연락처
	@Select("SELECT m_id FROM picker_member WHERE m_name = #{m_name} AND m_phone = #{m_phone}")
	public String findNamePhoneId(MemberDTO mdto);
	
	// 비밀번호 찾기 - 아이디
	@Select("SELECT m_password FROM picker_member WHERE m_id = #{m_id}")
	public String findIdPassword(@Param("m_id") String m_id);
	
	// 회원별 구매 목록 카운트
	@Select("SELECT COUNT(*) FROM picker_buy WHERE m_id = #{m_id}")
	public int getBuyCount(@Param("m_id") String m_id);
	
	// 회원별 포인트 카운트
	@Select("SELECT COUNT(*) FROM picker_point WHERE m_id = #{m_id}")
	public int getPointCount(@Param("m_id") String m_id);

	// 구매상품
	@Select("SELECT * FROM picker_buyitem ORDER BY bi_num DESC")
	public ArrayList<BuyitemDTO> buyItem();

	// 구매상세1
	@Select("SELECT * FROM picker_buy WHERE m_id = #{m_id} AND b_code = #{b_code}")
	public BuyDTO oneBuyInfo(@Param("m_id") String m_id, @Param("b_code") String b_code);

	// 구매상세2
	@Select("SELECT * FROM picker_buyitem WHERE b_code = #{b_code}")
	public ArrayList<BuyitemDTO> oneBuyItemInfo(@Param("b_code") String b_code);
	
	// 상품구매 금액 합계
	@Select("SELECT SUM(bi_cnt*i_price) FROM picker_buyitem WHERE b_code = #{b_code}")
	public int sumBuyPrice(@Param("b_code") String b_code);

	// 비회원 구매내역 체크
	@Select("SELECT * FROM picker_buy WHERE b_code = #{b_code} AND b_order_phone = #{b_order_phone}")
	public BuyDTO buyCheck(BuyDTO bdto);
	
	// 비회원 구매상세1
	@Select("SELECT * FROM picker_buy WHERE b_code = #{b_code} AND b_order_phone = #{b_order_phone}")
	public BuyDTO noneOneBuyInfo(@Param("b_code") String b_code, @Param("b_order_phone") String b_order_phone);

	// 비회원 구매상세2
	@Select("SELECT * FROM picker_buyitem WHERE b_code = #{b_code}")
	public ArrayList<BuyitemDTO> noneOneBuyItemInfo(@Param("b_code") String b_code);
}
