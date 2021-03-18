package com.board.dto;
/**
 * 
 * @author Jung.Harim
 *
 */
public class BoardDto {
	/** 글번호*/
	private String BrdIdx;
	/** 작성자의 고객번호*/
	private String CustomerNum;
	/** 글제목*/
	private String Title;
	/** 글내용*/
	private String Content;
	/** 이미지 경로*/
	private String ImagePath;
	/** 작성일*/
	private String InsDate;
	/** 글 사용여부*/
	private String UseYN;
	/** 수정일*/
	private String UpdateDate;
	

	public String getCustomerNum() {
		return CustomerNum;
	}
	public void setCustomerNum(String customerNum) {
		CustomerNum = customerNum;
	}
	public String getBrdIdx() {
		return BrdIdx;
	}
	public void setBrdIdx(String brdIdx) {
		BrdIdx = brdIdx;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public String getImagePath() {
		return ImagePath;
	}
	public void setImagePath(String imagePath) {
		ImagePath = imagePath;
	}
	public String getInsDate() {
		return InsDate;
	}
	public void setInsDate(String insDate) {
		InsDate = insDate;
	}
	public String getUseYN() {
		return UseYN;
	}
	public void setUseYN(String useYN) {
		UseYN = useYN;
	}
	public String getUpdateDate() {
		return UpdateDate;
	}
	public void setUpdateDate(String updateDate) {
		UpdateDate = updateDate;
	}
}
