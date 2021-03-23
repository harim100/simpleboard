package com.board.biz;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;

import com.board.dao.BoardDao;
import com.board.dto.BoardDto;
import com.board.util.Pagination;
 
@Service
public class BoardBiz {
	private static final Logger logger = LoggerFactory.getLogger(BoardBiz.class);
	
	private final String DOWNLOAD_PATH = "C:\\work";
	private final String URL_PATH = "/simpleboard/upload/";
	
	@Autowired
	private BoardDao bDao;
	
	Pagination pagination;
	int result;

	public List<BoardDto> getBoardList(int offset) {
		
		return bDao.getBoardList(offset);
	}
	
	public Pagination getPages(int page) {
		int totalRows = this.getTotal();
		pagination = new Pagination(totalRows, page-1);
		
		return pagination;
	}
	
	public BoardDto getBoardItem(int brdIdx) {
		return bDao.getBoardItem(brdIdx);
	}
	
	public int insertBoardItem(BoardDto bDto, MultipartFile file) 
			throws IllegalStateException, IOException {
		if(file != null && !file.isEmpty()) 
		{
			String path = fileUpload(file);
			bDto.setImage_path(path);
			result= bDao.insertBoardItem(bDto);
			result = 0;
			throw new RuntimeException("RuntimeException for transaction");
		}
		else
		{
			bDto.setImage_path(null);
			result= bDao.insertBoardItem(bDto);
		}
		return result;
	}
	
	public int deleteBoardItem(int brdIdx) {
		return bDao.deleteBoardItem(brdIdx);
	}
	
	public int deleteBoardGroup(String[] idxArr) {
		return bDao.deleteBoardGroup(idxArr);
	}
	
	public int updateBoardItem(BoardDto bDto, MultipartFile file) throws IllegalStateException, IOException{
		if(file != null && !file.isEmpty()) 
		{
			String path = fileUpload(file);
			bDto.setImage_path(path);
		}
		else
		{
			bDto.setImage_path(bDto.getOriImagePath());
		}
		return bDao.updateBoardItem(bDto);
	}
	
	public String fileUpload(MultipartFile file) throws IllegalStateException, IOException {
		String originFileName = file.getOriginalFilename();
		Date date = new Date();
		String randomString = String.valueOf(date.getTime());
		
		if (!file.getOriginalFilename().isEmpty()) 
		{
			file.transferTo(new File(DOWNLOAD_PATH, randomString+originFileName));
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
