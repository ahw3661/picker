package com.project.picker.Service;


import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.project.mapper.BuyMapperDAO;
import com.project.picker.DTO.CartDTO;

@Service
public class BuyServiceImpl implements BuyService {

	@Inject
	BuyMapperDAO bdao;

	@Override
	public CartDTO payList_cart(String m_id, String i_code) {
		return bdao.payList_cart(m_id, i_code);
	}

	
}
