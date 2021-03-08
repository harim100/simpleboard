package com.board.logic;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.dao.BoardDao;
import com.board.vo.BoardVO;
 
@Service
public class BoardLogic {
	private static final Logger logger = LoggerFactory.getLogger(BoardLogic.class);
	@Autowired(required=false)
	private BoardDao bDao;

	public List<Map<String, Object>> boardList() {
		return bDao.boardList();
	}
	public BoardVO boardSelect(Map<String, Object> pMap) {
		logger.info("boardModify 호출 성공");
		return bDao.boardSelect(pMap);
	}
	

}
