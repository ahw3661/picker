package com.project.picker.Service;

import java.util.ArrayList;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.project.mapper.NoticeMapperDAO;
import com.project.picker.DTO.NoticeDTO;

@Service
public class NoticeServiceImpl implements NoticeService {
	
	@Inject
	NoticeMapperDAO dao;
	
	@Override
	public boolean writeInsert(NoticeDTO dto) {
		return dao.insertNotice(dto) == 1;
	}

	@Override
	public int getNoticeCount(String keyword) {
		return dao.getNoticeCount(keyword);
	}

	@Override
	public ArrayList<NoticeDTO> getNoticeList(String keyword, int startRow, int endRow) {
		return dao.getNoticeList(keyword, startRow, endRow);
	}

	@Override
	public void addCount(int num) {
		dao.updateReadCount(num);
	}

	@Override
	public NoticeDTO getNoticeByNum(int num) {
		NoticeDTO dto = dao.getNoticeByNum(num);
		dto.setN_date(dto.getN_date().substring(0, 16));
		return dto;
	}

	@Override
	public int getNext(int num) {
		return dao.checkNext(num);
	}

	@Override
	public int getPrev(int num) {
		return dao.checkPrev(num);
	}

	@Override
	public String getTitle(int num) {
		return dao.getTitle(num);
	}

	@Override
	public boolean modifyNotice(NoticeDTO dto) {
		return dao.updateNotice(dto) == 1;
	}

	@Override
	public boolean eraseNotice(int num) {
		return dao.deleteNotice(num) == 1;
	}

}
