package com.board.frm.util;

import org.apache.commons.fileupload.FileUploadException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.board.dto.BoardDto;
import com.board.dto.MemberDto;
import com.microsoft.sqlserver.jdbc.SQLServerException;

/**
 * 컨트롤러에서 exception 발생 시 처리하는 클래스
 * 
 * @author Jung.Harim
 *
 */
@ControllerAdvice
public class ExceptionAdvice {
	
	/**
	 * 게시글 작성/수정 시 첨부파일 사이즈 초과시 예외가 발생하면 에러페이지로 연결 후 뒤로가기를 실행
	 * 
	 * @return mv 에러페이지
	 */
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public ModelAndView uploadSizeExceptionHandler() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("HandleErrors");
		mv.addObject("movePage", false);
		mv.addObject("message", "첨부파일 사이즈는 5MB를 넘길 수 없습니다.");
		return mv;
	}
	
	/**
	 * 게시글 작성 시 잘못된 확장자인 파일을 첨부하면 {@link com.board.biz.BoardBiz#uploadFile(MultipartFile file)}에서 
	 * 예외를 던지고 에러페이지로 연결 후 뒤로가기를 실행
	 * 
	 * @see {@link com.board.biz.BoardBizz#uploadFile(MultipartFile file)} 파일확장자 확인 후 불일치 시 예외를 던지는 메소드
	 * @return mv 에러페이지
	 */
	@ExceptionHandler(FileUploadException.class)
	public ModelAndView fileExtensionExceptionHandler(FileUploadException fe) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("HandleErrors");
		mv.addObject("movePage", false);
		mv.addObject("message", fe.getMessage());
		return mv;
	}
	
	/**
	 * 게시글 작성 시 제목 혹은 내용의 길이가 초과될 때 에러페이지로 연결 후 뒤로가기를 실행
	 * 
	 * @return mv 에러페이지
	 */
	@ExceptionHandler(SQLServerException.class)
	public ModelAndView fileExtensionExceptionHandler() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("HandleErrors");
		mv.addObject("movePage", false);
		mv.addObject("message", "제목이나 내용의 글자수가 30자, 100자를 초과합니다");
		return mv;
	}
	
	/**
	 * 게시글 목록, 게시글 페이지에서 숫자가 아닌 한글, 영문 등 잘못된 파라미터를 입력했을 때 에러페이지로 연결 후 게시글 목록으로 이동
	 * 
	 * @return mv 에러페이지
	 */
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ModelAndView methodArgumentTypeMismatchExceptionHandler() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("HandleErrors");
		mv.addObject("movePage", true);
		mv.addObject("message", "잘못된 접근입니다.");
		return mv;
	}
	
	/**
	 * 게시글 목록페이지에서 전체 페이지 수를 초과한 페이지 번호를 파라미터로 입력 시 발생하는 예외를 처리하는 메소드
	 * 
	 * @return mv 에러페이지
	 */
	@ExceptionHandler(NegativeArraySizeException.class)
	public ModelAndView NegativeArraySizeExceptionHandler() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("HandleErrors");
		mv.addObject("movePage", true);
		mv.addObject("message", "잘못된 접근입니다.");
		return mv;
	}
}
