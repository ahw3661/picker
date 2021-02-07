package com.project.picker.DTO;

public class MemberDTO {

	private String m_id; // 아이디
	private String m_password; // 비밀번호
	private String m_repassword; // 비밀번호 확인
	private String m_newpassword; // 변경할 비밀번호
	private String m_email; // 이메일
	private String m_name; // 이름
	private String m_phone; // 휴대전화
	private String m_zipcode; // 우편번호
	private String m_roadaddr; // 도로명주소
	private String m_detailaddr; // 상세주소
	private String m_date; // 가입일자
	private int m_type; // 회원유형 0 관리자, 1 회원, 2 탈퇴회원
	private int m_point; // 포인트
	private String m_terms; // 이용약관동의
	private String m_personal; // 개인정보수집및이용동의
	
	public MemberDTO() {}
	public MemberDTO(String m_id, String m_password, String m_newpassword, String m_email, String m_name,
			String m_phone, String m_zipcode, String m_roadaddr, String m_detailaddr, String m_date, int m_type,
			int m_point, String m_terms, String m_personal) {
		this.m_id = m_id;
		this.m_password = m_password;
		this.m_newpassword = m_newpassword;
		this.m_email = m_email;
		this.m_name = m_name;
		this.m_phone = m_phone;
		this.m_zipcode = m_zipcode;
		this.m_roadaddr = m_roadaddr;
		this.m_detailaddr = m_detailaddr;
		this.m_date = m_date;
		this.m_type = m_type;
		this.m_point = m_point;
		this.m_terms = m_terms;
		this.m_personal = m_personal;
	}
	
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	public String getM_password() {
		return m_password;
	}
	public void setM_password(String m_password) {
		this.m_password = m_password;
	}
	public String getM_repassword() {
		return m_repassword;
	}
	public void setM_repassword(String m_repassword) {
		this.m_repassword = m_repassword;
	}
	public String getM_newpassword() {
		return m_newpassword;
	}
	public void setM_newpassword(String m_newpassword) {
		this.m_newpassword = m_newpassword;
	}
	public String getM_email() {
		return m_email;
	}
	public void setM_email(String m_email) {
		this.m_email = m_email;
	}
	public String getM_name() {
		return m_name;
	}
	public void setM_name(String m_name) {
		this.m_name = m_name;
	}
	public String getM_phone() {
		return m_phone;
	}
	public void setM_phone(String m_phone) {
		this.m_phone = m_phone;
	}
	public String getM_zipcode() {
		return m_zipcode;
	}
	public void setM_zipcode(String m_zipcode) {
		this.m_zipcode = m_zipcode;
	}
	public String getM_roadaddr() {
		return m_roadaddr;
	}
	public void setM_roadaddr(String m_roadaddr) {
		this.m_roadaddr = m_roadaddr;
	}
	public String getM_detailaddr() {
		return m_detailaddr;
	}
	public void setM_detailaddr(String m_detailaddr) {
		this.m_detailaddr = m_detailaddr;
	}
	public String getM_date() {
		return m_date;
	}
	public void setM_date(String m_date) {
		this.m_date = m_date;
	}
	public int getM_type() {
		return m_type;
	}
	public void setM_type(int m_type) {
		this.m_type = m_type;
	}
	public int getM_point() {
		return m_point;
	}
	public void setM_point(int m_point) {
		this.m_point = m_point;
	}
	public String getM_terms() {
		return m_terms;
	}
	public void setM_terms(String m_terms) {
		this.m_terms = m_terms;
	}
	public String getM_personal() {
		return m_personal;
	}
	public void setM_personal(String m_personal) {
		this.m_personal = m_personal;
	}
	
}
