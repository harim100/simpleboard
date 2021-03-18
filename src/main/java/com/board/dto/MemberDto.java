package com.board.dto;

/**
 * 
 * @author Jung.Harim
 * @see com.board.dao.MemberDao#login(MemberDto dto)
 * 
 */
public class MemberDto {
	/**	고객번호 */
	private int customerNum;
	/**	고객 아이디 */
	private String id;
	/**	고객 비밀번호 */
	private String pw;
	/**	고객 이름 */
	private String customerName;
	/**	고객 휴대폰번호 */
	private String cellNum;
	/**	가입일시 */
	private String insDate;
	
	public int getCustomerNum() {
		return customerNum;
	}
	public void setCustomerNum(int customerNum) {
		this.customerNum = customerNum;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCellNum() {
		return cellNum;
	}
	public void setCellNum(String cellNum) {
		this.cellNum = cellNum;
	}
	public String getInsDate() {
		return insDate;
	}
	public void setInsDate(String insDate) {
		this.insDate = insDate;
	}
	

	
}