package com.project.picker.DTO;

public class PagingDTO {
	
	private int pageNum;	// 현재 페이지
	private int rowCount; 	// 행 개수
	private int rowSize; 	// 행 사이즈
	private int startRow; 	// 시작행
	private int endRow; 	// 끝행
	private int pageCount; 	// 페이지 개수
	private int pageSize; 	// 페이지 사이즈
	private int startPage;	// 시작 페이지
	private int endPage;	// 끝 페이지
	
	public PagingDTO() { }
	
	public PagingDTO(int pageNum, int rowCount, int rowSize, int pageSize) {
		this.pageNum = pageNum;
		this.rowCount = rowCount;
		this.rowSize = rowSize;
		this.startRow = ((pageNum - 1) * rowSize) + 1;
		this.endRow = pageNum * rowSize;
		this.pageCount = (rowCount / rowSize) + (rowCount % rowSize == 0 ? 0 : 1);
		this.pageSize = pageSize;
		this.startPage = (((pageNum / pageSize) + (pageNum % pageSize == 0 ? -1 : 0)) * pageSize) + 1;
		this.endPage = (this.startPage + pageSize) - 1 > this.pageCount ? this.pageCount : (this.startPage + pageSize) - 1;
	}

	public int getPageNum() {
		return pageNum;
	}
	public int getRowCount() {
		return rowCount;
	}
	public int getRowSize() {
		return rowSize;
	}
	public int getStartRow() {
		return startRow;
	}
	public int getEndRow() {
		return endRow;
	}
	public int getPageCount() {
		return pageCount;
	}
	public int getPageSize() {
		return pageSize;
	}
	public int getStartPage() {
		return startPage;
	}
	public int getEndPage() {
		return endPage;
	}

}
