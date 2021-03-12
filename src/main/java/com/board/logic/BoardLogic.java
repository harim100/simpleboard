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
	
	private final String DOWNLOAD_PATH = "C:\\work";
	private final String URL_PATH = "/simpleboard/upload/";
	private final String DEFAULT_IMAGE = "default.png";
	
	@Autowired(required=false)
	private BoardDao bDao;

	public List<Map<String, Object>> boardList(Map offset) {
		return bDao.boardList(offset);
	}
	public BoardVO boardSelect(Map<String, Object> pMap) {
		return bDao.boardSelect(pMap);
	}
	
	public int boardInsert(Map<String, Object> pMap, MultipartFile file) throws IllegalStateException, IOException {
		if(file.isEmpty()) {
			pMap.put("imagePath", URL_PATH + DEFAULT_IMAGE);
		} else {
			String path = fileUpload(file);
			pMap.put("imagePath", path);
		}
		
		return bDao.boardInsert(pMap);
	}
	
	public int boardDelete(Map<String, Object> pMap) {
		return bDao.boardDelete(pMap);
	}
	
	public int boardDeleteGroup(String[] pMap) {
		return bDao.boardDeleteGroup(pMap);
	}
	
	public int boardUpdate(Map<String, Object> pMap, MultipartFile file) throws IllegalStateException, IOException{
		if(file.isEmpty()) {
			pMap.put("imagePath", pMap.get("oriImagePath"));
		} else {
			String path = fileUpload(file);
			pMap.put("imagePath", path);
		}
		
		return bDao.boardUpdate(pMap);
	}
	
	public String fileUpload(MultipartFile file) throws IllegalStateException, IOException {
		String originFileName = file.getOriginalFilename();
		
	   if (!file.getOriginalFilename().isEmpty()) {
	      file.transferTo(new File(DOWNLOAD_PATH, originFileName));
	   }
	   String path = URL_PATH + originFileName;
	   
	   return path;
	}
	public int getTotal() {
		return bDao.getTotal();
	}
	
	

}
