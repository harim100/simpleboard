package com.board.util;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionAdvice {
	
	@ExceptionHandler(RuntimeException.class)
	public ModelAndView runtimeExceptionHandler() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("HandleErrors");
		mv.addObject("exception", "업로드에 실패하였습니다.");
		return mv;
	}
	
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public ModelAndView uploadSizeExceptionHandler() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("HandleErrors");
		mv.addObject("fileError", "첨부파일 사이즈는 5MB를 넘길 수 없습니다.");
		return mv;
	}
	
	@ExceptionHandler(fileExtensionException.class)
	public ModelAndView fileExtensionExceptionHandler(fileExtensionException fe) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("HandleErrors");
		mv.addObject("fileError", fe.getMessage());
		return mv;
	}
}
