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
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	int result = 0; 
	
	public List<BoardDto> getBoardList(int offset) {
		return sqlSessionTemplate.selectList("getBoardList", offset);
	}
	
	public BoardDto getBoardItem(int brdIdx) {
		return sqlSessionTemplate.selectOne("getBoardItem", brdIdx);
	}
	
	public int deleteBoardItem(int brdIdx) {
		return sqlSessionTemplate.delete("deleteBoardItem", brdIdx);
	}
 
	public int deleteBoardGroup(String[] idxArr) {
		return sqlSessionTemplate.delete("deleteBoardGroup", idxArr);
	}
	
	public int updateBoardItem(BoardDto bDto) {
		return sqlSessionTemplate.update("updateBoardItem", bDto);
	}
	
	public int insertBoardItem(BoardDto bDto) {
		return sqlSessionTemplate.insert("insertBoardItem", bDto);
	}

	public int getTotal() {
		return sqlSessionTemplate.selectOne("getTotal");
	}
		
}
