package com.board.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.board.biz.MemberBiz;
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
	private MessageSource messageSource;
	
	/**
	 * 로그인 처리를 하는 메소드
	 * 
	 * @param mDto 고객정보를 담은 DTO
	 * @param req session에 고객 이름과 번호를 담음
	 * @param rttr 로그인 실패시 메시지를 전달할 flash attribute 사용
	 * @return mv 로그인 성공시 게시글 목록 페이지, 실패시 로그인 페이지로 리다이렉트
	 */
	@RequestMapping("/do/login")
	public ModelAndView login(MemberDto mDto, HttpServletRequest req, RedirectAttributes rttr) {
		ModelAndView mv = new ModelAndView();
		
		//로그인한 회원의 정보를 담은 DTO와 비밀번호 일치여부를 담은 map
		Map<String, Object> loginMap = memBiz.passwordMatching(mDto);
		
		//비밀번호 일치여부 확인
		if((boolean) loginMap.get("isMatch"))    
		{
			MemberDto loginDto = (MemberDto) loginMap.get("loginDto");
			session = req.getSession(); 
			session.setAttribute("customer_nm", loginDto.getCustomer_nm());
			session.setAttribute("customer_no", loginDto.getCustomer_no());
			mv.setViewName("redirect:/board/list");
		} 
		else   
		{ 
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
	public String loginView(){
		return "Login";
	}
	
	/**
	 * 로그아웃 처리 후 로그인 페이지로 이동하는 메소드
	 * 
	 * @param session 로그아웃시 현재 세션 종료
	 * @return redirect:/login 로그인페이지
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public String logout(HttpSession session){
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
	
	/**
	 * 회원가입 페이지로 이동하는 메소드
	 * 
	 * @param mod 유저가 입력할 정보를 담을 새로운 DTO객체를 생성해 전달함
	 * @return Join 회원가입 페이지
	 */
	@RequestMapping("/join")
	public String join(Model mod) {
		mod.addAttribute("mDto", new MemberDto());
		return "Join";
	}
	
	/**
	 * 회원을 insert하는 메소드
	 * 
	 * @param mDto 유저가 입력한 값을 담고, 유효성검사를 하는 MemberDto 객체
	 * @param bindingResult 유효성 검사 결과
	 * @param res 회원가입 결과를 메시지로 전달하도록 HttpServletResponse의 PrintWriter를 이용함
	 * @return Join 에러발생 시 회원가입 페이지로 리턴
	 */
	@RequestMapping("/insert/member")
	public String insertMember(@Valid @ModelAttribute("mDto") MemberDto mDto
			, BindingResult bindingResult, HttpServletResponse res) throws IOException {
		
		//에러 발생시
		if(bindingResult.hasErrors())
		{
			return "Join";
		} 
		else    
		{ 
			//insert
			result = memBiz.insertMember(mDto);
			
			//메시지
			res.setCharacterEncoding("UTF-8");
			res.setContentType("text/html;charset=UTF-8");
			PrintWriter pw = res.getWriter();
			
			if(result == 1)  
			{
				pw.append("<script>");
				pw.append("alert('"+ messageSource.getMessage("Join.doJoin.success", null, Locale.KOREAN) +"'); location.href='/login';");
				pw.append("</script>");
			}
			else
			{
				pw.append("<script>");   
				pw.append("alert('"+ messageSource.getMessage("Join.doJoin.fail", null, Locale.KOREAN) +"'); location.href='/login';");
				pw.append("</script>");
			}
			return null;
		}
	}
}
