package com.project.picker.Service;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.project.mapper.CartMapperDAO;
import com.project.picker.DTO.CartDTO;

@Service
@SuppressWarnings("unchecked")
public class CartServiceImpl implements CartService {

	@Inject
	CartMapperDAO cdao;
	
	@Override
	public ArrayList<CartDTO> allCartList(String m_id) {
		return cdao.allCartList(m_id);
	}

	@Override
	public void insertCart(CartDTO cdto, HttpSession session) {
		cdto.setC_num(getNum());
		// 장바구니에 리스트에 상품 추가
		String m_id = "";
		int count = 0;
		System.out.println("장바구니 추가");
		if(session.getAttribute("u_id") != null) {		
			m_id =  (String)session.getAttribute("u_id");
			if(cdao.cartCount(cdto) == 0) {
				cdao.insertCart(cdto);
			}else {
				cdao.cartCntUpdate(cdto);
			}
			count = totalCartCount(m_id);
		}else {
			m_id = "";
			ArrayList<CartDTO> list;
			boolean update_Non = true;
			if(session.getAttribute("sessionList")==null) {
				list = new ArrayList<>();
			}else {
				list = (ArrayList<CartDTO>)session.getAttribute("sessionList");
				for(int i=0;i<list.size();i++) {
					if(cdto.getI_code().equals(list.get(i).getI_code())) {
						cdao.cartCntUpdate_Non(cdto.getC_cnt(), list.get(i).getC_num());
						list.get(i).setC_cnt(list.get(i).getC_cnt() + cdto.getC_cnt());
						update_Non = false;
						break;
					}
				}
			}
			if(update_Non) {
				cdao.insertCart(cdto);
				list.add(cdto);
			}
			System.out.println("list: " + list.size());
			for(CartDTO dto : list) {
				System.out.println("num : " + dto.getC_num());
			}
			session.setAttribute("sessionList", list);
			count = list.size();
		}
		session.setAttribute("cnt", count);
	}

	@Override
	public void delOne(String m_id, String i_code) {
		cdao.delOne(m_id, i_code);
	}
	
	@Override
	public void delOne_Non(int c_num) {
		cdao.delOne_Non(c_num);
	}

	@Override
	public void delAll(String m_id) {
		cdao.delAll(m_id);
	}

	@Override
	public int totalCartCount(String m_id) {
		return cdao.totalCartCount(m_id);
	}

	@Override
	public void cartCntUpdate(CartDTO cdto) {
		cdao.cartCntUpdate(cdto);
	}

	@Override
	public void changeId(String m_id, int c_num) {
		cdao.changeId(m_id, c_num);
	}

	@Override
	public void updateCnt(int c_cnt, String i_code, String m_id) {
		cdao.updateCnt(c_cnt,i_code,m_id);
	}

	@Override
	public void plmaupdateCnt(CartDTO cdto) {
		cdao.plmaupdateCnt(cdto);
	}

	@Override
	public void delOriginList(int c_num) {
		cdao.delOriginList(c_num);
	}

	@Override
	public int getNum() {
		return cdao.getNum();
	}

	@Override
	public int cartCount(CartDTO cdto) {
		return cdao.cartCount(cdto);
	}

	@Override
	public void delAll_Non(int c_num) {
		cdao.delAll_Non(c_num);
	}

	@Override
	public void plmaupdateCnt_Non(CartDTO cdto) {
		cdao.plmaupdateCnt_Non(cdto);
	}

	@Override
	public CartDTO getCartItem(String i_code) {
		return cdao.getCartItem(i_code);
	}

	@Override
	public CartDTO getCartNum(int c_num) {
		return cdao.getCartNum(c_num);
	}

	


}
