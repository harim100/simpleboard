package com.board.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.dao.MemberDao;
import com.board.dto.MemberDto;

@Service
public class MemberService { 
	private static final Logger logger = LoggerFactory.getLogger(MemberService.class);
	@Autowired(required=false)
	private MemberDao memDao;

//==================================== [[join]] ==========================================
	public MemberDto login(MemberDto mDto) {
		return memDao.login(mDto);
	}
	
	public int member_id_check(String requestedId) {
		return memDao.member_id_check(requestedId);
	}

//==================================== [[login]] ==========================================
	public int insertMember(MemberDto mDto) {
		return memDao.insertMember(mDto);
	}
}
