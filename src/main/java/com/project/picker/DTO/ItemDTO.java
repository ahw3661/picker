package com.project.picker.DTO;

public class ItemDTO {
	
	private String i_code, i_name, i_date, i_img, i_detailimg, i_category;
	private int i_price, i_chk;
	
	public ItemDTO() { }
	public ItemDTO(String i_code, String i_name, String i_date, String i_img, String i_detailimg, String i_category,
			int i_price, int i_chk) {
		this.i_code = i_code;
		this.i_name = i_name;
		this.i_date = i_date;
		this.i_img = i_img;
		this.i_detailimg = i_detailimg;
		this.i_category = i_category;
		this.i_price = i_price;
		this.i_chk = i_chk;
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
	public String getI_date() {
		return i_date;
	}
	public void setI_date(String i_date) {
		this.i_date = i_date;
	}
	public String getI_img() {
		return i_img;
	}
	public void setI_img(String i_img) {
		this.i_img = i_img;
	}
	public String getI_detailimg() {
		return i_detailimg;
	}
	public void setI_detailimg(String i_detailimg) {
		this.i_detailimg = i_detailimg;
	}
	public String getI_category() {
		return i_category;
	}
	public void setI_category(String i_category) {
		this.i_category = i_category;
	}
	public int getI_price() {
		return i_price;
	}
	public void setI_price(int i_price) {
		this.i_price = i_price;
	}
	public int getI_chk() {
		return i_chk;
	}
	public void setI_chk(int i_chk) {
		this.i_chk = i_chk;
	}
	
}
