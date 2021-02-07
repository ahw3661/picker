package com.project.picker.Service;

import java.util.ArrayList;

import com.project.picker.DTO.ItemDTO;
import com.project.picker.DTO.ItemQnaDTO;
import com.project.picker.DTO.QnaDTO;
import com.project.picker.DTO.ReplyDTO;

public interface QnaService {
	
	public int getCountByMember(String id); // 유저별 게시글 개수 호출
	public int getCountByItem(String code); // 상품별 게시글 개수 호출
	public ArrayList<QnaDTO> getQnaListByMember(String id, int endrow); // 유저별 게시글 목록 호출
	public ArrayList<ItemQnaDTO> getQnaListByItem(String code, int startRow, int endRow); // 상품별 게시글 목록 호출
	public ArrayList<ReplyDTO> getReplyByMember(ArrayList<QnaDTO> list); // 유저별 댓글 목록 호출
	public ArrayList<ReplyDTO> getReplyByQna(int num); // 게시글별 댓글 목록 호출
	public ItemDTO getItemInfo(String code); // 글쓰기 화면 상품정보 호출
	public QnaDTO getQnaByNum(int num); // 게시글 호출
	public ItemQnaDTO getQnaWithReplyCount(int num); // 게시글 및 댓글 개수 호출
	public boolean writeQna(QnaDTO dto); // 게시글 등록
	public boolean modifyQna(QnaDTO dto); // 게시글 수정
	public boolean deleteQna(int num); // 게시글 삭제
	public boolean writeReply(ReplyDTO dto, int ref); // 댓글 등록
	public void setAdminReplied(int num); // 관리자 댓글 등록
	public String getReplyContent(int num); // 댓글 내용 호출
	public boolean modifyReply(ReplyDTO dto); // 댓글 수정
	public boolean deleteReply(int num); // 댓글 삭제

}
