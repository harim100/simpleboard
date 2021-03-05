package com.board.controller;

import java.net.http.HttpRequest;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;

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
	List<Map<String,Object>> memList = null;
	
	@Autowired(required=false)
	MemberLogic memLogic; 
	
	@Autowired
	BCryptPasswordEncoder passEncoder;
	
	@RequestMapping("/DoLogin")
	public ModelAndView member_login(@RequestParam Map<String,Object> pMap
			,MemberVO vo, HttpServletRequest req, RedirectAttributes rttr) {
		logger.info("member_login 호출 성공"+pMap);
		
		MemberVO login = null; 
		login = memLogic.login(vo);
		HttpSession session = req.getSession();
		ModelAndView mv = new ModelAndView();
		
		if(login == null) {
			session.setAttribute("member", null);
			rttr.addFlashAttribute("msg", false);
			mv.setViewName("login");
			mv.addObject("msg", "fail");
			logger.info("로그인 실패");
		} else {
			session.setAttribute("customerName", login.getCustomerName());
			logger.info("로그인 성공");
			mv.setViewName("boardList");
			mv.addObject("msg", "success");
			logger.info("MemberController 세션 setAttribute===>"+ login.getCustomerName());
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
		return "login";
	}
	
	@RequestMapping("/create/member")
	public String member_join(Model mod, @RequestParam Map<String,Object> pMap) {
		logger.info("member_join 호출 성공"+pMap);
		
		//패스워드 암호화
		String password = (String) pMap.get("Pw");
		pMap.put("Pw", passEncoder.encode(password));
		
		result = memLogic.member_join(pMap);
		logger.info("결과 : "+result);
		mod.addAttribute("result", result);
		return "login";
	}
	
}
