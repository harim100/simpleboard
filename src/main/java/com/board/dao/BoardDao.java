package com.board.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.board.dto.BoardDto;


@Repository 
public class BoardDao {
	private static final Logger logger = LoggerFactory.getLogger(BoardDao.class);
	
	@Autowired(required=false) 
	private SqlSessionTemplate sqlSessionTemplate;
	int result = 0; 
	
	public List<Map<String, Object>> boardList(int offset) {
		return sqlSessionTemplate.selectList("boardList", offset);
	}
	
	public BoardDto boardSelect(Map<String, Object> pMap) {
		return sqlSessionTemplate.selectOne("boardSelect", pMap);
	}
	
	public int boardDelete(Map<String, Object> pMap) {
		return sqlSessionTemplate.delete("boardDelete", pMap);
	}
 
	public int boardDeleteGroup(String[] idxArr) {
		return sqlSessionTemplate.delete("boardDeleteGroup", idxArr);
	}
	
	public int boardUpdate(Map<String, Object> pMap) {
		return sqlSessionTemplate.update("boardUpdate", pMap);
	}
	
	public int boardInsert(Map<String, Object> pMap) {
		return sqlSessionTemplate.insert("boardInsert", pMap);
	}

	public int getTotal() {
		return sqlSessionTemplate.selectOne("getTotal");
	}
		
	
}
