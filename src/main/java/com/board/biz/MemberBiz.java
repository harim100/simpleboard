package com.board.biz;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.board.dao.MemberDao;
import com.board.dto.MemberDto;

@Service
public class MemberBiz { 
	private static final Logger logger = LoggerFactory.getLogger(MemberBiz.class);
	
	@Autowired
	private MemberDao memDao;
	
	@Autowired
	BCryptPasswordEncoder passEncoder;

//==================================== [[login]] ==========================================
	public MemberDto login(MemberDto mDto) {
		return memDao.login(mDto);
	}
	
	public int checkId(String requestedId) {
		return memDao.checkId(requestedId);
	}
	
	public Map<String, Object> passwordMatching(MemberDto mDto) {
		MemberDto loginDto = this.login(mDto);
		Map<String, Object> loginMap = new HashMap<>();
		loginMap.put("isMatch", passEncoder.matches(mDto.getPw(), loginDto.getPw()));
		loginMap.put("loginDto", loginDto);
		
		return loginMap;
	}

//==================================== [[join]] ==========================================
	public int insertMember(MemberDto mDto) {
		//패스워드 암호화
		String password = mDto.getPw();
		mDto.setPw(passEncoder.encode(password));
		
		return memDao.insertMember(mDto);
	}
}
