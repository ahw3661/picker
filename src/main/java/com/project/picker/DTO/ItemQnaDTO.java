package com.project.picker.DTO;

public class ItemQnaDTO extends QnaDTO {
	
	private int re_cnt;
	
	public ItemQnaDTO() { super(); }

	public ItemQnaDTO(int re_cnt) {
		super();
		this.re_cnt = re_cnt;
	}

	public int getRe_cnt() {
		return re_cnt;
	}
	public void setRe_cnt(int re_cnt) {
		this.re_cnt = re_cnt;
	}
	
}
