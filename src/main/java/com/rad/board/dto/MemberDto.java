package com.rad.board.dto;

public class MemberDto {
	private String CustomerNum   ;
	private String Id            ;
	private String Pw            ;
	private String CustomerName  ;
	private String CellNum       ;
	private String InsDate       ;
	
	public String getCustomerNum() {
		return CustomerNum;
	}
	public void setCustomerNum(String customerNum) {
		CustomerNum = customerNum;
	}
	public String getId() {
		return Id;
	}
	public void setId(String id) {
		Id = id;
	}
	public String getPw() {
		return Pw;
	}
	public void setPw(String pw) {
		Pw = pw;
	}
	public String getCustomerName() {
		return CustomerName;
	}
	public void setCustomerName(String customerName) {
		CustomerName = customerName;
	}
	public String getCellNum() {
		return CellNum;
	}
	public void setCellNum(String cellNum) {
		CellNum = cellNum;
	}
	public String getInsDate() {
		return InsDate;
	}
	public void setInsDate(String insDate) {
		InsDate = insDate;
	}
	
}