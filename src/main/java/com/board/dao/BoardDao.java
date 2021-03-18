package com.board.dao;

import java.util.List;

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
	
	public List<BoardDto> boardList(int offset) {
		return sqlSessionTemplate.selectList("boardList", offset);
	}
	
	public BoardDto boardSelect(int idx) {
		return sqlSessionTemplate.selectOne("boardSelect", idx);
	}
	
	public int boardDelete(int brdIdx) {
		return sqlSessionTemplate.delete("boardDelete", brdIdx);
	}
 
	public int boardDeleteGroup(String[] idxArr) {
		return sqlSessionTemplate.delete("boardDeleteGroup", idxArr);
	}
	
	public int boardUpdate(BoardDto bDto) {
		return sqlSessionTemplate.update("boardUpdate", bDto);
	}
	
	public int boardInsert(BoardDto bDto) {
		return sqlSessionTemplate.insert("boardInsert", bDto);
	}

	public int getTotal() {
		return sqlSessionTemplate.selectOne("getTotal");
	}
		
	
}
