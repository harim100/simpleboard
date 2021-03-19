package com.board.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

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
	@NotBlank(message = "{NotBlank.memberDto.id}")
	@Pattern(regexp="^[a-zA-Z0-9]{4,20}$", message = "{Pattern.memberDto.id}")
	private String id;
	/**	고객 비밀번호 */
	@NotBlank(message = "{NotBlank.memberDto.pw}")
	@Pattern(regexp="^(?=.+[0-9])(?=.+[A-Za-z])(?=.+[$@!%*#?&])[A-Za-z0-9$@!%*#?&]{8,20}$", message = "{Pattern.memberDto.pw}")
	private String pw;
	/**	고객 이름 */
	@NotBlank(message = "{NotBlank.memberDto.customerName}")
	@Pattern(regexp="^[a-zA-Z가-힣]{2,30}$", message = "{Pattern.memberDto.customerName}")
	private String customerName;
	/**	고객 휴대폰번호 */
	@Pattern(regexp="^[0-9]{11,15}$", message = "{Pattern.memberDto.cellNum}")
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