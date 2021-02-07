package com.project.picker.DTO;

import org.springframework.web.multipart.MultipartFile;

public class ItemInsertDTO extends ItemDTO {
	
	public ItemInsertDTO(){}
	
	private MultipartFile mainFile, detailFile;

	public MultipartFile getMainFile() {
		return mainFile;
	}

	public void setMainFile(MultipartFile mainFile) {
		this.mainFile = mainFile;
	}

	public MultipartFile getDetailFile() {
		return detailFile;
	}

	public void setDetailFile(MultipartFile detailFile) {
		this.detailFile = detailFile;
	}
	
	

}
