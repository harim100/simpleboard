package com.board.dto;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.board.dao.MemberDao;
import com.board.frm.util.CellPhoneConstraint;
import com.board.frm.util.IdDuplicationConstraint;

/**
 * 
 * @author Jung.Harim
 * @see com.board.dao.MemberDao#login(MemberDto dto)
 *		,com.board.dao.MemberDao#insertMember(MemberDto dto, BindingResult bindingResult, HttpServletResponse res)
 * 
 */
public class MemberDto {
	private static final Logger logger = LoggerFactory.getLogger(MemberDto.class);
	
	/**	고객번호 */
	private int customer_no;

	/**	고객 아이디 */
	@NotBlank
	@Pattern(regexp="^[a-zA-Z0-9]{4,20}$")
	@IdDuplicationConstraint
	private String id;
	
	/**	고객 비밀번호 */
	@NotBlank
	@Pattern(regexp="^(?=.*[0-9])(?=.*[A-Za-z])(?=.*[$@!%*#?&])[A-Za-z0-9$@!%*#?&]{8,20}$")
	private String pw;
	
	/** 고객 비밀번호 확인 */
	private String pwConfirm;
	
	private boolean pwConfirmCheck;
	
	@AssertTrue
	private boolean isPwConfirmCheck() {
		return this.pwConfirm.equals(this.pw);
	}

	/**	고객 이름 */
	@NotBlank
	@Pattern(regexp="^[a-zA-Z가-힣]{2,30}$")
	private String customer_nm;
	
	/**	고객 휴대폰번호 */
	@CellPhoneConstraint
	private String cell_no;
	
	/**	가입일시 */
	private String ins_date;
	
	public int getCustomer_no() {
		return customer_no;
	}
	public void setCustomer_no(int customer_no) {
		this.customer_no = customer_no;
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
	public String getCustomer_nm() {
		return customer_nm;
	}
	public void setCustomer_nm(String customer_nm) {
		this.customer_nm = customer_nm;
	}
	public String getCell_no() {
		return cell_no;
	}
	public void setCell_no(String cell_no) {
		this.cell_no = cell_no;
	}
	public String getIns_date() {
		return ins_date;
	}
	public void setIns_date(String ins_date) {
		this.ins_date = ins_date;
	}
	public String getPwConfirm() {
		return pwConfirm;
	}
	public void setPwConfirm(String pwConfirm) {
		this.pwConfirm = pwConfirm;
	}
	public boolean getPwConfirmCheck() {
		return pwConfirmCheck;
	}
	public void setPwConfirmCheck(boolean pwConfirmCheck) {
		this.pwConfirmCheck = pwConfirmCheck;
	}
}