package com.board.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.board.logic.BoardLogic;
import com.board.util.Pagination;
import com.board.vo.BoardVO;

/**
 * Handles requests for the application home page.
 */ 
@Controller
public class BoardController {     
	
	//private final String DOWNLOAD_PATH = "\\resources\\img";
	private final String DOWNLOAD_PATH = "C:\\work";
	
	//private final String SINGLE_FILE_UPLOAD_PATH = "C:\\work";
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	 
	@Inject
    SqlSession sqlSession;
	
	@Autowired(required=false) 
	BoardLogic bLogic;  
	
	Pagination pagination;
	
	@RequestMapping("/board/list") 
	public String boardList(Model model, BoardVO bVO, @RequestParam(defaultValue="0") int page) {
		int totalRows = bLogic.getTotal();
		pagination = new Pagination(totalRows, page);
		int pageArr[] = pagination.getPagesArr();
		Map<String, Integer> offset = new HashMap<>();
		offset.put("offset", pagination.getOffset(page));
		 
		List<Map<String,Object>> bList = null; 
		bList = bLogic.boardList(offset);
		
		model.addAttribute("bList", bList);
		model.addAttribute("pageArr", pageArr);
		return "boardList";  
	} 
	  
	@RequestMapping("/board/view") 
	public String modify(Model model, @RequestParam Map<String,Object> pMap, BoardVO bVO) {
		bVO = bLogic.boardSelect(pMap);
		model.addAttribute("bVO", bVO);
		return "boardModify";  
	}
	  
	@RequestMapping("/board/insert") 
	public String insert(Model model, @RequestParam Map<String,Object> pMap
			, @RequestParam("imagePath") MultipartFile file) throws IllegalStateException, IOException {
		int result = bLogic.boardInsert(pMap, file);
		model.addAttribute("result", result);
		return "redirect:/board/list"; 
	}
	
	@RequestMapping("/board/write") 
	public String write(Model model, @RequestParam Map<String,Object> pMap) {
		return "boardWrite"; 
	}
	
	@ResponseBody
	@RequestMapping("/board/delete") 
	public void delete(Model model, @RequestParam Map<String,Object> pMap) {
		int result = bLogic.boardDelete(pMap);
		model.addAttribute("result", result);
	}     
	  
	@RequestMapping("/board/delete/group")  
	public String deleteGroup(Model model, @RequestParam("idxArr") String[] pMap) {
		logger.info("idxArr===>" + pMap);
		int result = bLogic.boardDeleteGroup(pMap);   
		model.addAttribute("result", result); 
		return "redirect:/board/list";
	}  

	@RequestMapping("/board/update")  
	public String update(Model model, HttpServletRequest request, @RequestParam Map<String,Object> pMap
			, @RequestParam("imagePath") MultipartFile file) throws IOException {
		
		int result = bLogic.boardUpdate(pMap, file);
		model.addAttribute("result", result); 
		logger.info("update pmap ===> " + pMap );
		return "redirect:/board/view?idx=" + pMap.get("idx"); 
	} 
	
	@PostMapping("/singleFileUpload")
	public String singleFileUpload(@RequestParam("mediaFile") MultipartFile file, Model model)
	      throws IOException {
		String originFileName = file.getOriginalFilename();
		long fileSize = file.getSize(); // 파일 사이즈
		logger.info("originFileName= " + originFileName + "fileSize= " + fileSize);
		
	   // Save mediaFile on system 
	   if (!file.getOriginalFilename().isEmpty()) {
	      file.transferTo(new File(DOWNLOAD_PATH, file.getOriginalFilename()));
	      model.addAttribute("msg", "File uploaded successfully.");
	      logger.info("originFileName= " + originFileName + "fileSize= " + fileSize);
	   } else {
	      model.addAttribute("msg", "Please select a valid mediaFile..");
	   }
	   return "fileUploadForm";
	}
	
}
