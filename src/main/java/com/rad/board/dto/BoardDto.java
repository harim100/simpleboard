package com.rad.board.dto;

public class BoardDto {
	private String BrdIdx     ;
	private String Title      ;
	private String Content    ;
	private String ImagePath  ;
	private String InsDate    ;
	private String IsUse      ;
	private String UpdateDate ;
	private String CustomerNum;
	

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
	public String getIsUse() {
		return IsUse;
	}
	public void setIsUse(String isUse) {
		IsUse = isUse;
	}
	public String getUpdateDate() {
		return UpdateDate;
	}
	public void setUpdateDate(String updateDate) {
		UpdateDate = updateDate;
	}
	
}