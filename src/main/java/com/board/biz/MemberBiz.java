package com.board.biz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.dao.MemberDao;
import com.board.dto.MemberDto;

@Service
public class MemberBiz { 
	private static final Logger logger = LoggerFactory.getLogger(MemberBiz.class);
	
	@Autowired
	private MemberDao memDao;

//==================================== [[join]] ==========================================
	public MemberDto login(MemberDto mDto) {
		return memDao.login(mDto);
	}
	
	public int checkId(String requestedId) {
		return memDao.checkId(requestedId);
	}

//==================================== [[login]] ==========================================
	public int insertMember(MemberDto mDto) {
		return memDao.insertMember(mDto);
	}
}
