package com.board.frm.util;

import org.apache.commons.fileupload.FileUploadException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class ExceptionAdvice {
	
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public ModelAndView uploadSizeExceptionHandler() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("HandleErrors");
		mv.addObject("fileError", "첨부파일 사이즈는 5MB를 넘길 수 없습니다.");
		return mv;
	}
	
	@ExceptionHandler(FileUploadException.class)
	public ModelAndView fileExtensionExceptionHandler(FileUploadException fe) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("HandleErrors");
		mv.addObject("fileError", fe.getMessage());
		return mv;
	}
}
