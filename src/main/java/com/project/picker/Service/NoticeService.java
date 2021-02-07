package com.project.picker.Service;

import java.util.ArrayList;

import com.project.picker.DTO.NoticeDTO;

public interface NoticeService {
	
	public boolean writeInsert(NoticeDTO dto); // 공지글 작성
	public int getNoticeCount(String keyword); // 공지 개수 호출
	ArrayList<NoticeDTO> getNoticeList(String keyword, int startRow, int endRow); // 공지 리스트 호출
	public void addCount(int num); // 공지글 조회수 증가
	public NoticeDTO getNoticeByNum(int num); // 공지글 조회(공지글 정보 호출)
	public int getNext(int num); // 다음 글 호출
	public int getPrev(int num); // 이전 글 호출
	public String getTitle(int num); // 제목 호출
	public boolean modifyNotice(NoticeDTO dto); // 공지글 수정
	public boolean eraseNotice(int num); // 공지글 삭제
	
}
