package com.board.controller;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private final String DOWNLOAD_PATH = "C:\\work";
	private final String SINGLE_FILE_UPLOAD_PATH = "C:\\work";
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
	@Inject
    SqlSession sqlSession;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping(value = "/test") 
	public String test(Model model) {
		logger.info("dbTest");
		List bTitle = sqlSession.selectList("titleList");
		model.addAttribute("bTitle", bTitle);
		return "dbTest";
	}
	
	/*
	 * @param: ?CustomerId=insertTest&CustomerPw=1234&CustomerName=하하하&CustomerPhone=01000000000
	 * 	#{CustomerId}  
		,#{CustomerPw}   
		,#{CustomerName}
		,#{CustomerPhone}	
	 */
	@RequestMapping(value = "/inserttest") 
	public String insertTest(Model model, @RequestParam Map<String,Object> pMap) {
		logger.info("insertTest pMap=" + pMap);
		int result = sqlSession.insert("customer_join", pMap);
		model.addAttribute("result", result);
		return "result";
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
	   return "redirect:/";
	}

	
}
