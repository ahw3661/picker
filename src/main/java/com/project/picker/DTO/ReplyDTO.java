package com.project.picker.DTO;

public class ReplyDTO {
	
	private int r_num, q_num, r_dep, r_seq, r_chk;
	private String r_content, m_id, m_name, r_date;
	
	public ReplyDTO() { }
	public ReplyDTO(int r_num, int q_num, int r_dep, int r_seq, int r_chk, String r_content, String m_id, String m_name,
			String r_date) {
		this.r_num = r_num;
		this.q_num = q_num;
		this.r_dep = r_dep;
		this.r_seq = r_seq;
		this.r_chk = r_chk;
		this.r_content = r_content;
		this.m_id = m_id;
		this.m_name = m_name;
		this.r_date = r_date;
	}

	public int getR_num() {
		return r_num;
	}
	public void setR_num(int r_num) {
		this.r_num = r_num;
	}
	public int getQ_num() {
		return q_num;
	}
	public void setQ_num(int q_num) {
		this.q_num = q_num;
	}
	public int getR_dep() {
		return r_dep;
	}
	public void setR_dep(int r_dep) {
		this.r_dep = r_dep;
	}
	public int getR_seq() {
		return r_seq;
	}
	public void setR_seq(int r_seq) {
		this.r_seq = r_seq;
	}
	public int getR_chk() {
		return r_chk;
	}
	public void setR_chk(int r_chk) {
		this.r_chk = r_chk;
	}
	public String getR_content() {
		return r_content;
	}
	public void setR_content(String r_content) {
		this.r_content = r_content;
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
	public String getR_date() {
		return r_date;
	}
	public void setR_date(String r_date) {
		this.r_date = r_date;
	}
	
}
