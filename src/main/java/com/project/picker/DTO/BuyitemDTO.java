package com.project.picker.DTO;

public class BuyitemDTO {
	
	private int bi_num, bi_cnt, i_price, b_code;
	private String i_code, i_img, i_name;
	
	public BuyitemDTO() { }
	public BuyitemDTO(int bi_num, int bi_cnt, int i_price, int b_code, String i_img, String i_code, String i_name) {
		this.bi_num = bi_num; // 구매상품번호
		this.bi_cnt = bi_cnt; // 수량
		this.i_price = i_price; // 상품가격
		this.b_code = b_code; // 주문코드
		this.i_img = i_img; // 이미지
		this.i_code = i_code; // 상품코드
		this.i_name = i_name; // 상품명
	}
	public int getBi_num() {
		return bi_num;
	}
	public void setBi_num(int bi_num) {
		this.bi_num = bi_num;
	}
	public int getBi_cnt() {
		return bi_cnt;
	}
	public void setBi_cnt(int bi_cnt) {
		this.bi_cnt = bi_cnt;
	}
	public int getI_price() {
		return i_price;
	}
	public void setI_price(int i_price) {
		this.i_price = i_price;
	}
	public int getB_code() {
		return b_code;
	}
	public void setB_code(int b_code) {
		this.b_code = b_code;
	}
	public String getI_code() {
		return i_code;
	}
	public void setI_code(String i_code) {
		this.i_code = i_code;
	}
	public String getI_img() {
		return i_img;
	}
	public void setI_img(String i_img) {
		this.i_img = i_img;
	}
	public String getI_name() {
		return i_name;
	}
	public void setI_name(String i_name) {
		this.i_name = i_name;
	}
	
	

}
