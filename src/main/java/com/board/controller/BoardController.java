package com.board.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.board.dto.BoardDto;
import com.board.service.BoardService;
import com.board.util.Pagination;

/**
 * 
 */
@Controller   
public class BoardController {    
	 
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	 
	@Autowired(required=false) 
	BoardService bService;  
	
	@RequestMapping("/board/list")  
	public String boardList(Model model, BoardDto bVO, @RequestParam(defaultValue="1") int page) {
		Pagination pagination = bService.getPages(page);
		List<Map<String,Object>> bList = bService.boardList(pagination.getOffset(page-1));
		
		model.addAttribute("bList", bList);
		model.addAttribute("pagination", pagination);     
		return "boardList";  
	}  
	  
	@RequestMapping("/board/view") 
	public String modify(Model model, @RequestParam Map<String,Object> pMap, BoardDto bVO) {
		bVO = bService.boardSelect(pMap);
		model.addAttribute("bVO", bVO);
		return "boardModify";  
	}    
	      
	@RequestMapping("/board/insert")   
	public String insert(Model model, @RequestParam Map<String,Object> pMap
			, @RequestParam(value = "imagePath",required = false) MultipartFile file) throws IllegalStateException, IOException {
		int result = bService.boardInsert(pMap, file);
		model.addAttribute("result", result);
		return "redirect:/board/list";  
	}
	   
	@RequestMapping("/board/write") 
	public String write(Model model, @RequestParam Map<String,Object> pMap) {
		return "boardWrite"; 
	} 
	
	@RequestMapping("/error/attachFileOverSizeErr") 
	public String handleUploadError(Model model, @RequestParam Map<String,Object> pMap) {
		return "handleUploadError"; 
	}
	
	@RequestMapping("/board/delete") 
	public String delete(Model model, @RequestParam Map<String,Object> pMap) {
		int result = bService.boardDelete(pMap);
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
	public String update(Model model, HttpServletRequest request, @RequestParam Map<String,Object> pMap
			, @RequestParam(value = "imagePath" , required = false) MultipartFile file) throws IOException {
		
		int result = bService.boardUpdate(pMap, file);
		model.addAttribute("result", result); 
		return "redirect:/board/view?idx=" + pMap.get("idx"); 
	} 
	
}
