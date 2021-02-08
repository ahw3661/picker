package com.project.picker.Service;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.project.mapper.AdminMapperDAO;
import com.project.picker.DAO.AdminDAO;
import com.project.picker.DAO.PointDAO;
import com.project.picker.DTO.BuyDTO;
import com.project.picker.DTO.BuyitemDTO;
import com.project.picker.DTO.ItemDTO;
import com.project.picker.DTO.ItemInsertDTO;
import com.project.picker.DTO.ItemQnaDTO;										 
import com.project.picker.DTO.MemberDTO;
import com.project.picker.DTO.PointDTO;

@Service
public class AdminServicelmpl implements AdminService {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminServicelmpl.class);

	@Inject
	AdminMapperDAO adao;
	
	@Inject
	AdminDAO amdao;
	
	@Inject
	PointDAO pdao;
	
	// 회원정보를 불러오는 함수
	@Override
	public List<MemberDTO> memberList(String s_type, String m_keyword, int m_type, int startRow, int endRow) {
		return amdao.memberList(s_type, m_keyword, m_type, startRow, endRow);
	}
	
	//한 명의 회원정보를 불러오는 함수
	@Override
	public MemberDTO oneList(String m_id) {
		return adao.oneList(m_id);
	}
	
	@Override
	public List<ItemDTO> itemList(String s_type, String m_keyword, String i_category, int i_chk, int startRow, int endRow) {
		return amdao.itemList(s_type, m_keyword, i_category, i_chk, startRow, endRow);
	}

	@Override
	public int  itemListCount(String s_type, String m_keyword, String i_category, int i_chk) {
		return amdao.itemListCount(s_type, m_keyword, i_category, i_chk);
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
	public int getAllBuyCount(String start_date, String end_date) {
		return adao.getAllBuyCount(start_date, end_date);
	}

	@Override
	public List<BuyDTO> allBuyList(String start_date, String end_date, int startRow, int endRow) {
		return amdao.allBuyList(start_date, end_date, startRow, endRow);
	}

	@Override
	public ArrayList<BuyitemDTO> allBuyItem() {
		return adao.allBuyItem();
	}

	@Override
	public BuyDTO getOneBuyInfo(int b_code) {
		return adao.getOneBuyInfo(b_code);
	}

	@Override
	public ArrayList<BuyitemDTO> getOneBuyItemInfo(int b_code) {
		return adao.getOneBuyItemInfo(b_code);
	}

	@Override
	public int getSumBuyPrice(int b_code) {
		return adao.getSumBuyPrice(b_code);
	}

	@Override
	public ArrayList<BuyitemDTO> buyItemList() {
		return adao.buyItemList();
	}

	@Override
	public int getAllBuyCancelCount(String start_date, String end_date) {
		return adao.getAllBuyCancelCount(start_date, end_date);
	}

	@Override
	public List<BuyDTO> allBuyCancel(String start_date, String end_date, int startRow, int endRow) {
		return amdao.allBuyCancel(start_date, end_date, startRow, endRow);
	}

	@Override
	public BuyDTO oneBuyCancel(int b_code) {
		return adao.oneBuyCancel(b_code);
	}

	@Override
	public void itemUpdate(ItemInsertDTO idto, HttpSession session) {
		String imgName = idto.getMainFile().getOriginalFilename();
		String detailImgName = idto.getDetailFile().getOriginalFilename();
		
		try {
			String mainPath = session.getServletContext().getRealPath("/resources/image/category_img/");
			String detailPath = session.getServletContext().getRealPath("/resources/image/detail_img/");
			
			File mainDir = new File(mainPath);
			File detailDir = new File(detailPath);
			
			if(imgName != null && !imgName.equals("")) {
				if(detailImgName != null && !detailImgName.equals("")) {
					logger.info("새로운 상품 및 상품상세의 이미지 등록");
					// update를 위한 이미지 값 세팅
					idto.setI_img(imgName);
					idto.setI_detailimg(detailImgName);
				}else {
					logger.info("새로운 상품 이미지 등록");
					idto.setI_img(imgName); // 새로운 상품 이미지 등록
					idto.setI_detailimg(idto.getI_detailimg()); // 기존 상품상세 이미지 재등록
				}
			}else {
				if(detailImgName != null && !detailImgName.equals("")) {
					logger.info("새로운 상품상세 이미지 등록");
					idto.setI_img(idto.getI_img()); // 기존 상품 이미지 재등록
					idto.setI_detailimg(detailImgName);// 새로운 상품상세 이미지 등록
				}else {
					logger.info("기존 상품 및 상품상세 이미지 유지");
					// 기존 상품 및 상품상세 이미지 유지
					idto.setI_img(idto.getI_img());
					idto.setI_detailimg(idto.getI_detailimg());
				}
			}
			
			if(!mainDir.exists()) {
				mainDir.mkdirs();
			}
			
			if(!detailDir.exists()) {
				detailDir.mkdirs();
			}
			
			if(imgName != null && !imgName.equals("")) {
				if(detailImgName == null || detailImgName.equals("")) {
					logger.info("새로운 상품 이미지 등록");
					idto.getMainFile().transferTo(new File(mainPath + imgName)); // 새로운 상품 이미지 등록
				}
			}
			
			if(detailImgName != null && !detailImgName.equals("")) {
				if(imgName == null || imgName.equals("")) {
					logger.info("새로운 상품상세 이미지 등록");
					idto.getDetailFile().transferTo(new File(detailPath + detailImgName)); // 새로운 상품상세 이미지 등록
				}
			}
			
			if((imgName != null && !imgName.equals("")) && (detailImgName != null && !detailImgName.equals(""))) { // 새로운 상품 및 상품상세 이미지 등록
				logger.info("새로운 상품 및 상품상세 이미지 등록");
				idto.getMainFile().transferTo(new File(mainPath + imgName));
				idto.getDetailFile().transferTo(new File(detailPath + detailImgName));
			}
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		amdao.itemUpdate(idto);
		
	}

	@Override
	public int getAllMemberCount(String s_type, String m_keyword, int m_type) {
		return amdao.getAllMemberCount(s_type, m_keyword, m_type);
	}

	@Override
	public int qnaCount(String column, String keyword, String code, int rchk) {
		return amdao.qnaCount(column, keyword, code, rchk);
	}
	
	@Override
	public List<ItemQnaDTO> qnaList(String column, String keyword, String code, int rchk, int startRow, int endRow) {
		List<ItemQnaDTO> list = amdao.qnaList(column, keyword, code, rchk, startRow, endRow);
		for(ItemQnaDTO dto : list) {
			dto.setQ_date(dto.getQ_date().substring(0, 16));
		}
		return list;
	}
	
	@Override
	public ArrayList<ItemDTO> getItemNameList() {
		return adao.getItemNameList();
	}
}
