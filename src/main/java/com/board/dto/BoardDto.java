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
	private int customer_num;
	
	/** 글제목*/
	@NotBlank(message = "{NotBlank.bDto.title}")
	@Max(value=30, message = "{Max.bDto.title}")
	private String title;
	
	/** 글내용*/
	@NotBlank(message = "{NotBlank.bDto.content}")
	@Max(value=100, message = "{Max.bDto.content}")
	private String content;
	
	/** 이미지 경로*/
	private String image_path;
	
	/** 작성일*/
	private String ins_date;
	
	/** 글 사용여부*/
	private String use_yn;
	
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
	public int getCustomer_num() {
		return customer_num;
	}
	public void setCustomer_num(int customer_num) {
		this.customer_num = customer_num;
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
