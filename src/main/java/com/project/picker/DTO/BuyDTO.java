package com.project.picker.DTO;

public class BuyDTO {
	
	private String b_order_name, b_order_phone, b_order_email, b_take_name, b_take_email, b_take_phone, 
		b_take_zipcode, b_take_roadaddr, b_take_detailaddr, m_id, b_date, b_agree;
	private int b_price, b_code;
	private BuyitemDTO bidto;
	
	public BuyDTO() { }
	public BuyDTO(int b_code, String b_order_name, String b_order_phone, String b_order_email, String b_take_name,
			String b_take_email, String b_take_phone, String b_take_zipcode, String b_take_roadaddr,
			String b_take_detailaddr, String m_id, String b_date, String b_agree, int b_price, BuyitemDTO bidto) {
		this.b_code = b_code;//주문코드(PK)
		this.b_order_name = b_order_name;//주문자이름
		this.b_order_phone = b_order_phone;//주문자연락처
		this.b_order_email = b_order_email;//주문자이메일
		this.b_take_name = b_take_name;//수령인이름
		this.b_take_email = b_take_email;// 수령인 이메일
		this.b_take_phone = b_take_phone;//수령인연락처
		this.b_take_zipcode = b_take_zipcode; //수령인우편번호
		this.b_take_roadaddr = b_take_roadaddr; //수령인주소
		this.b_take_detailaddr = b_take_detailaddr; //수령인상세주소
		this.m_id = m_id; //아이디
		this.b_date = b_date; //구매일자
		this.b_agree = b_agree; //구매동의여부
		this.b_price = b_price; //배송비
		this.bidto = bidto; // BuyitemDTO
	}
	public int getB_code() {
		return b_code;
	}
	public void setB_code(int b_code) {
		this.b_code = b_code;
	}
	public String getB_order_name() {
		return b_order_name;
	}
	public void setB_order_name(String b_order_name) {
		this.b_order_name = b_order_name;
	}
	public String getB_order_phone() {
		return b_order_phone;
	}
	public void setB_order_phone(String b_order_phone) {
		this.b_order_phone = b_order_phone;
	}
	public String getB_order_email() {
		return b_order_email;
	}
	public void setB_order_email(String b_order_email) {
		this.b_order_email = b_order_email;
	}
	public String getB_take_name() {
		return b_take_name;
	}
	public void setB_take_name(String b_take_name) {
		this.b_take_name = b_take_name;
	}
	public String getB_take_email() {
		return b_take_email;
	}
	public void setB_take_email(String b_take_email) {
		this.b_take_email = b_take_email;
	}
	public String getB_take_phone() {
		return b_take_phone;
	}
	public void setB_take_phone(String b_take_phone) {
		this.b_take_phone = b_take_phone;
	}
	public String getB_take_zipcode() {
		return b_take_zipcode;
	}
	public void setB_take_zipcode(String b_take_zipcode) {
		this.b_take_zipcode = b_take_zipcode;
	}
	public String getB_take_roadaddr() {
		return b_take_roadaddr;
	}
	public void setB_take_roadaddr(String b_take_roadaddr) {
		this.b_take_roadaddr = b_take_roadaddr;
	}
	public String getB_take_detailaddr() {
		return b_take_detailaddr;
	}
	public void setB_take_detailaddr(String b_take_detailaddr) {
		this.b_take_detailaddr = b_take_detailaddr;
	}
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	public String getB_date() {
		return b_date;
	}
	public void setB_date(String b_date) {
		this.b_date = b_date;
	}
	public String getB_agree() {
		return b_agree;
	}
	public void setB_agree(String b_agree) {
		this.b_agree = b_agree;
	}
	public int getB_price() {
		return b_price;
	}
	public void setB_price(int b_price) {
		this.b_price = b_price;
	}
	public BuyitemDTO getBidto() {
		return bidto;
	}
	public void setBidto(BuyitemDTO bidto) {
		this.bidto = bidto;
	}
	
		
}
