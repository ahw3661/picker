package com.project.picker.DTO;

public class EvalDTO {
	
	private int e_num, e_level, e_chk;
	private String e_content, m_id, e_date, i_code;
	
	public EvalDTO() { }
	public EvalDTO(int e_num, int e_level, int e_chk, String e_content, String m_id, String e_date, String i_code) {
		this.e_num = e_num;
		this.e_level = e_level;
		this.e_chk = e_chk;
		this.e_content = e_content;
		this.m_id = m_id;
		this.e_date = e_date;
		this.i_code = i_code;
	}
	
	public int getE_num() {
		return e_num;
	}
	public void setE_num(int e_num) {
		this.e_num = e_num;
	}
	public int getE_level() {
		return e_level;
	}
	public void setE_level(int e_level) {
		this.e_level = e_level;
	}
	public int getE_chk() {
		return e_chk;
	}
	public void setE_chk(int e_chk) {
		this.e_chk = e_chk;
	}
	public String getE_content() {
		return e_content;
	}
	public void setE_content(String e_content) {
		this.e_content = e_content;
	}
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	public String getE_date() {
		return e_date;
	}
	public void setE_date(String e_date) {
		this.e_date = e_date;
	}
	public String getI_code() {
		return i_code;
	}
	public void setI_code(String i_code) {
		this.i_code = i_code;
	}

}
