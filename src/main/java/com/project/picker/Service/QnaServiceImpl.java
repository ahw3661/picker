package com.project.picker.Service;

import java.util.ArrayList;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.project.mapper.QnaMapperDAO;
import com.project.picker.DTO.ItemDTO;
import com.project.picker.DTO.ItemQnaDTO;
import com.project.picker.DTO.QnaDTO;
import com.project.picker.DTO.ReplyDTO;

@Service
public class QnaServiceImpl implements QnaService {
	
	private static final Logger logger = LoggerFactory.getLogger(QnaServiceImpl.class);
	
	@Inject
	QnaMapperDAO dao;

	@Override
	public int getCountByMember(String id) {
		return dao.getQnaCountByMember(id);
	}
	
	@Override
	public int getCountByItem(String code) {
		return dao.getQnaCountByItem(code);
	}

	@Override
	public ArrayList<QnaDTO> getQnaListByMember(String id, int endrow) {
		ArrayList<QnaDTO> list = dao.getQnaListByMember(id, endrow);
		for(QnaDTO dto : list) {
			dto.setQ_date(dto.getQ_date().substring(0, 16));
		}
		return list;
	}

	@Override
	public ArrayList<ItemQnaDTO> getQnaListByItem(String code, int startRow, int endRow) {
		ArrayList<ItemQnaDTO> list = dao.getQnaListByItem(code, startRow, endRow);
		for(ItemQnaDTO dto : list) {
			dto.setQ_date(dto.getQ_date().substring(0, 16));
		}
		return list;
	}

	@Override
	public ArrayList<ReplyDTO> getReplyByMember(ArrayList<QnaDTO> list) {
		ArrayList<ReplyDTO> replylist = new ArrayList<>();
		for(QnaDTO dto : list) {
			replylist.addAll(dao.getReplyByQna(dto.getQ_num()));
		}
		for(ReplyDTO dto : replylist) {
			dto.setR_date(dto.getR_date().substring(0, 16));
		}
		return replylist;
	}
	
	@Override
	public ArrayList<ReplyDTO> getReplyByQna(int num) {
		ArrayList<ReplyDTO> list = dao.getReplyByQna(num);
		for(ReplyDTO dto : list) {
			dto.setR_date(dto.getR_date().substring(0, 16));
		}
		return list;
	}

	@Override
	public ItemDTO getItemInfo(String code) {
		return dao.getItemInfo(code);
	}
	
	@Override
	public QnaDTO getQnaByNum(int num) {
		return dao.getQnaByNum(num);
	}
	
	@Override
	public ItemQnaDTO getQnaWithReplyCount(int num) {
		ItemQnaDTO dto = dao.getQnaWithReplyCount(num);
		dto.setQ_date(dto.getQ_date().substring(0, 16));
		return dto;
	}

	@Override
	public boolean writeQna(QnaDTO dto) {
		return dao.insertQna(dto) == 1;
	}

	@Override
	public boolean modifyQna(QnaDTO dto) {
		return dao.updateQna(dto) == 1;
	}

	@Override
	public boolean deleteQna(int num) {
		return dao.deleteQna(num) == 1;
	}

	@Override
	public boolean writeReply(ReplyDTO dto, int ref) {
		if(ref == 0) { // 1¥‹∞Ë ¥Ò±€
			dto.setR_dep(1);
			dto.setR_seq(dao.setSequenceWithoutRef(dto.getQ_num()));
		} else { // 2¥‹∞Ë ¿ÃªÛ¿« ¥Ò±€(¥Î¥Ò±€)
			ReplyDTO refDto = dao.getRefPosition(ref);
			int dep = refDto.getR_dep();
			dto.setR_dep(dep + 1);
			int seq = dao.setSequenceWithRef(dto.getQ_num(), dep, refDto.getR_seq());
			if(seq == 0) seq = dao.setSequenceWithoutRef(dto.getQ_num());
			else logger.info("SeqUpdate : " + dao.updateSequence(dto.getQ_num(), seq));
			dto.setR_seq(seq);
		}
		return dao.insertReply(dto) == 1;
	}
	
	@Override
	public void setAdminReplied(int num) {
		dao.setAdminReplied(num);
	}
	
	@Override
	public String getReplyContent(int num) {
		return dao.getReplyContent(num);
	}

	@Override
	public boolean modifyReply(ReplyDTO dto) {
		return dao.updateReply(dto) == 1;
	}

	@Override
	public boolean deleteReply(int num) {
		return dao.deleteReply(num) == 1;
	}

}
