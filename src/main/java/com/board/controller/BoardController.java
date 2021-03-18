package com.board.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.board.dto.BoardDto;
import com.board.service.BoardService;
import com.board.util.Pagination;

/**
 * 게시판관련 컨트롤러
 * @author Jung.Harim
 *
 */
@Controller
public class BoardController {    
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired(required=false) 
	BoardService bService;  
	
	/**
	 * @param model 프론트로 게시글 목록과 페이지처리 객체를 전달
	 * @param page 쿼리스트링에서 가져오는 현재페이지 번호
	 * @return
	 */
	@RequestMapping("/board/list")  
	public String boardList(Model model, @RequestParam(defaultValue="1") int page) {
		Pagination pagination = bService.getPages(page);
		List<BoardDto> bList = bService.boardList(pagination.getOffset(page-1));

		model.addAttribute("bList", bList);
		model.addAttribute("pagination", pagination);     
		return "boardList";  
	}  
	
	@RequestMapping("/board/view") 
	public String modify(Model model, @RequestParam("brdIdx") int brdIdx, BoardDto bDto) {
		bDto = bService.boardSelect(brdIdx);
		model.addAttribute("bDto", bDto);
		return "boardModify";  
	}    
	      
	@RequestMapping("/board/insert")   
	public String insert(Model model, @ModelAttribute BoardDto bDto
			,@RequestParam(value = "imageFile",required = false) MultipartFile file) throws IllegalStateException, IOException {
		int result = bService.boardInsert(bDto, file);
		model.addAttribute("result", result);
		return "redirect:/board/list";  
	}
	
	/**
	 * 글쓰기 페이지로 이동하는 메소드
	 * @return boardWrite 
	 */
	@RequestMapping("/board/write") 
	public String write() {
		return "boardWrite"; 
	} 
	
	@RequestMapping("/error/attachFileOverSizeErr") 
	public String handleUploadError() {
		return "handleUploadError";  
	}
	
	@RequestMapping("/board/delete") 
	public String delete(Model model, @RequestParam("brdIdx") int brdIdx) {
		int result = bService.boardDelete(brdIdx);
		model.addAttribute("result", result);
		return "redirect:/board/list";  
	}
	  
	@RequestMapping("/board/delete/group")  
	public String deleteGroup(Model model, @RequestParam(value = "idxArr[]") String[] idxArr) {
		int result = bService.boardDeleteGroup(idxArr);   
		model.addAttribute("result", result); 
		return "redirect:/board/list";
	}  
	
	@RequestMapping("/board/update")  
	public String update(Model model, HttpServletRequest request, @ModelAttribute BoardDto bDto
			, @RequestParam(value = "imageFile", required = false) MultipartFile file) throws IOException {
		
		int result = bService.boardUpdate(bDto, file);
		model.addAttribute("result", result); 
		return "redirect:/board/view?brdIdx=" + bDto.getBrdIdx();
	} 
	
}
