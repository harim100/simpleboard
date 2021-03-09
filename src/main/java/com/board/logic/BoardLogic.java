package com.board.logic;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.board.dao.BoardDao;
import com.board.vo.BoardVO;
 
@Service
public class BoardLogic {
	private static final Logger logger = LoggerFactory.getLogger(BoardLogic.class);
	
	private final String DOWNLOAD_PATH = "\\resources\\img";
	
	
	@Autowired(required=false)
	private BoardDao bDao;

	public List<Map<String, Object>> boardList() {
		return bDao.boardList();
	}
	public BoardVO boardSelect(Map<String, Object> pMap) {
		logger.info("boardModify 호출 성공");
		return bDao.boardSelect(pMap);
	}
	
	public int boardInsert(Map<String, Object> pMap) {
		logger.info("boardModify 호출 성공");
		return bDao.boardInsert(pMap);
	}
	
	public int boardDelete(Map<String, Object> pMap) {
		logger.info("delete 호출 성공");
		return bDao.boardDelete(pMap);
	}
	
	public int boardUpdate(Map<String, Object> pMap, MultipartFile file) throws IllegalStateException, IOException{
		logger.info("update 호출 성공");
		return bDao.boardUpdate(pMap);
	}
	
	
	

}
