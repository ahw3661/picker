package com.project.picker.Service;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import com.project.picker.DTO.CartDTO;

public interface CartService {

	public ArrayList<CartDTO> allCartList(String m_id);
	public int getNum();
	public void insertCart(CartDTO cdto, HttpSession session);
	public void delOne(String m_id, String i_code);
	public void delOne_Non(int c_num);
	public void delAll(String m_id);
	public void delAll_Non(int c_num);
	public int totalCartCount(String m_id);
	public void cartCntUpdate(CartDTO cdto);
	public void changeId(String m_id, int c_num);
	public void updateCnt(int c_cnt, String i_code, String m_id);
	public void plmaupdateCnt(CartDTO cdto);
	public void plmaupdateCnt_Non(CartDTO cdto);
	public void delOriginList(int c_num);
	public int cartCount(CartDTO cdto);
	public CartDTO getCartItem(String i_code);
	public CartDTO getCartNum(int c_num);
}
