package com.board.biz;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.fileupload.FileUploadException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.board.dao.BoardDao;
import com.board.dto.BoardDto;
import com.board.frm.util.Pagination;
 
@Service
public class BoardBiz {
	private static final Logger logger = LoggerFactory.getLogger(BoardBiz.class);
	
	private final String DOWNLOAD_PATH = "C:\\work";
	private final String URL_PATH = "/simpleboard/upload/";
	private final List<String> ALLOWED_EXTENSIONS = Arrays.asList("jpg", "png", "gif");

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
	
	public int insertBoardItem(BoardDto bDto, MultipartFile file) throws IOException, FileUploadException {
		if(file != null && !file.isEmpty()) 
		{
			String path = uploadFile(file);
			bDto.setImage_path(path);
			result = 0;
		}
		else
		{
			bDto.setImage_path(null);
		}
		result= bDao.insertBoardItem(bDto);
		return result;
	}
	
	public int deleteBoardItem(int brdIdx) {
		return bDao.deleteBoardItem(brdIdx);
	}
	
	public int deleteBoardGroup(String[] idxArr) {
		return bDao.deleteBoardGroup(idxArr);
	}
	
	public int updateBoardItem(BoardDto bDto, MultipartFile file) throws IOException, FileUploadException {
		if(file != null && !file.isEmpty()) 
		{
			String path = uploadFile(file);
			bDto.setImage_path(path);
		}
		else
		{
			bDto.setImage_path(bDto.getOriImagePath());
		}
		
		return bDao.updateBoardItem(bDto);
	}
	
	/**
	 * 파일 업로드를 처리하는 메소드
	 * 
	 * @param file 사용자가 업로드한 파일
	 * @return DB에 저장할 주소
	 * @throws IOException 파일 로컬 저장 실패 시 예외 발생
	 * @throws FileUploadException 파일 확장자가 지정된 이미지 확장자가 아닐 시 예외 발생
	 */
	public String uploadFile(MultipartFile file) throws IOException, FileUploadException {
		String originFileName = file.getOriginalFilename();
		String extension = originFileName.substring(originFileName.lastIndexOf(".")+1);
		Date date = new Date();
		String randomString = String.valueOf(date.getTime());
		
		//확장자 확인
		if(!ALLOWED_EXTENSIONS.contains(extension))
		{
			throw new FileUploadException("이미지파일만 허용됩니다.");
		}
	
		file.transferTo(new File(DOWNLOAD_PATH, randomString+originFileName));
		String path = URL_PATH + randomString + originFileName;
		
		return path;
	}
	
	/**
	 * 페이징 처리를 위해 전체 로우 수를 가져오는 메소드
	 * 
	 * @return bDao.getTotal() 전체 로우 수
	 */
	public int getTotal() {
		return bDao.getTotal();
	}
	
}
