package com.project.picker.DAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.project.picker.DTO.ItemDTO;

@Repository
public class ItemDAOImpl implements ItemDAO {
	
	@Inject
	SqlSession sqlSession;

	@Override
	public List<ItemDTO> itemList(String i_category, String item_sort) {
		Map<String, Object> map = new HashMap<>();
		map.put("i_category", i_category);
		map.put("item_sort", item_sort);
		return sqlSession.selectList("item.itemList", map);
	}

}
