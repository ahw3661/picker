package com.project.picker.Service;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.project.mapper.AdminMapperDAO;
import com.project.picker.DAO.AdminDAO;
import com.project.picker.DAO.PointDAO;
import com.project.picker.DTO.BuyDTO;
import com.project.picker.DTO.BuyitemDTO;
import com.project.picker.DTO.ItemDTO;
import com.project.picker.DTO.ItemInsertDTO;
import com.project.picker.DTO.MemberDTO;
import com.project.picker.DTO.PointDTO;

@Service
public class AdminServicelmpl implements AdminService {

	@Inject
	AdminMapperDAO adao;
	
	@Inject
	AdminDAO amdao;
	
	@Inject
	PointDAO pdao;
	
	// 회원정보를 불러오는 함수
	@Override
	public List<MemberDTO> memberList(int startRow, int endRow) {
		return amdao.memberList(startRow, endRow);
	}
	
	//한 명의 회원정보를 불러오는 함수
	@Override
	public MemberDTO oneList(String m_id) {
		return adao.oneList(m_id);
	}
	
	@Override
	public ArrayList<ItemDTO> itemList(int startRow, int endRow) {
		return adao.itemList(startRow, endRow);
	}

	@Override
	public int  itemListCount() {
		return adao.itemListCount();
	}

	@Override
	public ItemDTO oneItemList(String i_code) {
		return adao.oneItemList(i_code);
	}

	@Override
	public int memberCount() {
		return adao.memberCount();
	}

	@Override
	public int itemCount() {
		return adao.itemCount();
	}

	@Override
	public List<PointDTO> allPointList(int startRow, int endRow) {
		return pdao.allPointList(startRow, endRow);
	}

	@Override
	public List<PointDTO> onePointDetail(String m_id, int startRow, int endRow) {
		return pdao.onePointDetail(m_id, startRow, endRow);
	}

	@Override
	public int getAllPointCount() {
		return adao.getAllPointCount();
	}

	@Override
	public int getOnePointCount(String m_id) {
		return adao.getOnePointCount(m_id);
	}
	
	@Override
	public int getAllMemberCount() {
		return adao.getAllMemberCount();
	}
	@Override
	public String getCode() {
		return adao.getCode();
	}
	
	// 상품 인서트 함수
	@Override
	public void ItemInsert(ItemInsertDTO idto, HttpSession session) {
		String maxcode = adao.getCode();
		int icode = Integer.parseInt(maxcode.substring(1)) + 1;
		DecimalFormat df = new DecimalFormat("00000");
		maxcode = "P" + df.format(icode);
		idto.setI_code(maxcode);
		
		String imgname = idto.getMainFile().getOriginalFilename();
		String detailimgname = idto.getDetailFile().getOriginalFilename();
		
		idto.setI_img(imgname);
		idto.setI_detailimg(detailimgname);
		
		try {
			String mainpath = session.getServletContext().getRealPath("/resources/image/category_img/");
			String detailpath = session.getServletContext().getRealPath("/resources/image/detail_img/");
			File maindir = new File(mainpath);
			File detaildir = new File(detailpath);
			if(!maindir.exists()) {
				maindir.mkdirs();
			}
			if(!detaildir.exists()) {
				detaildir.mkdirs();
			}
			idto.getMainFile().transferTo(new File(mainpath + imgname));
			idto.getDetailFile().transferTo(new File(detailpath + detailimgname));
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		adao.ItemInsert(idto);
	}

	@Override
	public int getAllBuyCount() {
		return adao.getAllBuyCount();
	}

	@Override
	public List<BuyDTO> allBuyList(int startRow, int endRow) {
		return amdao.allBuyList(startRow, endRow);
	}

	@Override
	public ArrayList<BuyitemDTO> allBuyItem() {
		return adao.allBuyItem();
	}

	@Override
	public BuyDTO getOneBuyInfo(String b_code) {
		return adao.getOneBuyInfo(b_code);
	}

	@Override
	public ArrayList<BuyitemDTO> getOneBuyItemInfo(String b_code) {
		return adao.getOneBuyItemInfo(b_code);
	}

	@Override
	public int getSumBuyPrice(String b_code) {
		return adao.getSumBuyPrice(b_code);
	}
	
}
