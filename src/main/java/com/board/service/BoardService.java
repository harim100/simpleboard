package com.board.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.board.dao.BoardDao;
import com.board.dto.BoardDto;
import com.board.util.Pagination;
 
@Service
public class BoardService {
	private static final Logger logger = LoggerFactory.getLogger(BoardService.class);
	
	private final String DOWNLOAD_PATH = "C:\\work";
	private final String URL_PATH = "/simpleboard/upload/";
	
	@Autowired(required=false)
	private BoardDao bDao;
	
	Pagination pagination;

	public List<BoardDto> boardList(int offset) {
		
		return bDao.boardList(offset);
	}
	
	public Pagination getPages(int page) {
		int totalRows = this.getTotal();
		pagination = new Pagination(totalRows, page-1);
		
		return pagination;
	}
	
	public BoardDto boardSelect(int brdIdx) {
		return bDao.boardSelect(brdIdx);
	}
	
	public int boardInsert(BoardDto bDto, MultipartFile file) throws IllegalStateException, IOException {
		if(file != null && !file.isEmpty()) 
		{
			String path = fileUpload(file);
			bDto.setImagePath(path);
		}
		else
		{
			bDto.setImagePath(null);
		}
		return bDao.boardInsert(bDto);
	}
	
	public int boardDelete(int brdIdx) {
		return bDao.boardDelete(brdIdx);
	}
	
	public int boardDeleteGroup(String[] idxArr) {
		return bDao.boardDeleteGroup(idxArr);
	}
	
	public int boardUpdate(BoardDto bDto, MultipartFile file) throws IllegalStateException, IOException{
		if(file != null && !file.isEmpty()) 
		{
			String path = fileUpload(file);
			bDto.setImagePath(path);
		}
		else
		{
			bDto.setImagePath(bDto.getOriImagePath());
		}
		return bDao.boardUpdate(bDto);
	}
	
	public String fileUpload(MultipartFile file) throws IllegalStateException, IOException {
		String originFileName = file.getOriginalFilename();
		
	   if (!file.getOriginalFilename().isEmpty()) {
	      file.transferTo(new File(DOWNLOAD_PATH, originFileName));
	   }
	   String path = URL_PATH + originFileName;
	   
	   return path;
	}
	
	/**
	 * 페이징 처리를 위해 전체 로우 수를 가져오는 메소드
	 * @return bDao.getTotal() 전체 로우 수
	 */
	public int getTotal() {
		return bDao.getTotal();
	}
	
}
