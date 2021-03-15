package com.rad.board.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rad.board.dto.MemberDto;


@Repository
public class MemberDao {
	private static final Logger logger = LoggerFactory.getLogger(MemberDao.class);
	
	@Autowired(required=false)
	private SqlSessionTemplate sqlSessionTemplate;
	int result = 0;
	List<Map<String,Object>> memList= null;
	
//==================================== [[join]] ==========================================
	/*
	 * pMap = (requestedId, value)
	 */
	public int member_id_check(Map<String, Object> pMap) {
		logger.info("member_id_check 호출성공");
		return sqlSessionTemplate.selectOne("checkId",pMap);
	}
	
	public int insertMember(Map<String, Object> pMap) {
		logger.info("member_join 호출성공");
		return sqlSessionTemplate.insert("joinMember",pMap);
	}
	
//==================================== [[login]] ==========================================
	
	public MemberDto login(MemberDto vo) {
		logger.info("member_login 호출성공");
		return sqlSessionTemplate.selectOne("loginBcrypt",vo);
	}
		
	
}
