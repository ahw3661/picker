package com.project.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.project.picker.DTO.ItemDTO;
import com.project.picker.DTO.ItemQnaDTO;
import com.project.picker.DTO.QnaDTO;
import com.project.picker.DTO.ReplyDTO;

@Repository
public interface QnaMapperDAO {
	
	// 유저별 글 개수
	@Select("SELECT COUNT(*) FROM picker_qna WHERE m_id = #{id} AND q_chk = 0")
	public int getQnaCountByMember(@Param("id") String id);
	
	// 상품별 글 개수
	@Select("SELECT COUNT(*) FROM picker_qna WHERE i_code = #{code} AND q_chk = 0")
	public int getQnaCountByItem(@Param("code") String code);
	
	// 유저별 글 목록
	@Select("SELECT Q.* FROM (SELECT * FROM picker_qna WHERE m_id = #{id} AND q_chk = 0 ORDER BY q_num DESC) Q WHERE ROWNUM <= #{endrow}")
	public ArrayList<QnaDTO> getQnaListByMember(@Param("id") String id, @Param("endrow") int endrow);
	
	// 상품별 글 목록
	@Select("SELECT q_num, q_title, q_date, m_id, m_name, q_rchk, re_cnt FROM (SELECT qna.*, ROWNUM AS rnum FROM "
			+ "(SELECT q.*, NVL(r.re_cnt, 0) AS re_cnt FROM picker_qna q LEFT OUTER JOIN "
			+ "(SELECT q_num, COUNT(*) AS re_cnt FROM picker_reply WHERE r_chk = 0 GROUP BY q_num) r ON q.q_num = r.q_num "
			+ "WHERE q.q_chk = 0 AND q.i_code = #{code} ORDER BY q.q_num DESC) qna) WHERE rnum BETWEEN #{startRow} AND #{endRow}")
	public ArrayList<ItemQnaDTO> getQnaListByItem(@Param("code") String code, @Param("startRow") int startRow, @Param("endRow") int endRow);
	
	// 단일 게시글
	@Select("SELECT * FROM picker_qna WHERE q_num = #{num}")
	public QnaDTO getQnaByNum(@Param("num") int num);
	
	// 단일 게시글 및 댓글 개수
	@Select("SELECT q.*, NVL(r.re_cnt, 0) AS re_cnt FROM picker_qna q LEFT OUTER JOIN "
			+ "(SELECT q_num, COUNT(*) AS re_cnt FROM picker_reply WHERE r_chk = 0 GROUP BY q_num) r ON q.q_num = r.q_num WHERE q.q_num = #{num}")
	public ItemQnaDTO getQnaWithReplyCount(@Param("num") int num);
	
	// 게시글 별 댓글 목록
	@Select("SELECT * FROM picker_reply WHERE q_num = #{num} ORDER BY q_num DESC, r_seq ASC")
	public ArrayList<ReplyDTO> getReplyByQna(@Param("num") int num);
	
	// 게시글 관련 상품
	@Select("SELECT i_code, i_name, i_img FROM picker_item WHERE i_code = #{code}")
	public ItemDTO getItemInfo(@Param("code") String code);
	
	// 게시글 등록
	@Insert("INSERT INTO picker_qna VALUES ((SELECT NVL(MAX(q_num)+1, 1) FROM picker_qna), "
			+ "#{q_title}, #{q_content}, SYSDATE, #{m_id}, #{m_name}, 0, #{i_img}, #{i_code}, #{i_name}, 0)")
	public int insertQna(QnaDTO dto);
	
	// 게시글 수정
	@Update("UPDATE picker_qna SET q_title = #{q_title}, q_content = #{q_content} WHERE q_num = #{q_num}")
	public int updateQna(QnaDTO dto);
	
	// 게시글 삭제(체크키 변경)
	@Update("UPDATE picker_qna SET q_chk = 1 WHERE q_num = #{num}")
	public int deleteQna(@Param("num") int num);
	
	// 게시글 작성자
	@Select("SELECT m_id FROM picker_qna WHERE q_num = #{num}")
	public String getWriter(@Param("num") int num);
	
	// 댓글 작성자
	@Select("SELECT m_id FROM picker_reply WHERE r_num = #{num}")
	public String getReplyWriter(@Param("num") int num);
	
	// 원 댓글 깊이 및 위치
	@Select("SELECT r_dep, r_seq FROM picker_reply WHERE r_num = #{num}")
	public ReplyDTO getRefPosition(@Param("num") int num);
	
	// 원 댓글 참조하여 위치 설정
	@Select("SELECT NVL(MIN(r_seq), 0) FROM picker_reply WHERE q_num = #{num} AND r_dep <= #{dep} AND r_seq > #{seq}")
	public int setSequenceWithRef(@Param("num") int num, @Param("dep") int dep, @Param("seq") int seq);
	
	// 새 댓글 등록 시 기존 댓글 위치 변경
	@Update("UPDATE picker_reply SET r_seq = r_seq + 1 WHERE q_num = #{num} AND r_seq >= #{seq}")
	public int updateSequence(@Param("num") int num, @Param("seq") int seq);
	
	// 원 댓글 없이 위치 설정
	@Select("SELECT NVL(MAX(r_seq)+1, 1) FROM picker_reply WHERE q_num = #{num}")
	public int setSequenceWithoutRef(@Param("num") int num);
	
	// 댓글 작성
	@Insert("INSERT INTO picker_reply VALUES ((SELECT NVL(MAX(r_num)+1, 1) FROM picker_reply), "
			+ "#{q_num}, #{r_content}, #{m_id}, #{m_name}, SYSDATE, #{r_dep}, #{r_seq}, 0)")
	public int insertReply(ReplyDTO dto);
	
	// 관리자 댓글 작성 시 표시(체크키 변경)
	@Update("UPDATE picker_qna SET q_rchk = 1 WHERE q_num = #{num}")
	public int setAdminReplied(@Param("num") int num);
	
	// 댓글 내용
	@Select("SELECT r_content FROM picker_reply WHERE r_num = #{num}")
	public String getReplyContent(@Param("num") int num);
	
	// 댓글 수정
	@Update("UPDATE picker_reply SET r_content = #{r_content} WHERE r_num = #{r_num}")
	public int updateReply(ReplyDTO dto);
	
	// 댓글 삭제(체크키 변경)
	@Update("UPDATE picker_reply SET r_chk = 1 WHERE r_num = #{num}")
	public int deleteReply(@Param("num") int num);
	
}
