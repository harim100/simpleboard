package com.board.dto;
/**
 * 
 * @author Jung.Harim
 *
 */
public class BoardDto {
	/** 글번호*/
	private int brdIdx;
	/** 작성자의 고객번호*/
	private int customerNum;
	/** 글제목*/
	private String title;
	/** 글내용*/
	private String content;
	/** 이미지 경로*/
	private String imagePath;
	/** 작성일*/
	private String insDate;
	/** 글 사용여부*/
	private String useYN;
	/** 수정일*/
	private String updateDate;
	/** 수정시 사용하는 기존 이미지 주소*/
	private String oriImagePath;
	
	public int getBrdIdx() {
		return brdIdx;
	}
	public void setBrdIdx(int brdIdx) {
		this.brdIdx = brdIdx;
	}
	public int getCustomerNum() {
		return customerNum;
	}
	public void setCustomerNum(int customerNum) {
		this.customerNum = customerNum;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public String getInsDate() {
		return insDate;
	}
	public void setInsDate(String insDate) {
		this.insDate = insDate;
	}
	public String getUseYN() {
		return useYN;
	}
	public void setUseYN(String useYN) {
		this.useYN = useYN;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public String getOriImagePath() {
		return oriImagePath;
	}
	public void setOriImagePath(String oriImagePath) {
		this.oriImagePath = oriImagePath;
	}
	
	
	
}
