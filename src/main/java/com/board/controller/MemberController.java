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

import com.board.logic.MemberLogic;
import com.board.vo.MemberVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class MemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	HttpSession session= null;
	int result = 0;
	
	@Autowired(required=false)
	MemberLogic memLogic; 
	
	@Autowired
	BCryptPasswordEncoder passEncoder;
	
	@RequestMapping("/do/login")
	public ModelAndView member_login(@RequestParam Map<String,Object> pMap
			,MemberVO vo, HttpServletRequest req, RedirectAttributes rttr) {
		logger.info("member_login 호출 성공"+pMap);
		
		MemberVO login = null; 
		login = memLogic.login(vo);
		session = req.getSession(); 
		ModelAndView mv = new ModelAndView(); 
		
		boolean passMatch = passEncoder.matches(vo.getPw(), login.getPw());
		
		if(login != null && passMatch) {
			logger.info("로그인 성공");
			session.setAttribute("customerName", login.getCustomerName());
			session.setAttribute("customerNumber", login.getCustomerNum());
			mv.setViewName("redirect:/board/list");
			logger.info("MemberController 세션 setAttribute===>"+ login.getCustomerName());
		} else {
			session.setAttribute("customerName", null);
			rttr.addFlashAttribute("msg", false);
			logger.info("msg ===>" + rttr.getAttribute("msg"));
			mv.setViewName("redirect:/login");
			logger.info("로그인 실패");
		} 
		 
		return mv;
	}
	
	@RequestMapping("/login")
	public String loginView(HttpSession session) throws Exception{
		return "login";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) throws Exception{
		session.invalidate();
		return "login";
	}
	
	@ResponseBody
	@RequestMapping("/check-id")
	public int member_id_check(Model mod, @RequestParam Map<String,Object> pMap) {
		logger.info("member_id_check 호출 성공"+pMap);
		result = memLogic.member_id_check(pMap);
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
		logger.info("member_join 호출 성공"+pMap);
		
		//패스워드 암호화
		String password = (String) pMap.get("Pw");
		pMap.put("Pw", passEncoder.encode(password));
		
		//insert
		result = memLogic.insertMember(pMap);
		logger.info("결과 : "+result);
		 
		//이름 세션에 담기
		session = req.getSession();
		session.setAttribute("customerName", pMap.get("CustomerName"));
		mod.addAttribute("result", result);
		return "redirect:/board/list";
	}
	
}
