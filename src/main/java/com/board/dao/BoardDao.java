package com.board.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.board.vo.BoardVO;


@Repository 
public class BoardDao {
	private static final Logger logger = LoggerFactory.getLogger(BoardDao.class);
	
	@Autowired(required=false) 
	private SqlSessionTemplate sqlSessionTemplate;
	int result = 0; 
	

	public List<Map<String, Object>> boardList() {
		return sqlSessionTemplate.selectList("boardList");
	}
	public BoardVO boardSelect(Map<String, Object> pMap) {
		return sqlSessionTemplate.selectOne("boardSelect", pMap);
	}
		
	
}