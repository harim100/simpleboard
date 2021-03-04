package com.board.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.board.logic.MemberLogic;

/**
 * Handles requests for the application home page.
 */
@Controller
public class MemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	HttpSession session= null;
	int result = 0;
	Map<String,Object> userMap = null;
	List<Map<String,Object>> memList = null;
	
	@Autowired(required=false)
	MemberLogic memLogic;
	
	@RequestMapping("/login")
	public @ResponseBody List<Map<String,Object>> member_login(@RequestParam Map<String,Object> pMap
			,HttpServletRequest req) {
		logger.info("member_login 호출 성공"+pMap);
		memList = memLogic.member_login(pMap);
		logger.info(pMap.toString());
		pMap.clear();
		pMap=memList.get(0);
		String token ="";
		HttpSession session = req.getSession();
		session.setAttribute("userMap", pMap);
		logger.info("MemberController 세션 setAttribute===>"+pMap);
		return memList;
	}
	@RequestMapping("/id_check")
	public String member_id_check(Model mod, @RequestParam Map<String,Object> pMap) {
		logger.info("member_id_check 호출 성공"+pMap);
		memList = memLogic.member_id_check(pMap);
		return "forward:/test.jsp";
	}
	@RequestMapping("/logout")
	public String member_logout(Model mod, @RequestParam Map<String,Object> pMap) {
		return "sns/member/member_logout";
	}
	
	@RequestMapping("/join")
	public String member_join(Model mod, @RequestParam Map<String,Object> pMap) {
		logger.info("member_join 호출 성공"+pMap);
		result = memLogic.member_join(pMap);
		logger.info("결과 : "+result);
		mod.addAttribute("result", result);
		return "test";
	}
	
}
