package com.board.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;

/**
 * 
 * @author Jung.Harim
 *
 */
public class BoardDto {
	/** 글번호*/
	private int brd_idx;
	
	/** 작성자의 고객번호*/
	private int customer_no;
	
	/** 글제목*/
	@NotBlank
	@Max(value=30)
	private String title;
	
	/** 글내용*/
	@NotBlank
	@Max(value=100)
	private String content;
	
	/** 이미지 경로*/
	private String image_path;
	
	/** 글 사용여부*/
	private String use_yn;
	
	/** 작성일*/
	private String ins_date;
	
	/** 수정일*/
	private String upt_date;
	
	/** 수정시 사용하는 기존 이미지 주소*/
	private String oriImagePath;
	
	public int getBrd_idx() {
		return brd_idx;
	}
	public void setBrd_idx(int brd_idx) {
		this.brd_idx = brd_idx;
	}
	public int getCustomer_no() {
		return customer_no;
	}
	public void setCustomer_no(int customer_no) {
		this.customer_no = customer_no;
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
	public String getImage_path() {
		return image_path;
	}
	public void setImage_path(String image_path) {
		this.image_path = image_path;
	}
	public String getIns_date() {
		return ins_date;
	}
	public void setIns_date(String ins_date) {
		this.ins_date = ins_date;
	}
	public String getUse_yn() {
		return use_yn;
	}
	public void setUse_yn(String use_yn) {
		this.use_yn = use_yn;
	}
	public String getUpt_date() {
		return upt_date;
	}
	public void setUpt_date(String upt_date) {
		this.upt_date = upt_date;
	}
	public String getOriImagePath() {
		return oriImagePath;
	}
	public void setOriImagePath(String oriImagePath) {
		this.oriImagePath = oriImagePath;
	}

	
}
