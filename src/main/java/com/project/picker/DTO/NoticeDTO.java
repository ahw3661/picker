package com.project.picker.DTO;

public class NoticeDTO {
	
	private int n_num, n_cnt, n_chk;
	private String n_title, n_content, n_date, m_id;
	
	public NoticeDTO() { }
	public NoticeDTO(int n_num, int n_cnt, int n_chk, String n_title, String n_content, String n_date, String m_id) {
		this.n_num = n_num;
		this.n_cnt = n_cnt;
		this.n_chk = n_chk;
		this.n_title = n_title;
		this.n_content = n_content;
		this.n_date = n_date;
		this.m_id = m_id;
	}
	
	public int getN_num() {
		return n_num;
	}
	public void setN_num(int n_num) {
		this.n_num = n_num;
	}
	public int getN_cnt() {
		return n_cnt;
	}
	public void setN_cnt(int n_cnt) {
		this.n_cnt = n_cnt;
	}
	public int getN_chk() {
		return n_chk;
	}
	public void setN_chk(int n_chk) {
		this.n_chk = n_chk;
	}
	public String getN_title() {
		return n_title;
	}
	public void setN_title(String n_title) {
		this.n_title = n_title;
	}
	public String getN_content() {
		return n_content;
	}
	public void setN_content(String n_content) {
		this.n_content = n_content;
	}
	public String getN_date() {
		return n_date;
	}
	public void setN_date(String n_date) {
		this.n_date = n_date;
	}
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}

}
