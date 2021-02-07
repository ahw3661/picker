package com.project.picker.DAO;

import java.util.List;

import com.project.picker.DTO.PointDTO;

public interface PointDAO {

	public List<PointDTO> allPointList(int startRow, int endRow);

	public List<PointDTO> onePointDetail(String m_id, int startRow, int endRow);

}
