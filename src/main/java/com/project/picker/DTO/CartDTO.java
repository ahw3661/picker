package com.project.picker.DTO;

public class CartDTO {
	
	private int c_num, c_cnt, i_price;
	private String i_img, i_code, i_name, m_id;
	
	public CartDTO() { }
	public CartDTO(int c_num, int c_cnt, int i_price, String i_img, String i_code, String i_name, String m_id) {
		this.c_num = c_num;
		this.c_cnt = c_cnt;
		this.i_price = i_price;
		this.i_img = i_img;
		this.i_code = i_code;
		this.i_name = i_name;
		this.m_id = m_id;
	}
	
	public int getC_num() {
		return c_num;
	}
	public void setC_num(int c_num) {
		this.c_num = c_num;
	}
	public int getC_cnt() {
		return c_cnt;
	}
	public void setC_cnt(int c_cnt) {
		this.c_cnt = c_cnt;
	}
	public int getI_price() {
		return i_price;
	}
	public void setI_price(int i_price) {
		this.i_price = i_price;
	}
	public String getI_img() {
		return i_img;
	}
	public void setI_img(String i_img) {
		this.i_img = i_img;
	}
	public String getI_code() {
		return i_code;
	}
	public void setI_code(String i_code) {
		this.i_code = i_code;
	}
	public String getI_name() {
		return i_name;
	}
	public void setI_name(String i_name) {
		this.i_name = i_name;
	}
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}

}
