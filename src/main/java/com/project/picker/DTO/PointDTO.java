package com.project.picker.DTO;

public class PointDTO {
	
	private int p_num, p_point, b_code;
	private String m_id, p_date, p_history;
	
	public PointDTO() { }
	public PointDTO(int p_num, int p_point, String m_id, String p_date, String p_history, int b_code) {
		this.p_num = p_num;
		this.p_point = p_point;
		this.m_id = m_id;
		this.p_date = p_date;
		this.p_history = p_history;
		this.b_code = b_code;
	}
	
	public int getP_num() {
		return p_num;
	}
	public void setP_num(int p_num) {
		this.p_num = p_num;
	}
	public int getP_point() {
		return p_point;
	}
	public void setP_point(int p_point) {
		this.p_point = p_point;
	}
	public String getM_id() {
		return m_id;
	}
	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	public String getP_date() {
		return p_date;
	}
	public void setP_date(String p_date) {
		this.p_date = p_date;
	}
	public String getP_history() {
		return p_history;
	}
	public void setP_history(String p_history) {
		this.p_history = p_history;
	}
	public int getB_code() {
		return b_code;
	}
	public void setB_code(int b_code) {
		this.b_code = b_code;
	}
}
