package com.board.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.board.dto.MemberDto;

@Repository
public class MemberDao {
	private static final Logger logger = LoggerFactory.getLogger(MemberDao.class);
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	int result = 0;
	List<Map<String,Object>> memList= null;
	
//==================================== [[join]] ==========================================
	public int checkId(String requestedId) {
		return sqlSessionTemplate.selectOne("checkId", requestedId);
	}
	
	public int insertMember(MemberDto mDto) {
		return sqlSessionTemplate.insert("insertMember", mDto);
	}
	
//==================================== [[login]] ==========================================
	public MemberDto login(MemberDto mDto) {
		return sqlSessionTemplate.selectOne("login", mDto);
	}
		
	
}
