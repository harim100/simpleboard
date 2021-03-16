package com.board.service;

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
import com.board.dto.BoardDto;
 
@Service
public class BoardService {
	private static final Logger logger = LoggerFactory.getLogger(BoardService.class);
	
	private final String DOWNLOAD_PATH = "C:\\work";
	private final String URL_PATH = "/simpleboard/upload/";
	
	@Autowired(required=false)
	private BoardDao bDao;

	public List<Map<String, Object>> boardList(Map offset) {
		return bDao.boardList(offset);
	}
	public BoardDto boardSelect(Map<String, Object> pMap) {
		return bDao.boardSelect(pMap);
	}
	
	public int boardInsert(Map<String, Object> pMap, MultipartFile file) throws IllegalStateException, IOException {
		if(file != null && !file.isEmpty()) 
		{
			String path = fileUpload(file);
			pMap.put("imagePath", path);
		}
		else
		{
			pMap.put("imagePath", null);
		}
		logger.info("pMap" + pMap);
		return bDao.boardInsert(pMap);
	}
	
	public int boardDelete(Map<String, Object> pMap) {
		return bDao.boardDelete(pMap);
	}
	
	public int boardDeleteGroup(String[] idxArr) {
		return bDao.boardDeleteGroup(idxArr);
	}
	
	public int boardUpdate(Map<String, Object> pMap, MultipartFile file) throws IllegalStateException, IOException{
		if(file != null) 
		{
			String path = fileUpload(file);
			pMap.put("imagePath", path);
		}
		else
		{
			pMap.put("imagePath", pMap.get("oriImagePath"));
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
