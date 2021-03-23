package com.board.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.board.biz.MemberBiz;
import com.board.dto.BoardDto;
import com.board.dto.MemberDto;
  
/** 
 * 고객 관련 컨트롤러
 * @author Jung.Harim
 *   
 */
@Controller
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	HttpSession session= null;
	int result = 0;
	
	@Autowired
	MemberBiz memBiz; 
	
	@Autowired
	BCryptPasswordEncoder passEncoder;
	
	/**
	 * 로그인 처리를 하는 메소드
	 * 
	 * @param mDto 고객정보를 담은 DTO
	 * @param req session에 고객 이름과 번호를 담음
	 * @param rttr 로그인 실패시 메시지를 전달할 flash attribute 생성을 위해 사용
	 * @return mv 로그인 성공시 게시글 목록 페이지, 실패시 로그인 페이지로 리다이렉트
	 */
	@RequestMapping("/do/login")
	public ModelAndView login(MemberDto mDto, HttpServletRequest req, RedirectAttributes rttr) {
		
		MemberDto login = null; 
		login = memBiz.login(mDto);
		session = req.getSession(); 
		ModelAndView mv = new ModelAndView(); 
		
		boolean passMatch = passEncoder.matches(mDto.getPw(), login.getPw());
		
		if(login != null && passMatch) {
			session.setAttribute("customerName", login.getCustomer_name());
			session.setAttribute("customerNumber", login.getCustomer_num());
			mv.setViewName("redirect:/board/list");
		} else { 
			session.setAttribute("customerName", null);
			rttr.addFlashAttribute("msg", false);
			mv.setViewName("redirect:/login");
		} 
		 
		return mv; 
	}
	
	/**
	 * 로그인 페이지로 이동하는 메소드
	 * 
	 * @return login 로그인 페이지
	 */
	@RequestMapping(value={"/", "/login"})
	public String loginView() throws Exception{
		return "Login";
	}
	
	/**
	 * 로그아웃 처리 후 로그인 페이지로 이동하는 메소드
	 * 
	 * @param session 
	 * @return redirect:/login 로그인페이지
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) throws Exception{
		session.invalidate();
		return "redirect:/login";
	}
	
	/**
	 * 회원가입시 아이디 중복을 확인하는 메소드
	 * 
	 * @param requestedId 중복체크할 아이디
	 * @return 중복체크할 아이디를 count한 갯수
	 */
	@ResponseBody
	@RequestMapping("/check/id")
	public int checkId(@RequestParam String requestedId) {
		result = memBiz.checkId(requestedId);
		return result;
	}
	
	@RequestMapping("/logout")  
	public String logout(@RequestParam Map<String,Object> pMap) {
		return "redirect:/login";
	}
	
	@RequestMapping("/join")
	public String join(Model mod) {
		mod.addAttribute("mDto", new MemberDto());
		return "Join";
	}
	 
	@RequestMapping("/insert/member")
	public String insertMember(@Valid @ModelAttribute("mDto") MemberDto mDto
			, BindingResult bindingResult
			, HttpServletRequest req, HttpServletResponse res) throws IOException {
		
		//에러 발생시
		if(bindingResult.hasErrors())
		{
			return "Join";
		} 
		else
		{ 
			//패스워드 암호화
			String password = mDto.getPw();
			mDto.setPw(passEncoder.encode(password));
			
			//insert
			result = memBiz.insertMember(mDto);
			
			//이름 세션에 담기
			session = req.getSession();
			session.setAttribute("customerName", mDto.getCustomer_name());
			
			
			//메시지
			res.setCharacterEncoding("UTF-8");
			res.setContentType("text/html;charset=UTF-8");
			PrintWriter pw = res.getWriter();
			
			if(result == 1) 
			{
				pw.append("<script>");
				pw.append("alert('회원가입 성공'); location.href='/login';");
				pw.append("</script>");
			}
			else
			{
				pw.append("<script>");
				pw.append("alert('회원가입 실패!'); location.href='/login';");
				pw.append("</script>");
			}
			
			return null;
		}
	}
	
}
