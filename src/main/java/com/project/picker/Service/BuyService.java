package com.project.picker.Service;

import com.project.picker.DTO.CartDTO;

public interface BuyService {
	public CartDTO payList_cart(String m_id, String i_code);
}
