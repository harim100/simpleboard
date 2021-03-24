package com.board.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.board.dto.MemberDto;

/**
 * @author Jung.Harim
 *
 */
@Repository
public class MemberDao {
	private static final Logger logger = LoggerFactory.getLogger(MemberDao.class);
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	int result = 0;
	List<Map<String,Object>> memList= null;
	
//==================================== [[join]] ==========================================
	/**
	 * @param pMap = (requestedId, value)
	 */
	public int checkId(String requestedId) {
		return sqlSessionTemplate.selectOne("checkId", requestedId);
	}
	
	/**
	 * 
	 * @param pMap 가입 시 아이디, 비밀번호, 이름, 연락처
	 * @return
	 */
	public int insertMember(MemberDto mDto) {
		return sqlSessionTemplate.insert("insertMember", mDto);
	}
	
//==================================== [[login]] ==========================================
	public MemberDto login(MemberDto memDto) {
		return sqlSessionTemplate.selectOne("login", memDto);
	}
		
	
}
