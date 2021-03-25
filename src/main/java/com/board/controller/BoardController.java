package com.board.controller;

import java.io.IOException;
import java.util.List;

import org.apache.commons.fileupload.FileUploadException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.board.biz.BoardBiz;
import com.board.dto.BoardDto;
import com.board.frm.util.Pagination;

/**
 * 게시판관련 컨트롤러
 * 
 * @author Jung.Harim
 */ 
@Controller
@RequestMapping("/board")
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Autowired
	BoardBiz bBiz;
	
	/**
	 * 게시글 목록과 페이지목록을 불러오는 메소드
	 * 
	 * @param model 프론트로 게시글 목록과 페이지처리 객체를 전달
	 * @param page  쿼리스트링에서 가져오는 현재페이지 번호
	 * @return boardList 게시판 목록 페이지
	 */
	@RequestMapping("/list")
	public String getBoardList(Model model, @RequestParam(defaultValue = "1") int page) {
		Pagination pagination = bBiz.getPages(page);
		List<BoardDto> bList = bBiz.getBoardList(pagination.getOffset(page - 1));

		model.addAttribute("bList", bList);
		model.addAttribute("pagination", pagination);
		return "BoardList";
	}

	/**
	 * 글 수정페이지로 이동하는 메소드
	 * 
	 * @param model  boardDto를 담아 화면단에 전달
	 * @param brdIdx 게시판 글번호
	 * @param bDto   게시글 정보를 담은 DTO
	 * @return boardModify 글 수정 페이지
	 */
	@RequestMapping("/view")
	public String getBoardItem(Model model, @RequestParam("brdIdx") int brdIdx, BoardDto bDto) {
		bDto = bBiz.getBoardItem(brdIdx);
		model.addAttribute("bDto", bDto);
		return "BoardUpdate";
	}

	/**
	 * 게시글을 작성하는 메소드
	 * 
	 * @param model 게시글 작성 결과를 담는 모델
	 * @param bDto  게시글 정보를 담은 DTO
	 * @param file  게시글 작성 시 첨부한 이미지 파일
	 * @return pageName 이동할 페이지, exception 발생 시 뒤로가기
	 * @throws FileUploadException 파일 확장자가 지정된 이미지 확장자가 아닐 시 에러 발생 
	 */
	@RequestMapping("/insert")
	public String insertBoardItem(@ModelAttribute BoardDto bDto
			, @RequestParam(value = "imageFile", required = false) MultipartFile file)
			throws IOException, FileUploadException {
		
		bBiz.insertBoardItem(bDto, file);
		return "redirect:/board/list";
	} 
	
	/**
	 * 글쓰기 페이지로 이동하는 메소드
	 * 
	 * @return boardWrite 글쓰기 페이지
	 */
	@RequestMapping("/write")
	public String write() {
		return "BoardWrite";
	}

	/**
	 * 게시글 목록 페이지 또는 수정페이지에서 단일 게시글을 삭제하는 메소드
	 * 
	 * @param model  삭제 결과를 담음
	 * @param brdIdx 삭제 대상 글번호
	 * @return redirect:/board/list 게시글 목록 페이지
	 */
	@RequestMapping("/delete")
	public String deleteBoardItem(Model model, @RequestParam("brdIdx") int brdIdx) {
		int result = bBiz.deleteBoardItem(brdIdx);
		model.addAttribute("result", result);
		return "redirect:/board/list";    
	}

	/**
	 * 게시글 목록 페이지에서 여러 게시글을 삭제하는 메소드
	 * 
	 * @param model  삭제 결과를 담음
	 * @param idxArr 삭제 대상인 게시글들의 게시글번호 배열
	 * @return redirect:/board/list 게시글 목록 페이지
	 */
	@RequestMapping("/delete/group")
	public String deleteGroup(Model model, @RequestParam(value = "idxArr[]") String[] idxArr) {
		int result = bBiz.deleteBoardGroup(idxArr);
		model.addAttribute("result", result);
		return "redirect:/board/list";
	}

	/**
	 * 게시글 수정 메소드
	 * 
	 * @param model 수정 결과를 전달함
	 * @param bDto  게시글 정보를 담는 DTO
	 * @param file  수정 시 첨부한 이미지 파일
	 * @return "redirect:/board/view?brdIdx=" + bDto.getBrdIdx() 수정된 해당 게시글 페이지
	 * @throws FileUploadException 파일 확장자가 지정된 이미지 확장자가 아닐 시 에러 발생 
	 */
	@RequestMapping("/update")
	public String updateBoardItem(@ModelAttribute BoardDto bDto,
			@RequestParam(value = "imageFile", required = false) MultipartFile file) throws IOException, FileUploadException {
		bBiz.updateBoardItem(bDto, file);
		return "redirect:/board/view?brdIdx=" + bDto.getBrd_idx();
	}

}
