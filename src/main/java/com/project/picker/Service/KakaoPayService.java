package com.project.picker.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.project.picker.DTO.BuyDTO;
import com.project.picker.DTO.KakaoPayApprovalDTO;
import com.project.picker.DTO.PointDTO;

public interface KakaoPayService {

	public String kakaoPayReady(HttpServletRequest request,HttpSession session, BuyDTO bdto);
	public KakaoPayApprovalDTO kakaoPayInfo(HttpServletRequest request, HttpSession session, BuyDTO bdto, String pg_token);
	public void insertBuyitems(BuyDTO bdto, HttpServletRequest request, HttpSession session, Model model);
	public void insertPlusPoint(PointDTO pdto);
}
