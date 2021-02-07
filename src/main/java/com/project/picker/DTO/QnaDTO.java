package com.project.picker.DTO;

public class QnaDTO {
	
	private int q_num, q_rchk, q_chk;
	private String q_title, q_content, q_date, m_id, m_name, i_img, i_code, i_name;
	
	public QnaDTO() { }
	public QnaDTO(int q_num, int q_rchk, int q_chk, String q_title, String q_content, String q_date, String m_id,
			String i_img, String i_code, String i_name) {
		this.q_num = q_num;
		this.q_rchk = q_rchk;
		this.q_chk = q_chk;
		this.q_title = q_title;
		this.q_content = q_content;
		this.q_date = q_date;
		this.m_id = m_id;
		this.i_img = i_img;
		this.i_code = i_code;
		this.i_name = i_name;
	}

	public int getQ_num() {
		return q_num;
	}
	public void setQ_num(int q_num) {
		this.q_num = q_num;
	}
	public int getQ_rchk() {
		return q_rchk;
	}
	public void setQ_rchk(int q_rchk) {
		this.q_rchk = q_rchk;
	}
	public int getQ_chk() {
		return q_chk;
	}
	public void setQ_chk(int q_chk) {
		this.q_chk = q_chk;
	}
	public String getQ_title() {
		return q_title;
	}
	public void setQ_title(String q_title) {
		this.q_title = q_title;
	}
	public String getQ_content() {
		return q_content;
	}
	public void setQ_content(String q_content) {
		this.q_content = q_content;
	}
	public String getQ_date() {
		return q_date;
	}
	public void setQ_date(String q_date) {
		this.q_date = q_date;
	}
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	public String getM_name() {
		return m_name;
	}
	public void setM_name(String m_name) {
		this.m_name = m_name;
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

}
