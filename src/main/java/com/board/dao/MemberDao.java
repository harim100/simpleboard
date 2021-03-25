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
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	int result = 0;
	List<Map<String,Object>> memList= null;
	
	public int checkId(String requestedId) {
		return sqlSessionTemplate.selectOne("checkId", requestedId);
	}
	
	public int insertMember(MemberDto mDto) {
		return sqlSessionTemplate.insert("insertMember", mDto);
	}
	
	public MemberDto login(MemberDto mDto) {
		return sqlSessionTemplate.selectOne("login", mDto);
	}
		
	
}
