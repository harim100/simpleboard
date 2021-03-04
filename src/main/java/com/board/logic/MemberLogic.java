package com.board.logic;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.board.dao.MemberDao;

public class MemberLogic {
	private static final Logger logger = LoggerFactory.getLogger(MemberLogic.class);
	@Autowired(required=false)
	private MemberDao memDao;
	
	
/////////////////////////////////[[ MemberDao_1 ]]////////////////////////////////	
	public List<Map<String,Object>> member_login(Map<String,Object> pMap) {
		logger.info("member_login 호출 성공");
		logger.info(pMap.toString());
		return memDao.member_login(pMap);
	}
	
	public List<Map<String,Object>> member_id_check(Map<String,Object> pMap) {
		logger.info("member_id_check 호출 성공");
		return memDao.member_login(pMap);
	}
	
	public List<Map<String, Object>> member_info(Map<String,Object> pMap) {
		logger.info("member_info 호출 성공");
		return memDao.member_info(pMap);
	}
	
	
	public int member_join(Map<String,Object> pMap) {
		logger.info("member_join 호출 성공");
		return memDao.member_join(pMap);
	}
}
