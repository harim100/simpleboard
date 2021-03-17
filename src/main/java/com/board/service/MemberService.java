package com.board.service;

import java.util.Map;

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
	public MemberDto login(MemberDto vo) {
		return memDao.login(vo);
	}
	
	public int member_id_check(Map<String,Object> pMap) {
		return memDao.member_id_check(pMap);
	}

//==================================== [[login]] ==========================================
	public int insertMember(Map<String,Object> pMap) {
		return memDao.insertMember(pMap);
	}
}
