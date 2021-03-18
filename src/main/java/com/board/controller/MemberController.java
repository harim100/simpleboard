package com.board.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public ModelAndView member_login(@RequestParam Map<String,Object> pMap
			,MemberDto vo, HttpServletRequest req, RedirectAttributes rttr) {
		
		MemberDto login = null; 
		login = memService.login(vo);
		session = req.getSession(); 
		ModelAndView mv = new ModelAndView(); 
		
		boolean passMatch = passEncoder.matches(vo.getPw(), login.getPw());
		
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
	public int member_id_check(Model mod, @RequestParam Map<String,Object> pMap) {
		result = memService.member_id_check(pMap);
		return result;
	}
	
	@RequestMapping("/logout")  
	public String member_logout(Model mod, @RequestParam Map<String,Object> pMap) {
		return "redirect:/login";
	}
	
	@RequestMapping("/join")
	public String member_join(Model mod, @RequestParam Map<String,Object> pMap) {
		return "join";
	}
	
	@RequestMapping("/create/member")
	public String insertMember(Model mod, @RequestParam Map<String,Object> pMap, HttpServletRequest req) {
		
		//패스워드 암호화
		String password = (String) pMap.get("Pw");
		pMap.put("Pw", passEncoder.encode(password));
		
		//insert
		result = memService.insertMember(pMap);
		 
		//이름 세션에 담기
		session = req.getSession();
		session.setAttribute("customerName", pMap.get("CustomerName"));
		mod.addAttribute("result", result);
		return "redirect:/login";
	}
	
}
