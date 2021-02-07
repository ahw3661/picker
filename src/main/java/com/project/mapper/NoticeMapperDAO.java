package com.project.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.project.picker.DTO.NoticeDTO;

@Repository
public interface NoticeMapperDAO {
	
	@Insert("INSERT INTO picker_notice VALUES ((SELECT NVL(MAX(n_num)+1, 1) FROM picker_notice), "
			+ "#{n_title}, #{n_content}, SYSDATE, #{m_id}, 0, 0)")
	public int insertNotice(NoticeDTO dto);
	
	@Select("SELECT COUNT(*) FROM picker_notice WHERE n_title LIKE #{keyword} AND n_chk = 0")
	public int getNoticeCount(@Param("keyword") String keyword);
	
	@Select("SELECT n_num, n_title FROM (SELECT n.*, ROWNUM AS rnum FROM "
			+ "(SELECT * FROM picker_notice WHERE n_title LIKE #{keyword} AND n_chk = 0 "
			+ "ORDER BY n_num DESC) n) WHERE rnum BETWEEN #{startRow} AND #{endRow}")
	public ArrayList<NoticeDTO> getNoticeList(@Param("keyword") String keyword, 
			@Param("startRow") int startRow, @Param("endRow") int endRow);
	
	@Update("UPDATE picker_notice SET n_cnt = n_cnt + 1 WHERE n_num = #{num}")
	public int updateReadCount(@Param("num") int num);
	
	@Select("SELECT * FROM picker_notice WHERE n_num = #{num}")
	public NoticeDTO getNoticeByNum(@Param("num") int num);
	
	@Update("UPDATE picker_notice SET n_title = #{n_title}, n_content = #{n_content} WHERE n_num = #{n_num}")
	public int updateNotice(NoticeDTO dto);
	
	@Update("UPDATE picker_notice SET n_chk = 1 WHERE n_num = #{num}")
	public int deleteNotice(@Param("num") int num);
	
	@Select("SELECT NVL(MIN(n_num), 0) FROM picker_notice WHERE n_num > #{num} AND n_chk = 0")
	public int checkNext(@Param("num") int num);
	
	@Select("SELECT NVL(MAX(n_num), 0) FROM picker_notice WHERE n_num < #{num} AND n_chk = 0")
	public int checkPrev(@Param("num") int num);
	
	@Select("SELECT n_title FROM picker_notice WHERE n_num = #{num}")
	public String getTitle(@Param("num") int num);
	
}
