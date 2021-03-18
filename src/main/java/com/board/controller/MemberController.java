package com.board.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.board.dto.BoardDto;
import com.board.dto.MemberDto;
import com.board.service.MemberService;
  
/** 
 * 
 */ 
@Controller
public class MemberController {
	 
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	HttpSession session= null;
	int result = 0;
	
	@Autowired(required=false)
	MemberService memService; 
	
	@Autowired
	BCryptPasswordEncoder passEncoder;
	
	@RequestMapping("/do/login")
	public ModelAndView member_login(MemberDto mDto, HttpServletRequest req, RedirectAttributes rttr) {
		
		MemberDto login = null; 
		login = memService.login(mDto);
		session = req.getSession(); 
		ModelAndView mv = new ModelAndView(); 
		
		boolean passMatch = passEncoder.matches(mDto.getPw(), login.getPw());
		
		if(login != null && passMatch) {
			session.setAttribute("customerName", login.getCustomerName());
			session.setAttribute("customerNumber", login.getCustomerNum());
			mv.setViewName("redirect:/board/list");
		} else { 
			session.setAttribute("customerName", null);
			rttr.addFlashAttribute("msg", false);
			mv.setViewName("redirect:/login");
		} 
		 
		return mv;
	}
	
	@RequestMapping(value={"/", "/login"})
	public String loginView(HttpSession session) throws Exception{
		return "login";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) throws Exception{
		session.invalidate();
		return "redirect:/login";
	}
	
	@ResponseBody
	@RequestMapping("/check/id")
	public int member_id_check(@RequestParam String requestedId) {
		result = memService.member_id_check(requestedId);
		return result;
	}
	
	@RequestMapping("/logout")  
	public String member_logout(@RequestParam Map<String,Object> pMap) {
		return "redirect:/login";
	}
	
	@RequestMapping("/join")
	public String member_join() {
		return "join";
	}
	 
	@RequestMapping("/create/member")
	public String insertMember(Model mod, @ModelAttribute MemberDto mDto, HttpServletRequest req, HttpServletResponse res) throws IOException {
		//modelandview 로 
		
		//패스워드 암호화
		String password = mDto.getPw();
		mDto.setPw(passEncoder.encode(password));
		
		//insert
		result = memService.insertMember(mDto);
		
		//이름 세션에 담기
		session = req.getSession();
		session.setAttribute("customerName", mDto.getCustomerName());
		
		mod.addAttribute("result", result);
		
		res.setCharacterEncoding("UTF-8");
		res.setContentType("text/html;charset=UTF-8");
		PrintWriter pw = res.getWriter();
		pw.append("<script>");
		pw.append("alert('회원가입 성공'); location.href='/login';");
		pw.append("</script>");
		
		//url에 같이
		return "redirect:/login";
	}
	
}
