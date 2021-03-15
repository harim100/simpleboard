package com.rad.board.logic;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rad.board.dao.MemberDao;
import com.rad.board.dto.MemberDto;

@Service
public class MemberLogic {
	private static final Logger logger = LoggerFactory.getLogger(MemberLogic.class);
	@Autowired(required=false)
	private MemberDao memDao;

//==================================== [[join]] ==========================================
	public MemberDto login(MemberDto vo) {
		logger.info("member_login 호출 성공");
		return memDao.login(vo);
	}
	
	public int member_id_check(Map<String,Object> pMap) {
		logger.info("member_id_check 호출 성공");
		return memDao.member_id_check(pMap);
	}

//==================================== [[login]] ==========================================
	public int insertMember(Map<String,Object> pMap) {
		logger.info("member_join 호출 성공");
		return memDao.insertMember(pMap);
	}
}
